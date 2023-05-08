package com.example.ahut_scsp.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.example.ahut_scsp.controller.form.DeleteCosFileForm;
import com.example.ahut_scsp.util.R;
import com.example.ahut_scsp.util.ScspException;
import com.example.ahut_scsp.util.oss.CosUtil;
import com.example.ahut_scsp.util.oss.TypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/cos")
@Tag(name = "CosController", description = "对象存储web接口")
@Slf4j
public class CosController {
    @Autowired
    private CosUtil cosUtil;

    @PostMapping("/uploadCosFile")
    @Operation(summary = "上传文件")
    @SaCheckLogin
    public R uploadCosFile(@Param("file") MultipartFile file, @Param("type") String type){
        TypeEnum typeEnum = TypeEnum.findByKey(type);
        if(typeEnum == null){
            throw new ScspException("type类型错误");
        }
        try {
            HashMap map = cosUtil.uploadFile(file, typeEnum);
            return R.ok(map);
        } catch (IOException e) {
            log.error("文件上传到腾讯云错误",e);
            throw new ScspException("文件上传到腾讯云错误");
        }
    }

    @PostMapping("/deleteCosFile")
    @Operation(summary = "删除文件")
    @SaCheckLogin
    public R deleteCosFile(@Valid @RequestBody DeleteCosFileForm form){
        cosUtil.deleteFile(form.getPathes());
        return R.ok();
    }

}
