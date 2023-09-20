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
 * @TableName kno_category
 */
@TableName(value ="kno_category")
@Data
@Entity
public class Category implements Serializable {
    /**
     * 分类id
     */
    @Id
    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;

    /**
     * 分类名称
     */
    @TableField(value = "category_name")
    private String categoryName;

    /**
     * 分类描述
     */
    @TableField(value = "category_des")
    private String categoryDes;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}