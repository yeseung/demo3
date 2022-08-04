package com.gongdaeoppa.demo3.service;

import org.springframework.stereotype.Service;

import com.gongdaeoppa.demo3.repository.BoardRepository;
import com.gongdaeoppa.demo3.vo.Board;

@Service
public class BoardService {
	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}
}