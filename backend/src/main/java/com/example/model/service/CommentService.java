package com.example.model.service;

import java.util.List;

import com.example.model.dto.Comment;

public interface CommentService {
    public int commentTotal(int bno);

    public boolean commentInsert(Comment comment);

    public boolean commentDelete(int cno);

    public boolean commentUpdate(Comment comment);

    public List<Comment> commentList(int bno);

    public String getCommentWriter(int cno);

    public int getBnoByCno(int cno);

}
