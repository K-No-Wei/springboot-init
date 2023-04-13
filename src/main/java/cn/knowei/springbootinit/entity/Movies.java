package cn.knowei.springbootinit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName movies
 */
@TableName(value ="movies")
@Data
public class Movies implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "actors")
    private String actors;

    /**
     * 
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 
     */
    @TableField(value = "genres")
    private String genres;

    /**
     * 
     */
    @TableField(value = "regions")
    private String regions;

    /**
     * 
     */
    @TableField(value = "storyline")
    private String storyline;

    /**
     * 
     */
    @TableField(value = "tags")
    private String tags;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}