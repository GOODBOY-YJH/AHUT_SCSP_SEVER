package com.example.ahut_scsp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 团队表
 * @TableName team
 */
@TableName(value ="team")
@Data
public class Team implements Serializable {
    /**
     * 团队ID，主键，自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID，外键，关联用户表
     */
    private Integer userId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 团队介绍
     */
    private String teamIntroduction;

    /**
     * 团队图片
     */
    private String teamImage;

    /**
     * 团队所属学院
     */
    private String teamCollege;

    /**
     * 团队类型 0:自组 & 1:老师带队
     */
    private String teamType;

    /**
     * 联系方式
     */
    private String contactInformation;

    /**
     * 团队状态('active', 'over')
     */
    private String teamStatus;

    /**
     * 团队创建时间
     */
    private Date teamCreateTime;

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