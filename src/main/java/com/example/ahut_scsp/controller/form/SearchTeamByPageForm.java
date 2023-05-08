package com.example.ahut_scsp.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;


@Data
public class SearchTeamByPageForm {

    @NotNull(message = "page不能为空")
    @Min(value = 1, message = "page不能小于1")
    private Integer page;

    @NotNull(message = "length不能为空")
    @Range(min = 5, max = 50, message = "length必须在5~50之间")
    private Integer length;

    // 关键词
    private String keyword;

    // 团队类型
    private ArrayList teamTypes;

    // 学院
    private ArrayList colleges;

    //  团队状态 用于审核时查询待审核状态
    private String teamStatus;

    //  查询我的团队时上传
    private Boolean isMe;
}
