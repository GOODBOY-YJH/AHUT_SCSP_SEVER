package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.TeamTypes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
* @author Good Boy
* @description 针对表【team_types(团队类型)】的数据库操作Mapper
* @createDate 2023-05-01 11:50:49
* @Entity com.example.ahut_scsp.domain.TeamTypes
*/
@Mapper
public interface TeamTypesMapper extends BaseMapper<TeamTypes> {
    public ArrayList searchAllType();
}




