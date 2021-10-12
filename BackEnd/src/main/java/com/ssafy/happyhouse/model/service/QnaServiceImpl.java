package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.PageResultDto;
import com.ssafy.happyhouse.model.dto.QnaDto;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	QnaMapper mapper;

	@Override
	public void registQna(QnaDto qna) throws Exception {
		mapper.registQna(qna);
	}

	@Override
	public Map<String, Object> listQna(Map<String, String> map) throws Exception {
		int count = mapper.selectQnaCount(map);
		PageResultDto prd = new PageResultDto(Integer.parseInt(map.get("pageNo")), count, 10);
		prd.setKey(map.get("key"));
		prd.setWord(map.get("word"));
		List<QnaDto> list = mapper.listQna(prd);
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("pageResult", prd);
		return result;
	}

	@Override
	public QnaDto getQna(int id) throws Exception {
		return mapper.getQna(id);
	}

	@Override
	public void modifyQna(QnaDto qna) throws Exception {
		mapper.modifyQna(qna);
	}

	@Override
	public void deleteQna(int id) throws Exception {
		mapper.deleteQna(id);
	}

	@Override
	public void registAnswer(QnaDto qna) {
		qna.setCompleted("true");
		mapper.registAnswer(qna);
	}

	@Override
	public List<QnaDto> listAllQna(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.listAllQna(map);
	}

}
