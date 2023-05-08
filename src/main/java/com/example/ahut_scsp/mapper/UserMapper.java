package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
* @author Good Boy
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-04-27 18:13:21
* @Entity com.example.ahut_scsp.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    public HashMap getUserInfoById(int id);

    public int updatePassword(HashMap params);

    public ArrayList<HashMap> searchUserByPage(HashMap param);

    public long searchUserCount(HashMap param);

    public int changeUserType(HashMap params);

    public int deleteUserById(int userId);
}




