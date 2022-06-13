package com.example.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.dao.CommentDAO;
import com.example.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDAO commentdao;

    @Autowired
    public void setCommentdao(CommentDAO commentdao) {
        this.commentdao = commentdao;
    }

    @Override
    public int commentTotal(int bno) {
        // TODO Auto-generated method stub
        return commentdao.commentTotal(bno);
    }

    @Override
    public boolean commentInsert(Comment comment) {
        // TODO Auto-generated method stub
        return commentdao.commentInsert(comment);
    }

    @Override
    public boolean commentDelete(int cno) {
        // TODO Auto-generated method stub
        return commentdao.commentDelete(cno);
    }

    @Override
    public boolean commentUpdate(Comment comment) {
        // TODO Auto-generated method stub
        return commentdao.commentUpdate(comment) == 1;
    }

    @Override
    public List<Comment> commentList(int bno) {
        // TODO Auto-generated method stub
        return commentdao.commentList(bno);
    }

    @Override
    public String getCommentWriter(int cno) {
        return commentdao.getCommentWriter(cno);
    }

    @Override
    public int getBnoByCno(int cno) {
        return commentdao.getBnoByCno(cno);
    }


}
