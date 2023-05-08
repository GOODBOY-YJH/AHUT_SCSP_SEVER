package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddLikeForm {
    @NotNull(message = "postId不为空")
    private int postId;
}
