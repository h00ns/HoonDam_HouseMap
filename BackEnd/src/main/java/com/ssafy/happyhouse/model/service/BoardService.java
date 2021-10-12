package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface BoardService {
	public void registBoard(BoardDto boardDto) throws Exception;
	
	Map<String,Object> listBoard(Map<String, String> map) throws Exception;	
	
	public BoardDto getBoard(int id) throws Exception;
	
	public void modifyBoard(BoardDto boardDto) throws Exception;
	
	public void deleteBoard(int id) throws Exception;

	public List<BoardDto> listAllBoard(Map<String, String> map);

	
}
