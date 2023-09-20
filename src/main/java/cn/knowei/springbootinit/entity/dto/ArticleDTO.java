package cn.knowei.springbootinit.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.security.DenyAll;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: knowei
 * @Description:
 * @Date: Create in 23:22 2023-04-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO implements Serializable {
    /**
     * 博文ID
     */
    private Long articleId;

    /**
     * 博客标题
     */
    private String articleTitle;

    /**
     * 博客内容
     */
    private String articleContent;

    /**
     * 文章图片链接
     */
    private String articleCover;

    /**
     * 文章详情
     */
    private String articleSummary;

    /**
     * 文章浏览
     */
    private Long articleViews;

    /**
     * 文章点赞
     */
    private Long articleLike;

    /**
     * 发表时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleDate;

}
