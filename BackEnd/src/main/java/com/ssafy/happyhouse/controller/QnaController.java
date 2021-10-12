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

import com.ssafy.happyhouse.model.dto.QnaDto;
import com.ssafy.happyhouse.model.service.QnaService;


@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/qna")
public class QnaController {

	
	@Autowired
	QnaService service;
	@GetMapping("/all")
	public List<QnaDto> selectAll(@RequestParam String key, @RequestParam String word) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("word", word);
		return service.listAllQna(map);
	}
	
	/**
	 * 전체 조회
	 * */
	
	/**
	 * 추가
	 * @throws Exception 
	 * return 처음 화면 데이터
	 * */
	@PostMapping
	public List<QnaDto> insertQna(@RequestBody QnaDto qna) throws Exception{
		service.registQna(qna);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", qna.getKey());
		map.put("word", qna.getWord());
		return service.listAllQna(map);
	}
	
	/**
	 * 수정
	 * @throws Exception 
	 * return 수정된  상세 화면 데이터
	 * */
	@PutMapping
	public QnaDto updateQna(@RequestBody QnaDto qna) throws Exception{
		service.modifyQna(qna);
		return service.getQna(qna.getId());
	}
	
	/**
	 * 삭제
	 * return 초기 화면 데이터
	 * */
	@DeleteMapping
	public List<QnaDto> deleteTodo(@RequestParam int id, @RequestParam String key, @RequestParam String word) throws Exception{
		service.deleteQna(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("word", word);

		return service.listAllQna(map);
	}
	
	/**
	 * 상세 화면
	 * return 상세 화면 데이터
	 * */
	@GetMapping("/detail")
	public QnaDto detail(@RequestParam int id) throws Exception {
		return service.getQna(id);
	}
	
	/**
	 * 답변 등록
	 * 
	 * 
	 * */
	@PutMapping("/answer")
	public QnaDto answerQna(@RequestBody QnaDto qna) throws Exception{
		service.registAnswer(qna);
		return service.getQna(qna.getId());
	}
	
}
