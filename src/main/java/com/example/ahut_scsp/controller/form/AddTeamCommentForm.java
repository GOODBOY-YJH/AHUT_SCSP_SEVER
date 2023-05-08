package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class AddTeamCommentForm {
    @NotBlank(message = "commentContent不能为空")
    private String commentContent;
    @NotBlank(message = "teamId不能为空")
    private String teamId;
}
