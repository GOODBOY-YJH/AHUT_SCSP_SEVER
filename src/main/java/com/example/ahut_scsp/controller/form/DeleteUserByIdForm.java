package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteUserByIdForm {
    @NotNull(message = "userId不能为空")
    private int userId;
}
