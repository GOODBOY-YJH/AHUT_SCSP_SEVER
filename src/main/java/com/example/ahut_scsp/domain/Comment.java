package com.example.ahut_scsp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论ID，主键，自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID，外键，关联用户表
     */
    private Integer userId;
    /**
     * 用户ID，被回复的id
     */
    private Integer receiveUserId;
    /**
     * 帖子ID，外键，关联帖子表
     */
    private Integer postId;

    /**
     * 上一级ID
     */
    private Integer primerId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论是否被删除
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