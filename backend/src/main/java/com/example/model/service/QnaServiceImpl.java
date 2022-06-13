package com.example.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.dao.QnaDAO;
import com.example.model.dto.Qna;

@Service
public class QnaServiceImpl implements QnaService {

    @Resource
    private QnaDAO qnadao;

    @Autowired
    public void setQnadao(QnaDAO qnadao) {
        this.qnadao = qnadao;
    }

    @Override
    public boolean insertQna(Qna qna) {
        return qnadao.insertQna(qna);
    }

    @Override
    public List<Qna> selectQnaList() {
        return qnadao.selectQnaList();
    }

    @Override
    public void readQna(int id) {
        qnadao.readQna(id);
    }

    @Override
    public boolean deleteQna(int id) {
        return qnadao.deleteQna(id);
    }

    @Override
    public boolean updateQna(Qna qna) {
        return qnadao.updateQna(qna) == 1;
    }

    @Override
    public Qna selectQna(int id) {
        return qnadao.selectQna(id);
    }


}
