package com.example.ahut_scsp.mapper;

import com.example.ahut_scsp.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【comment(评论表)】的数据库操作Mapper
* @createDate 2023-05-02 20:31:55
* @Entity com.example.ahut_scsp.domain.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    public int searchPostCommentCount(int postId);

    public ArrayList<HashMap> selectCommentByPostId(int postId);

    public ArrayList<HashMap> searchReplyComment(int commentId, int postId);

    public int searchReplyCommentCount(int commentId, int postId);

    public ArrayList<HashMap> searchMoreComments(HashMap params);

    public ArrayList<HashMap> searchMoreReplies(HashMap params);

    public int searchCommentCountByPostId(int postId);

    public HashMap searchCommentById(int commentId);

    public HashMap searchNewReplyById(Integer commentId);
}




