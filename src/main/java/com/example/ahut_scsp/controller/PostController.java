package com.example.ahut_scsp.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.example.ahut_scsp.controller.form.*;
import com.example.ahut_scsp.domain.Comment;
import com.example.ahut_scsp.service.CommentService;
import com.example.ahut_scsp.service.PostService;
import com.example.ahut_scsp.util.PageUtils;
import com.example.ahut_scsp.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/post")
@Tag(name = "TeamController", description = "帖子模块web接口")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/addPost")
    @Operation(description = "添加帖子")
    public R addPost(@Valid @RequestBody AddPostForm form){
        int rows = postService.addPost(form);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/updatePost")
    @Operation(description = "更新帖子")
    public R updatePost(@Valid @RequestBody UpdatePostForm form){
        HashMap params = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = postService.updatePostById(params);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/searchPostByPage")
    @Operation(description = "查询帖子")
    public R searchPostByPage(@Valid @RequestBody SearchPostByPageForm form){

        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start", start);
        PageUtils pageUtils = postService.searchPostByPage(param);

        return R.ok().put("page", pageUtils);
    }

    @GetMapping("/postInfoById")
    @Operation(description = "获取帖子信息")
    public R searchPostInfoById(@RequestParam("id") Integer postId){
        HashMap postInfo = postService.postInfoById(postId);
        return R.ok().put("postInfo", postInfo);
    }

    @GetMapping("/checkLikes")
    @Operation(description = "查询是否已经点赞")
    @SaCheckLogin
    public R checkLikes(@RequestParam("postId") Integer postId){
        boolean isLike = postService.checkLikes(StpUtil.getLoginIdAsInt(), postId);
        return R.ok().put("isLike", isLike);
    }

    @PostMapping("/addLike")
    @Operation(description = "添加一个点赞")
    public R addLike(@Valid @RequestBody AddLikeForm form){
        HashMap params = new HashMap();
        params.put("postId", form.getPostId());
        params.put("userId", StpUtil.getLoginIdAsInt());
        int rows = postService.updateLikes(params);
        int likes = postService.searchLikesById(form.getPostId());
        return R.ok().put("likes", likes);
    }

    @GetMapping("/commentInfoByPostId")
    @Operation(description = "获取评论信息")
    public R searchCommentInfoByPostId(@RequestParam("id") Integer postId){
        HashMap commentInfo = postService.commentInfoByPostId(postId);
        return R.ok().put("commentInfo", commentInfo);
    }

    @PostMapping("/searchMoreComments")
    @Operation(description = "查询更多评论")
    public R searchMoreComments(@Valid @RequestBody SearchMoreCommentsForm form){
        HashMap params = JSONUtil.parse(form).toBean(HashMap.class);
        params.put("start", params.get("start"));
        ArrayList<HashMap> comments = commentService.searchMoreComments(params);
        return R.ok().put("comments", comments).put("commentPageSize", form.getCommentPageSize() + 3);
    }

    @PostMapping("/searchMoreReplies")
    @Operation(description = "查询更多评论回复")
    public R searchMoreReplies(@Valid @RequestBody SearchMoreRepliesForm form){
        HashMap params = JSONUtil.parse(form).toBean(HashMap.class);
        params.put("start", params.get("start"));
        ArrayList<HashMap> replies = commentService.searchMoreReplies(params);
        return R.ok().put("replies", replies).put("replyPageSize", form.getReplyPageSize() + 3);
    }


    @PostMapping("/addComment")
    @Operation(description = "添加评论")
    public R addComment(@Valid @RequestBody AddCommentForm form){
        Comment comment = new Comment();
        comment.setCommentContent(form.getContent());
        comment.setPostId(form.getPostId());
        comment.setUserId(StpUtil.getLoginIdAsInt());
        comment.setIsDelete("no");
        boolean rows = commentService.save(comment);
        // 把这条数据返回给前端显示 comment
        HashMap newComment = commentService.searchCommentById(comment.getId(), comment.getPostId());
        return R.ok().put("newComment", newComment);
    }

    @PostMapping("/addCommentReply")
    @Operation(description = "添加评论回复")
    public R addCommentReply(@Valid @RequestBody AddCommentReplyForm form){
        Comment comment = new Comment();
        comment.setCommentContent(form.getContent());
        comment.setPostId(form.getPostId());
        comment.setUserId(StpUtil.getLoginIdAsInt());
        if(ObjectUtil.isNotNull(form.getReceiveUserId())) {
            comment.setReceiveUserId(form.getReceiveUserId());
        }
        comment.setPrimerId(form.getPrimerId());
        comment.setIsDelete("no");
        boolean rows = commentService.save(comment);
        HashMap newReply = commentService.searchNewReplyById(comment.getId());
        return R.ok().put("newReply", newReply);
    }

    @DeleteMapping("/deletePostById")
    @Operation(description = "删除Post")
    public R deletePostById(@RequestParam("postId") Integer postId){
        int rows = postService.deletePostById(postId);
        return R.ok().put("rows", rows);
    }

    @GetMapping("/isPostDelete")
    @Operation(description = "查看帖子是否删除")
    public R isPostDelete(@RequestParam("postId") Integer postId){
        String isDelete = postService.searchPostDelete(postId);
        return R.ok().put("isDelete", isDelete);
    }
}
