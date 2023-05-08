package com.example.ahut_scsp.service;

import com.example.ahut_scsp.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【comment(评论表)】的数据库操作Service
* @createDate 2023-05-02 20:31:55
*/
public interface CommentService extends IService<Comment> {

    public ArrayList<HashMap> searchMoreComments(HashMap params);

    public ArrayList<HashMap> searchMoreReplies(HashMap params);

    public HashMap searchCommentById(int commentId, int postId);

    public HashMap searchNewReplyById(Integer commentId);
}
