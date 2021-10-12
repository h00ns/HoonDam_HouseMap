package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	HouseDealMapper mapper;

	@Override
	public List<HouseDealDto> listHouseDeal(Map<String, String> map) throws Exception {
		int count = mapper.selectHouseCount(map);
		return mapper.listHouseDeal(map);
	}

	@Override
	public HouseDealDto getHouseDeal(int no) throws Exception {
		return mapper.getHouseDeal(no);
	}

	@Override
	public List<String> listFavor(String memberId) throws Exception {
		return mapper.listFavor(memberId);
	}

	@Override
	public void deleteFavor(String memberId, String dong) throws SQLException {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("dong", dong);
		mapper.deleteFavor(map);
	}

	@Override
	public void insertFavor(String memberId, String dong) throws SQLException {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("dong", dong);
		mapper.insertFavor(map);
	}

	@Override
	public int selectFavorCount(String memberId) throws Exception {
		return 0;
	}

	@Override
	public HouseDealDto select(int no) {
		return mapper.select(no);
	}

	@Override
	public List<HouseDealDto> selectAll(String dong) {
		return mapper.selectAll(dong);
	}

	// 찜목록
	@Override
	public List<HouseDealDto> zzimList(String memberid) {
		return mapper.zzimList(memberid);
	}

	// 찜하기
	@Override
	public void zzim(Map<String, String> map) {
		mapper.zzim(map);
	}
	
	// 찜취소
	@Override
	public void zzimNo(Map<String, String> map) {
		mapper.zzimNo(map);
	}

	@Override
	public int count(int no) {
		return mapper.count(no);
	}
	
	@Override
	public List<CommentDto> selectComment(String aptNo) {
	    return mapper.selectComment(aptNo);
	}

	@Override
	public void insertComment(CommentDto comment) {
	    mapper.insertComment(comment);
	}

	@Override
	public List<HouseDealDto> top() {
		return mapper.top();
	}
}
