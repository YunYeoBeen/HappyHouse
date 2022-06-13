package com.example.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.dto.Comment;

@Mapper
public interface CommentDAO {

    public int commentTotal(int bno);

    public boolean commentInsert(Comment comment);

    public boolean commentDelete(int cno);

    public int commentUpdate(Comment comment);

    public List<Comment> commentList(int bno);

    public String getCommentWriter(int cno);

    public int getBnoByCno(int cno);


}
