package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.QnaDto;

public interface QnaService {

	
	public void registQna(QnaDto qna) throws Exception;
	
	Map<String,Object> listQna(Map<String, String> map) throws Exception;	
	
	public QnaDto getQna(int id) throws Exception;
	
	public void modifyQna(QnaDto qna) throws Exception;
	
	public void deleteQna(int id) throws Exception;
	
	public void registAnswer(QnaDto qna);

	public List<QnaDto> listAllQna(Map<String, String> map);
}
