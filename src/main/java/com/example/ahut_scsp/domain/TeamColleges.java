package com.example.ahut_scsp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 全部学院
 * @TableName team_colleges
 */
@TableName(value ="team_colleges")
@Data
public class TeamColleges implements Serializable {
    /**
     * 评论ID，主键，自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学院
     */
    private String college;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}