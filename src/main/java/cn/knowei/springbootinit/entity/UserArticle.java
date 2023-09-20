package cn.knowei.springbootinit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @TableName kno_user_article
 */
@TableName(value ="kno_user_article")
@Data
@Entity
public class UserArticle implements Serializable {
    /**
     * id
     */
    @Id
    @TableId(value = "id")
    private Long id;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 是否点赞
     */
    @TableField(value = "article_like")
    private Long articleLike;

    /**
     * 是否收藏
     */
    @TableField(value = "article_collect")
    private Long articleCollect;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}