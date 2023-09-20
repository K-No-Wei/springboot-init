package cn.knowei.springbootinit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @TableName kno_user_article_collect
 */
@TableName(value ="kno_user_article_collect")
@Data
@Entity
public class UserArticleCollect implements Serializable {
    /**
     * id
     */
    @Id
    @TableId(value = "id", type = IdType.AUTO)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING )
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}