package com.example.ahut_scsp.controller;

import com.example.ahut_scsp.controller.form.TestForm;
import com.example.ahut_scsp.domain.User;
import com.example.ahut_scsp.service.UserService;
import com.example.ahut_scsp.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Tag(name = "TestController", description = "测试web接口")
@Slf4j
public class TestController {

    @Autowired
    public UserService userService;

    @GetMapping("/getUser")
    @Operation(description = "获取用户信息")
    public R getUser(){
        return R.ok().put("name","aaa.jpg").put("url","http:localhost:8080/asdj.jpg");
    }
    @PostMapping("/picture")
    public R postPicture(){
        return R.ok().put("name","aaa.jpg").put("url","http:localhost:8080/asdj.jpg");
    }


    @PostMapping("/testType")
    public R testType(@Valid @RequestBody TestForm form){
        System.out.println(form);
        return R.ok();
    }


}
