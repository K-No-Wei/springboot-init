package cn.knowei.springbootinit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @TableName kno_user_article_browse
 */
@TableName(value ="kno_user_article_browse")
@Data
@Accessors(chain = true)
@Entity
public class UserArticleBrowse implements Serializable {
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
    @JsonFormat(shape =JsonFormat.Shape.STRING )
    private Long userId;

    /**
     * 最后浏览时间
     */
    @TableField(value = "browse")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date browse;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}