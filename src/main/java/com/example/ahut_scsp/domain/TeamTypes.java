package com.example.ahut_scsp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 团队类型
 * @TableName team_types
 */
@TableName(value ="team_types")
@Data
public class TeamTypes implements Serializable {
    /**
     * 评论ID，主键，自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    private String type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}