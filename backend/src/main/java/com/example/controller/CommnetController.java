package com.example.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.Comment;
import com.example.model.dto.User;
import com.example.model.service.CommentService;

import ch.qos.logback.core.util.SystemInfo;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommnetController {

    @Resource
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{bno}")
    private ResponseEntity<?> commentList(@PathVariable int bno) {
        commentService.commentTotal(bno);
        List<Comment> comment = commentService.commentList(bno);
        return new ResponseEntity<List<Comment>>(comment, HttpStatus.OK);
    }

    @GetMapping("/count/{bno}")
    private ResponseEntity<?> commentCount(@PathVariable int bno) {
        int count = commentService.commentTotal(bno);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> commentInsert(@RequestBody Comment comment) {
        Comment c = Comment.builder().bno(comment.getBno()).content(comment.getContent()).writer(comment.getWriter())
                .build();
        System.out.println(c.toString());
        commentService.commentInsert(c);
        // 오류나면 이쪽으로
        List<Comment> list = commentService.commentList(c.getBno());
        return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
    }

    @PutMapping("/{cno}")
    private ResponseEntity<?> commentUpdate(@RequestBody Comment comment, @PathVariable("cno") int cno) {
        comment.setCno(cno);
        commentService.commentUpdate(comment);
        List<Comment> list = commentService.commentList(comment.getBno());
        return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{cno}")
    private ResponseEntity<?> commentDelete(@PathVariable int cno) {
        String commentWriter = commentService.getCommentWriter(cno);
        int bno = commentService.getBnoByCno(cno);
        commentService.commentDelete(cno);
        List<Comment> list = commentService.commentList(bno);
        return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
    }

}
