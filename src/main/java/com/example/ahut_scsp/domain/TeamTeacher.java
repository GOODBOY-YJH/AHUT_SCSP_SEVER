package com.example.ahut_scsp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 团队优秀成员
 * @TableName team_teacher
 */
@TableName(value ="team_teacher")
@Data
public class TeamTeacher implements Serializable {
    /**
     * 团队优秀成员ID，主键，自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 团队ID，外键，关联团队表
     */
    private Integer teamId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 简介
     */
    private String content;

    /**
     * 图片url
     */
    private String avatar;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}