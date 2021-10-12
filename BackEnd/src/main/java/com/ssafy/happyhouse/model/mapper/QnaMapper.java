package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.PageResultDto;
import com.ssafy.happyhouse.model.dto.QnaDto;

public interface QnaMapper {
	
	void registQna(QnaDto qna);
	
	List<QnaDto> listQna(PageResultDto pageResultDto);
	
	QnaDto getQna(int id);
		
	void modifyQna(QnaDto qna);
	
	void deleteQna(int id);
		
	int selectQnaCount(Map<String, String> map) throws SQLException;	
	
	void registAnswer(QnaDto qna);

	List<QnaDto> listAllQna(Map<String, String> map);
}
