package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.TeamTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【team_teacher_and_student(团队优秀成员)】的数据库操作Mapper
* @createDate 2023-05-01 10:52:54
* @Entity com.example.ahut_scsp.domain.TeamTeacher
*/
@Mapper
public interface TeamTeacherMapper extends BaseMapper<TeamTeacher> {
    public ArrayList<HashMap> selectTeacherByTeamId(int id);
}




