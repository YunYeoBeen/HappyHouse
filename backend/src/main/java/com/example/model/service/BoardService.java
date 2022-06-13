package com.example.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.dto.Board;


public interface BoardService {
    public boolean insertBoard(Board board);

    public boolean deleteBoard(int no);

    public boolean updateBoard(Board board);

    public Board selectBoard(int boardNo);

    public List<Board> selectBoardList();

    public List<Board> selectBoardListByName(String search_option, String keyword);

    public void readBoard(int no);
    
    public List<Board> selectBoardTop();
}
