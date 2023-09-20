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
 * @TableName kno_tag
 */
@TableName(value ="kno_tag")
@Data
@Entity
public class Tag implements Serializable {
    /**
     * 标签id
     */
    @Id
    @GeneratedValue
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Long tagId;

    /**
     * 标签名称
     */
    @TableField(value = "tag_name")
    private String tagName;

    /**
     * 标签描述
     */
    @TableField(value = "tag_des")
    private String tagDes;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}