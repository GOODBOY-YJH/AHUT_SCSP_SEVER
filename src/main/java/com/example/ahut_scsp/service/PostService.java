package com.example.ahut_scsp.service;

import cn.hutool.core.lang.hash.Hash;
import com.example.ahut_scsp.controller.form.AddPostForm;
import com.example.ahut_scsp.controller.form.SearchPostByPageForm;
import com.example.ahut_scsp.controller.form.UpdatePostForm;
import com.example.ahut_scsp.domain.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ahut_scsp.util.PageUtils;
import com.example.ahut_scsp.util.R;

import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【post(帖子)】的数据库操作Service
* @createDate 2023-05-02 01:10:22
*/
public interface PostService extends IService<Post> {

    public int addPost(AddPostForm form);

    public PageUtils searchPostByPage(HashMap form);

    public HashMap postInfoById(int postId);

    public HashMap commentInfoByPostId(int postId);

    public boolean checkLikes(int userId, int postId);

    public int updateLikes(HashMap params);

    public int searchLikesById(int postId);

    public int deletePostById(int postId);

    String searchPostDelete(int postId);

    public int updatePostById(HashMap params);
}
