package com.example.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.dao.BoardDAO;
import com.example.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private BoardDAO boarddao;

    @Autowired
    public void setBoarddao(BoardDAO boarddao) {
        this.boarddao = boarddao;
    }

    @Override
    public boolean insertBoard(Board board) {
        return boarddao.insertBoard(board);
    }

    @Override
    public boolean deleteBoard(int no) {
        return boarddao.deleteBoard(no);
    }

    @Override
    public Board selectBoard(int boardNo) {
        return boarddao.selectBoard(boardNo);
    }

    @Override
    public List<Board> selectBoardList() {
        return boarddao.selectBoardList();
    }

    @Override
    public List<Board> selectBoardListByName(String search_option, String keyword) {
        return boarddao.selectBoardListByName(search_option, keyword);
    }

    @Override
    public boolean updateBoard(Board board) {
        return boarddao.updateBoard(board) == 1;
    }

    @Override
    public void readBoard(int no) {
        boarddao.readBoard(no);

    }

	@Override
	public List<Board> selectBoardTop() {
		return boarddao.selectBoardTop();
	}

}
