package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.service.BoardService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/board")
public class BoardController {
		
@Autowired
BoardService service;



/**
 * 전체 조회 
 * */

@GetMapping("/all")
private List<BoardDto> selectAll2(@RequestParam String key, @RequestParam String word) throws Exception {
    Map<String, String> map = new HashMap<String, String>();
    map.put("key", key);
    map.put("word", word);
    return service.listAllBoard(map);
}

	
	/**
	 * 상세보기 
	 * */
	@GetMapping("/detail")
	private BoardDto getBoard(@RequestParam int id) throws Exception {
	    return service.getBoard(id);
	}
	
	
	/**
	 * 등록 
	 * */
	@PostMapping
	public List<BoardDto> write(@RequestBody BoardDto boardDto) throws Exception {
		service.registBoard(boardDto);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", boardDto.getKey());
		map.put("word", boardDto.getWord());
		return service.listAllBoard(map);
	}
	
	/**
	 * 수정 
	 * */
	@PutMapping
	public BoardDto updateBoard(@RequestBody BoardDto boardDto) throws Exception {
		service.modifyBoard(boardDto);
		return service.getBoard(boardDto.getId());
	}
		
	
	/**
	 * 삭제 
	 * */
	@DeleteMapping
	public List<BoardDto> deleteBoard(@RequestParam int id, @RequestParam String key, @RequestParam String word) throws Exception {
		service.deleteBoard(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("word", word);
		return service.listAllBoard(map);
		
	}

}
