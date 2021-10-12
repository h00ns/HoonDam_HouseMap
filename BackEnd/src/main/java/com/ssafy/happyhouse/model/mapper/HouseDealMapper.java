package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;

public interface HouseDealMapper {

	public List<HouseDealDto> listHouseDeal(Map<String, String> map) throws SQLException;
	
	public HouseDealDto getHouseDeal(int no) throws SQLException;

	int selectHouseCount(Map<String, String> map) throws SQLException;
	
	List<String> listFavor(String memberId) throws SQLException;
	
	int selectFavorCount(String memberId) throws SQLException;
	
	void deleteFavor(Map<String, String> map) throws SQLException;

	void insertFavor(Map<String, String> map) throws SQLException;
	
	public HouseDealDto select(int no);

	public List<HouseDealDto> selectAll(String dong);

	public List<HouseDealDto> zzimList(String memberid);

	public void zzim(Map<String, String> map);

	public void zzimNo(Map<String, String> map);

	public int count(int no);
	
	List<CommentDto> selectComment(String aptNo);

	void insertComment(CommentDto comment);
	
	List<HouseDealDto> top();
}
