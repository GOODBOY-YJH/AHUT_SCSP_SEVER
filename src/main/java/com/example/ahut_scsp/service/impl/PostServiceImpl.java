package com.example.ahut_scsp.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ahut_scsp.controller.form.AddPostForm;
import com.example.ahut_scsp.controller.form.SearchPostByPageForm;
import com.example.ahut_scsp.controller.form.UpdatePostForm;
import com.example.ahut_scsp.domain.Post;
import com.example.ahut_scsp.domain.User;
import com.example.ahut_scsp.mapper.CommentMapper;
import com.example.ahut_scsp.mapper.UserMapper;
import com.example.ahut_scsp.service.PostService;
import com.example.ahut_scsp.mapper.PostMapper;
import com.example.ahut_scsp.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
* @author Good Boy
* @description 针对表【post(帖子)】的数据库操作Service实现
* @createDate 2023-04-27 18:13:21
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addPost(AddPostForm form) {
        Post post = new Post();
        post.setPostTitle(form.getTitle());
        post.setPostContent(form.getContent());
        post.setPostLike("");
        post.setIsDelete("no");
        post.setUserId(StpUtil.getLoginIdAsInt());
        int rows = postMapper.insert(post);
        return rows;
    }

    @Override
    public PageUtils searchPostByPage(HashMap param) {
        ArrayList<HashMap> list = postMapper.searchPostByPage(param);

        for (HashMap map : list) {
            int comments = commentMapper.searchPostCommentCount((int) map.get("id"));
            map.put("comments", comments);
        }

        long count = postMapper.searchPostCount(param);
        int start = (Integer)param.get("start");
        int length = (Integer)param.get("length");
        PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }

    @Override
    public HashMap postInfoById(int postId) {
        HashMap postInfo = postMapper.selectPostById(postId);
        return postInfo;
    }

    @Override
    public HashMap commentInfoByPostId(int postId) {
        HashMap commentInfo = new HashMap();
        // 查询评论信息 第一次加载三条
        ArrayList<HashMap> comments = commentMapper.selectCommentByPostId(postId);
        // 封装每一条评论数据
        if(comments != null) {
            for (HashMap comment : comments) {
                User user = userMapper.selectById((int) comment.get("userId"));
                comment.put("name", user.getUserName());
                comment.put("avatar", user.getAvatar());

                // 用于前端页面展示使用，本应该做到前后端解耦合，这里为了方便，耦合度高了一点
                comment.put("showReply", false);
                comment.put("replyPageSize", 3);

                int replyTotalCount = commentMapper.searchReplyCommentCount((int) comment.get("id"), postId);
                comment.put("replyTotalCount", replyTotalCount);


                // 查询评论回复信息 第一次加载3条
                ArrayList<HashMap> replies = commentMapper.searchReplyComment((int) comment.get("id"), postId);
                // 封装每一条评论回复
                for (HashMap reply : replies) {
                    User user1 = userMapper.selectById((int) reply.get("userId"));
                    reply.put("name", user1.getUserName());
                    reply.put("avatar", user1.getAvatar());
                    if(ObjectUtil.isNotNull(reply.get("receiveUserId"))){
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
        commentInfo.put("comments", comments);

        // 用于前端页面展示使用，本应该做到前后端解耦合，这里为了方便，耦合度高了一点
        commentInfo.put("commentPageSize", 3);
        int commentTotalCount = commentMapper.searchCommentCountByPostId(postId);
        commentInfo.put("commentTotalCount", commentTotalCount);
        int likes = postMapper.searchLikesById(postId);
        commentInfo.put("likes", likes);
        return commentInfo;
    }

    @Override
    public boolean checkLikes(int userId, int postId) {
        return postMapper.checkLikes(userId, postId);
    }

    @Override
    public int updateLikes(HashMap params) {
        int rows = postMapper.updateLikes(params);
        return rows;
    }

    @Override
    public int searchLikesById(int postId) {
        return postMapper.searchLikesById(postId);
    }

    @Override
    public int deletePostById(int postId) {
        return postMapper.deletePostById(postId);
    }

    @Override
    public String searchPostDelete(int postId) {
        return postMapper.searchPostDelete(postId);
    }

    @Override
    public int updatePostById(HashMap params) {
        return postMapper.updatePostById(params);
    }
}




