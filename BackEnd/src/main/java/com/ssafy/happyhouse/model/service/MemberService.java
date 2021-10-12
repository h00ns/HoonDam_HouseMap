package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.MemberDto;

public interface MemberService {
	
	// 로그인
	public MemberDto login(MemberDto memberDto) throws Exception;
	
	// 회원가입
	public void signUp(MemberDto memberDto);

	// 회원조회
	public MemberDto select(String id);

	// 회원탈퇴
	public void delete(String memberid);
	
	// 회원수정
	public void update(MemberDto memberDto);

	// 회원목록
	public List<MemberDto> selectAll();
	
}
