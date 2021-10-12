package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.PageResultDto;
import com.ssafy.happyhouse.model.dto.SearchDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper mapper;
	
	@Override
	public void registBoard(BoardDto boardDto) throws Exception {
		mapper.registBoard(boardDto);
	}

	@Override
	public Map<String, Object> listBoard(Map<String, String> map) throws Exception {
		int count = mapper.selectBoardCount(map);
		PageResultDto prd = new PageResultDto(Integer.parseInt(map.get("pageNo")), count, 10);
		prd.setKey(map.get("key"));
		prd.setWord(map.get("word"));
		List<BoardDto> list = mapper.listBoard(prd);
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("pageResult", prd);
		return result;
	}

	@Override
	public BoardDto getBoard(int id) throws Exception {
		return mapper.getBoard(id);
	}

	@Override
	public void modifyBoard(BoardDto boardDto) throws Exception {
		mapper.modifyBoard(boardDto);
	}

	@Override
	public void deleteBoard(int id) throws Exception {
		mapper.deleteBoard(id);
	}

	@Override
	public List<BoardDto> listAllBoard(Map<String, String> map) {
		return mapper.listAllBoard(map);
	}



}
