package cn.knowei.springbootinit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @TableName kno_article
 */
@TableName(value ="kno_article")
@Data
@Entity
public class Article implements Serializable {
    /**
     * 博文ID
     */
    @Id
    @TableId(value = "article_id", type = IdType.AUTO)
    @GeneratedValue
    private Long articleId;

    /**
     * 博客标题
     */
    @TableField(value = "article_title")
    private String articleTitle;

    /**
     * 博客内容
     */
    @TableField(value = "article_content")
    private String articleContent;

    /**
     * 文章图片链接
     */
    @TableField(value = "article_cover")
    private String articleCover;

    /**
     * 文章详情
     */
    @TableField(value = "article_summary")
    private String articleSummary;


    /**
     * 文章浏览
     */
    @TableField(value = "article_views")
    private Long articleViews;

    /**
     * 文章点赞
     */
    @TableField(value = "article_like")
    private Long articleLike;

    /**
     * 发表时间
     */
    @TableField(value = "article_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleDate;

    /**
     * 是否删除，0-未删除，1-删除，默认为0
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}