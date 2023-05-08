package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【post(帖子)】的数据库操作Mapper
* @createDate 2023-05-02 01:10:22
* @Entity com.example.ahut_scsp.domain.Post
*/
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    public ArrayList<HashMap> searchPostByPage(HashMap param);

    public long searchPostCount(HashMap param);

    public HashMap selectPostById(Integer id);

    public boolean checkLikes(int userId, int postId);

    public int updateLikes(HashMap params);

    public int searchLikesById(int postId);

    public int deletePostById(int postId);

    public String searchPostDelete(int postId);

    public int updatePostById(HashMap params);
}




