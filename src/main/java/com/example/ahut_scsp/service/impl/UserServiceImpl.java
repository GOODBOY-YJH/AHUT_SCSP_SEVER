package com.example.ahut_scsp.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ahut_scsp.domain.User;
import com.example.ahut_scsp.service.UserService;
import com.example.ahut_scsp.mapper.UserMapper;
import com.example.ahut_scsp.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
* @author Good Boy
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-04-27 18:13:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageUtils searchUserByPage(HashMap param) {
        ArrayList<HashMap> list = userMapper.searchUserByPage(param);
        long count = userMapper.searchUserCount(param);

        int start = (Integer)param.get("start");
        int length = (Integer)param.get("length");
        PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }

    @Override
    public int changeUserType(HashMap params) {
        return userMapper.changeUserType(params);
    }

    @Override
    public int deleteUserById(int userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public HashMap getUserInfoById(int id) {
        return userMapper.getUserInfoById(id);
    }

    @Override
    public int updatePassword(HashMap params) {
        return userMapper.updatePassword(params);
    }
}




