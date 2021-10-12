package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;

public interface HouseDealService {

	
	public List<HouseDealDto> listHouseDeal(Map<String, String> map) throws Exception;
	
	public HouseDealDto getHouseDeal(int no) throws Exception;

	List<String> listFavor(String memberId) throws Exception;
	
	int selectFavorCount(String memberId) throws Exception;
	
	void deleteFavor(String memberId, String dong) throws SQLException;

	void insertFavor(String memberId, String dong) throws SQLException;
	
	public HouseDealDto select(int no);

	public List<HouseDealDto> selectAll(String dong);

	public List<HouseDealDto> zzimList(String memberid);

	public void zzimNo(Map<String, String> map);

	public void zzim(Map<String, String> map);

	public int count(int no);
	
	List<CommentDto> selectComment(String aptNo);

    void insertComment(CommentDto comment);
	
    List<HouseDealDto> top();
}
