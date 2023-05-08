package com.example.ahut_scsp.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ahut_scsp.controller.form.*;
import com.example.ahut_scsp.domain.User;
import com.example.ahut_scsp.service.UserService;
import com.example.ahut_scsp.util.PageUtils;
import com.example.ahut_scsp.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户模块web接口")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 生成登陆验证码
     */
    @GetMapping("/createCode")
    @Operation(summary = "生成登陆验证码")
    public void createQrCode(HttpSession session, HttpServletResponse response) throws IOException {
        // 利用 hutool 工具，生成验证码图片资源
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 50, 4, 5);
        captcha.createCode();
        // 获得生成的验证码字符
        String code = captcha.getCode();

        // 利用 session 来存储验证码
        session.setAttribute("code",code);
        System.out.println("createCode:" + session);
        // 将验证码图片的二进制数据写入【响应体 response 】
        captcha.write(response.getOutputStream());
    }

    /**
     * 检测登陆验证码
     *
     * @param form
     * @return
     */
    @PostMapping("/checkCode")
    @Operation(summary = "检测登陆验证码")
    public R checkQrCode(HttpSession session, @Valid @RequestBody CheckQrCodeForm form) {
        System.out.println(form.getCode() + " " + session.getAttribute("code"));
        System.out.println("checkCode:" + session);
        boolean bool = form.getCode().trim().equals(session.getAttribute("code"));
        if(!bool){
            return R.error().put("result", false).put("message","验证码错误，请重试");
        }
        return R.ok().put("result", true);
    }


    @PostMapping("/register")
    @Operation(description = "注册用户")
    public R register(@Valid @RequestBody RegisterForm form){
        User user = new User();
        user.setUserName(form.getUserName().trim());
        user.setPassword(form.getPassword().trim());
        // 账号已被使用的逻辑
        User oldUser = userService.getOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        System.out.println(oldUser);
        if(oldUser != null){
            return R.error("用户名已存在");
        }
        // 保存用户信息
        boolean save = userService.save(user);
        if(save){
            StpUtil.login(user.getId());
        } else {
            return R.ok("用户注册失败, 请重试");
        }
        return R.ok("用户注册成功").put("token", StpUtil.getTokenValue());
    }

    @PostMapping("/searchUserListByPage")
    @Operation(description = "分页查询所有用户")
    public R searchUserListByPage(@Valid @RequestBody SearchUserByPageForm form){
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start", start);
        PageUtils pageUtils = userService.searchUserByPage(param);
        return R.ok().put("page", pageUtils);
    }

    @PostMapping("/login")
    @Operation(description = "用户登陆")
    public R login(@Valid @RequestBody LoginForm form){
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", form.getUserName().trim()).eq("password", form.getPassword().trim()));
        if(user != null){
            StpUtil.login(user.getId());
        } else {
            return R.error("账号或密码错误");
        }
        return R.ok("用户登陆").put("token", StpUtil.getTokenValue());
    }

    @PostMapping("/updatePassword")
    @Operation(description = "修改密码")
    @SaCheckLogin
    public R updatePassword(@Valid @RequestBody UpdatePasswordForm form){
        HashMap params = new HashMap(){{
            put("password", form.getPassword());
            put("userId", StpUtil.getLoginIdAsInt());
        }};
        int rows = userService.updatePassword(params);
        return R.ok();
    }

    @GetMapping("/isLogin")
    @Operation(description = "判断是否登陆")
    public R isLogin(){
        boolean login = StpUtil.isLogin();
        String userAdmin = null;
        int userId = -1;
        if(login) {
            User user = userService.getById(StpUtil.getLoginIdAsInt());
            userAdmin = user.getUserType();
            userId = StpUtil.getLoginIdAsInt();
        }
        return R.ok().put("login", login).put("userId", userId).put("isAdmin", userAdmin);
    }

    @GetMapping("/outLogin")
    @Operation(description = "退出登陆")
    @SaCheckLogin
    public R outLogin(){
        StpUtil.logout();
        return R.ok("已退出"); // .put("token", "")
    }

    @GetMapping("/info")
    @Operation(description = "获取用户信息")
    @SaCheckLogin
    public R info(@RequestParam(required = false,name = "userId") Integer userId){
        HashMap userInfo;
        if(userId != null && userId > 0){
            userInfo = userService.getUserInfoById(userId);
        }else {
            userInfo = userService.getUserInfoById(StpUtil.getLoginIdAsInt());
        }
        return R.ok().put("user", userInfo);
    }

    @PostMapping("/deleteUserById")
    @Operation(description = "删除用户信息")
    public R deleteUserById(@Valid @RequestBody DeleteUserByIdForm form){
        int rows = userService.deleteUserById(form.getUserId());
        return R.ok().put("rows", rows);
    }

    @PostMapping("/changeUserType")
    @Operation(description = "修改用户类型")
    public R changeUserType(@Valid @RequestBody ChangeUserTypeForm form){
        HashMap params = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = userService.changeUserType(params);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/updateUserInfo")
    @Operation(description = "更新用户信息")
    @SaCheckLogin
    public R updateUserInfo(@Valid @RequestBody UpdateUserInfoForm form){
        User user = new User();
        if(form.getUserId() != -1 && form.getUserId() > 0){
            user.setId(form.getUserId());
        }else {
            user.setId(StpUtil.getLoginIdAsInt());
        }
        if(StrUtil.isNotBlank(form.getName())){
            user.setUserName(form.getName());
        }
        if(StrUtil.isNotBlank(form.getAddress())){
            user.setAddress(form.getAddress());
        }
        if(StrUtil.isNotBlank(form.getAvatar())){
            user.setAvatar(form.getAvatar());
        }
        if(StrUtil.isNotBlank(form.getBirthday())){
            user.setBirthDate(DateUtil.parse(form.getBirthday()));
        }
        if(StrUtil.isNotBlank(form.getEmail())){
            user.setEmail(form.getEmail());
        }
        if(StrUtil.isNotBlank(form.getGender())){
            user.setGender(form.getGender());
        }
        if(StrUtil.isNotBlank(form.getPhoneNumber())){
            user.setPhoneNumber(form.getPhoneNumber());
        }
        boolean rows = userService.updateById(user);
        return R.ok().put("rows", rows);
    }
}
