package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;


@Data
public class SearchUserByPageForm {

    @NotNull(message = "page不能为空")
    @Min(value = 1, message = "page不能小于1")
    private Integer page;

    @NotNull(message = "length不能为空")
    @Range(min = 3, max = 50, message = "length必须在3~50之间")
    private Integer length;

    // 关键词
    private String keyword;
}
