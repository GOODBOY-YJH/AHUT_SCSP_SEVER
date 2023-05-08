package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchMoreRepliesForm {

    @NotNull(message = "start不能为空")
    private int start;
    @NotNull(message = "replyPageSize不能为空")
    private int replyPageSize;
    @NotNull(message = "postId不能为空")
    private int postId;
    @NotNull(message = "commentId不能为空")
    private int commentId;
}
