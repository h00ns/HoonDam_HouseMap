package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper mapper;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return mapper.login(memberDto);
	}

	@Override
	public void signUp(MemberDto memberDto) {
		mapper.signUp(memberDto);
	}

	@Override
	public MemberDto select(String id) {
		return mapper.select(id);
	}

	@Override
	public void delete(String memberid) {
		mapper.delete(memberid);
	}

	@Override
	public void update(MemberDto memberDto) {
		mapper.update(memberDto);
	}

	@Override
	public List<MemberDto> selectAll() {
		return mapper.selectAll();
	}
	
}
