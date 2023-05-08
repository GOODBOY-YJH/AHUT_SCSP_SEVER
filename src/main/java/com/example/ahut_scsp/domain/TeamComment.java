package com.example.ahut_scsp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 团队评论表
 * @TableName team_comment
 */
@TableName(value ="team_comment")
@Data
public class TeamComment implements Serializable {
    /**
     * 评论ID，主键，自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 团队ID，外键，关联团队表
     */
    private Integer teamId;

    /**
     * 用户ID，外键，关联用户表
     */
    private Integer userId;

    /**
     * 上一级ID
     */
    private Integer primerId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论是否被删除 yes 或 no
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