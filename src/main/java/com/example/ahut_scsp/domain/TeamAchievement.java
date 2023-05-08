package com.example.ahut_scsp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 团队成果表
 * @TableName team_achievement
 */
@TableName(value ="team_achievement")
@Data
public class TeamAchievement implements Serializable {
    /**
     * 团队成果ID，主键，自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 团队ID，外键，关联团队表
     */
    private Integer teamId;

    /**
     * 成果名称
     */
    private String achievementDescription;

    /**
     * 成果图片
     */
    private String achievementImage;

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