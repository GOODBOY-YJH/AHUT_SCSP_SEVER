package com.example.ahut_scsp.controller.form;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCommentReplyForm {
    @NotNull(message = "postId不能为空")
    private int postId;

    @NotNull(message = "primerId不能为空")
    private int primerId;


    private Integer receiveUserId;

    @NotBlank(message = "内容不能为空")
    private String content;
}
