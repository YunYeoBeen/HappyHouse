package com.example.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.dto.Board;

@Mapper
public interface BoardDAO {
    public boolean insertBoard(Board board);

    public Board selectBoard(int boardNo);

    public List<Board> selectBoardList();

    public int updateBoard(Board board);

    public boolean deleteBoard(int boardNo);

    public List<Board> selectBoardListByName(String search_option, String keyword);

    public void readBoard(int no);
    
    public List<Board> selectBoardTop();
    	
}
