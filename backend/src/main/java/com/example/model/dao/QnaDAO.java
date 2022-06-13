package com.example.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.dto.Qna;

@Mapper
public interface QnaDAO {

    public boolean insertQna(Qna qna);

    public boolean deleteQna(int id);

    public int updateQna(Qna qna);

    public List<Qna> selectQnaList();

    public Qna selectQna(int id);

    public void readQna(int id);


}
