package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeUserTypeForm {
    @NotNull(message = "userId不能为空")
    private int userId;
    @NotBlank(message = "type不能为空")
    private String type;
}
