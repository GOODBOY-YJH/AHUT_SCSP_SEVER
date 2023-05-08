package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.TeamColleges;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
* @author Good Boy
* @description 针对表【team_colleges(全部学院)】的数据库操作Mapper
* @createDate 2023-05-01 11:50:49
* @Entity com.example.ahut_scsp.domain.TeamColleges
*/
@Mapper
public interface TeamCollegesMapper extends BaseMapper<TeamColleges> {
    public ArrayList searchAllCollege();
}




