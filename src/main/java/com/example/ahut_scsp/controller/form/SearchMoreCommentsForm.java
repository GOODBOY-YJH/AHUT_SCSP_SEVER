package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchMoreCommentsForm {
    @NotNull(message = "start不能为空")
    private int start;
    @NotNull(message = "commentPageSize不能为空")
    private int commentPageSize;
    @NotNull(message = "postId不能为空")
    private int postId;
}
