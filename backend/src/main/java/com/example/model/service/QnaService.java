package com.example.model.service;

import java.util.List;

import com.example.model.dto.Qna;

public interface QnaService {
    public boolean insertQna(Qna qna);

    public List<Qna> selectQnaList();

    public void readQna(int id);

    public boolean deleteQna(int id);

    public boolean updateQna(Qna qna);

    public Qna selectQna(int id);

}
