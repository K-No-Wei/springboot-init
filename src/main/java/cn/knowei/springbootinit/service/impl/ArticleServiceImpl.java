package cn.knowei.springbootinit.service.impl;

import cn.knowei.springbootinit.common.BaseResponse;
import cn.knowei.springbootinit.common.ErrorCode;
import cn.knowei.springbootinit.common.PageRequest;
import cn.knowei.springbootinit.common.ResultUtils;
import cn.knowei.springbootinit.constant.RedisConstant;
import cn.knowei.springbootinit.entity.Article;
import cn.knowei.springbootinit.entity.UserArticleBrowse;
import cn.knowei.springbootinit.entity.UserArticleCollect;
import cn.knowei.springbootinit.entity.dto.ArticleDTO;
import cn.knowei.springbootinit.mapper.ArticleMapper;
import cn.knowei.springbootinit.mapper.UserArticleBrowseMapper;
import cn.knowei.springbootinit.mapper.UserArticleCollectMapper;
import cn.knowei.springbootinit.service.ArticleService;
import cn.knowei.springbootinit.utils.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
* @author zheng
* @description 针对表【kno_article】的数据库操作Service实现
* @createDate 2023-04-13 23:17:26
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserArticleBrowseMapper userArticleBrowseMapper;

    // @Resource
    // private Redisson redisson;

    @Override
    public BaseResponse getHotArticle() {
        List<ArticleDTO> articleDTOs = (List<ArticleDTO>) redisTemplate.opsForValue().get(RedisConstant.HOT_ARTICLE);
        // RLock lock = redisson.getLock("zzzzkey");
        // lock.lock();
        if (articleDTOs == null) {
            // synchronized (ArticleService.class) {
            //     if (articleDTOs == null) {
                    LambdaQueryWrapper<Article> qw = new LambdaQueryWrapper<>();
                    qw.orderByDesc(Article::getArticleViews);
                    qw.last(" limit 3");
                    List<Article> articles = this.list(qw);
                    articleDTOs = articles.stream().map(e -> {
                        ArticleDTO articleDTO = new ArticleDTO();
                        BeanUtils.copyProperties(e, articleDTO);
                        return articleDTO;
                    }).collect(Collectors.toList());

                    redisTemplate.opsForValue().set(RedisConstant.HOT_ARTICLE, articleDTOs, 7L, TimeUnit.MINUTES);
                // }
            // }
        }
        // lock.unlock();
        return ResultUtils.success(articleDTOs);
    }

    @Override
    public List<ArticleDTO> getList(PageRequest page) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        IPage<Article> articlePage = page(new Page<>(page.getCurrent(), page.getPageSize()), queryWrapper);
        List<ArticleDTO> articleDTOS = articlePage.getRecords().stream().map(e -> {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(e, articleDTO);
            return articleDTO;
        }).collect(Collectors.toList());

        return articleDTOS;
    }

    @Override
    public boolean like(Long id) {
        Article article = this.getById(id);
        article.setArticleLike(article.getArticleLike() + 1);

        boolean b = this.updateById(article);
        return b;
    }

    @Override
    @Transactional
    public boolean articleView(Long id, HttpServletRequest request) {
        //1.有token，记录浏览文章
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)){
            Claims claims = JwtUtils.parseJWT(token);
            if (claims != null) {
                Long userId = (Long) claims.get("userId");

                UserArticleBrowse userArticleBrowse = userArticleBrowseMapper.selectOne(new LambdaQueryWrapper<UserArticleBrowse>()
                        .eq(UserArticleBrowse::getArticleId, id)
                        .eq(UserArticleBrowse::getUserId, userId));
                if (userArticleBrowse == null) {
                    userArticleBrowseMapper.insert(new UserArticleBrowse()
                            .setArticleId(id)
                            .setUserId(userId)
                            .setBrowse(new Date()));
                } else {
                    userArticleBrowse.setBrowse(new Date());
                    userArticleBrowseMapper.updateById(userArticleBrowse);
                }
            }
        }


        //2.无token,直接文章加一浏览

        Article article = this.getById(id);
        article.setArticleViews(article.getArticleViews() + 1);

        boolean b = this.updateById(article);

        return b;
    }

    @Resource
    private UserArticleCollectMapper collectMapper;

    @Override
    public BaseResponse collectById(Long articleId, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR);
        }

        Claims claims = JwtUtils.parseJWT(token);
        Long userId = (Long) claims.get("userId");
        LambdaQueryWrapper<UserArticleCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserArticleCollect::getArticleId, articleId)
                        .eq(UserArticleCollect::getUserId, userId);

        UserArticleCollect userArticleCollect = collectMapper.selectOne(wrapper);
        if (userArticleCollect != null) {
            return ResultUtils.error(40010, "不可重复收藏");
        }
        UserArticleCollect collect = new UserArticleCollect();
        collect.setArticleId(articleId);
        collect.setUserId(userId);
        collectMapper.insert(collect);
        return ResultUtils.success(true).setMessage("收藏成功");
    }

    @Override
    public BaseResponse getCollectByArticleId(Long articleId, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR);
        }

        Claims claims = JwtUtils.parseJWT(token);
        Long userId = (Long) claims.get("userId");
        LambdaQueryWrapper<UserArticleCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserArticleCollect::getArticleId, articleId)
                .eq(UserArticleCollect::getUserId, userId);

        UserArticleCollect userArticleCollect = collectMapper.selectOne(wrapper);
        if (userArticleCollect != null) {
            return ResultUtils.success(ErrorCode.COLLECT_SUCCESS);
        }
        return ResultUtils.error(ErrorCode.COLLECT_EMPTY);
    }

    @Override
    public BaseResponse deleteByArticleId(Long articleId, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR);
        }

        Claims claims = JwtUtils.parseJWT(token);
        Long userId = (Long) claims.get("userId");
        LambdaQueryWrapper<UserArticleCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserArticleCollect::getArticleId, articleId)
                .eq(UserArticleCollect::getUserId, userId);
        int i = collectMapper.delete(wrapper);

        if (i > 0) {
            return ResultUtils.success("true").setMessage("取消收藏");
        }
        return ResultUtils.error(ErrorCode.COLLECT_EMPTY);
    }
}




