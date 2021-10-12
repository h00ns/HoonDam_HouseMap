package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.PageResultDto;


public interface BoardMapper {
	public void registBoard(BoardDto boardDto) throws SQLException;
	
	public List<BoardDto> listBoard(PageResultDto pageResultDto) throws SQLException;
	
	public BoardDto getBoard(int id) throws SQLException;
	
	public void modifyBoard(BoardDto boardDto) throws SQLException;
	
	public void deleteBoard(int id) throws SQLException;
	
	int selectBoardCount(Map<String, String> map) throws SQLException;

	public List<BoardDto> listAllBoard(Map<String, String> map);

}

