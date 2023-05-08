package com.example.ahut_scsp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 帖子
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    /**
     * 帖子ID，主键，自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID，外键，关联用户表
     */
    private Integer userId;

    /**
     * 帖子标题
     */
    private String postTitle;


    /**
     * 帖子内容
     */
    private String postContent;

    /**
     * 帖子点赞用户id
     */
    private String postLike;

    /**
     * 帖子是否被删除
     */
    private String isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}