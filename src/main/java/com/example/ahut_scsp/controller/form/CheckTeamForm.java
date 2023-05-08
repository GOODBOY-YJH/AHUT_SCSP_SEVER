package com.example.ahut_scsp.controller.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckTeamForm {
    @NotNull(message = "teamId不能为空")
    private int teamId;

    @NotNull(message = "status不能为空")
    private String status;
}
