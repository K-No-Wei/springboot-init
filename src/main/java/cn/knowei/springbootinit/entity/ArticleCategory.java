package cn.knowei.springbootinit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @TableName kno_article_category
 */
@TableName(value ="kno_article_category")
@Data
@Entity
public class ArticleCategory implements Serializable {
    /**
     * 分类和文章
     */
    @Id
    @TableId(value = "id")
    @GeneratedValue
    private Long id;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 分类id
     */
    @TableField(value = "category_id")
    private Long categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}