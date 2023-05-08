package com.example.ahut_scsp.service;

import com.example.ahut_scsp.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ahut_scsp.util.PageUtils;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
* @author Good Boy
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-04-27 18:13:21
*/
public interface UserService extends IService<User> {
    public HashMap getUserInfoById(int id);

    public int updatePassword(HashMap params);

    public PageUtils searchUserByPage(HashMap param);

    public int changeUserType(HashMap params);

    public int deleteUserById(int userId);
}
