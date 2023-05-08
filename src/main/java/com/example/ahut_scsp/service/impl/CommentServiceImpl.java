package com.example.ahut_scsp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ahut_scsp.domain.Comment;
import com.example.ahut_scsp.domain.User;
import com.example.ahut_scsp.mapper.UserMapper;
import com.example.ahut_scsp.service.CommentService;
import com.example.ahut_scsp.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author Good Boy
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2023-05-02 20:31:55
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ArrayList<HashMap> searchMoreComments(HashMap params) {
        ArrayList<HashMap> comments = commentMapper.searchMoreComments(params);
        if(comments != null) {
            for (HashMap comment : comments) {
                User user = userMapper.selectById((int) comment.get("userId"));
                comment.put("name", user.getUserName());
                comment.put("avatar", user.getAvatar());

                // 用于前端页面展示使用，本应该做到前后端解耦合，这里为了方便，耦合度高了一点
                comment.put("showReply", false);
                comment.put("replyPage", 1);
                comment.put("replyPageSize", 3);

                int replyTotalCount = commentMapper.searchReplyCommentCount((int) comment.get("id"), (int) params.get("postId"));
                comment.put("replyTotalCount", replyTotalCount);

                // 查询评论回复信息 第一次加载3条
                ArrayList<HashMap> replies = commentMapper.searchReplyComment((int) comment.get("id"), (int) params.get("postId"));
                // 封装每一条评论回复
                for (HashMap reply : replies) {
                    User user1 = userMapper.selectById((int) reply.get("userId"));
                    reply.put("name", user1.getUserName());
                    reply.put("avatar", user1.getAvatar());
                    if(ObjectUtil.isNotNull(reply.get("receiveUserId"))) {
                        // 查询被回复者的姓名
                        User replyUser = userMapper.selectById((int) reply.get("receiveUserId"));
                        reply.put("receiveUserName", replyUser.getUserName());
                    }
                    // 用于前端页面展示使用，本应该做到前后端解耦合，这里为了方便，耦合度高了一点
                    reply.put("showReply", false);
                }
                comment.put("replies", replies);
            }
        }
        return comments;
    }

    @Override
    public ArrayList<HashMap> searchMoreReplies(HashMap params) {
        ArrayList<HashMap> replies = commentMapper.searchMoreReplies(params);
        // 封装每一条评论回复
        for (HashMap reply : replies) {
            User user = userMapper.selectById((int) reply.get("userId"));
            reply.put("name", user.getUserName());
            reply.put("avatar", user.getAvatar());
            if(ObjectUtil.isNotNull(reply.get("receiveUserId"))){
                // 查询被回复者的姓名
                User replyUser = userMapper.selectById((int) reply.get("receiveUserId"));
                reply.put("receiveUserName", replyUser.getUserName());
            }

            // 用于前端页面展示使用，本应该做到前后端解耦合，这里为了方便，耦合度高了一点
            reply.put("showReply", false);
        }
        return replies;
    }

    @Override
    public HashMap searchCommentById(int commentId, int postId) {
        HashMap comment = commentMapper.searchCommentById(commentId);
        User user = userMapper.selectById((int) comment.get("userId"));
        comment.put("name", user.getUserName());
        comment.put("avatar", user.getAvatar());

        // 用于前端页面展示使用，本应该做到前后端解耦合，这里为了方便，耦合度高了一点
        comment.put("showReply", false);
        comment.put("replyPage", 1);
        comment.put("replyPageSize", 3);

        int replyTotalCount = commentMapper.searchReplyCommentCount(commentId, postId);
        comment.put("replyTotalCount", replyTotalCount);

        // 查询评论回复信息 第一次加载3条
        ArrayList<HashMap> replies = commentMapper.searchReplyComment(commentId, postId);
        // 封装每一条评论回复
        for (HashMap reply : replies) {
            User user1 = userMapper.selectById((int) reply.get("userId"));
            reply.put("name", user1.getUserName());
            reply.put("avatar", user1.getAvatar());
            if(ObjectUtil.isNotNull(reply.get("receiveUserId"))) {
                // 查询被回复者的姓名
                User replyUser = userMapper.selectById((int) reply.get("receiveUserId"));
                reply.put("replyName", replyUser.getUserName());
            }
            // 用于前端页面展示使用，本应该做到前后端解耦合，这里为了方便，耦合度高了一点
            reply.put("showReply", false);
        }
        comment.put("replies", replies);

        return comment;
    }

    @Override
    public HashMap searchNewReplyById(Integer commentId) {
        HashMap reply = commentMapper.searchNewReplyById(commentId);

            User user = userMapper.selectById((int) reply.get("userId"));
            reply.put("name", user.getUserName());
            reply.put("avatar", user.getAvatar());
            if(ObjectUtil.isNotNull(reply.get("receiveUserId"))){
                // 查询被回复者的姓名
                User replyUser = userMapper.selectById((int) reply.get("receiveUserId"));
                reply.put("replyName", replyUser.getUserName());
            }
            // 用于前端页面展示使用，本应该做到前后端解耦合，这里为了方便，耦合度高了一点
            reply.put("showReply", false);
            return reply;
    }
}




