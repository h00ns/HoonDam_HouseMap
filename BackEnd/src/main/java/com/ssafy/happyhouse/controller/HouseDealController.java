package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.HouseDealService;

@RestController
@RequestMapping("/housedeal")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class HouseDealController {

	@Autowired
	HouseDealService service;
	/**
	 *  관심 항목 추가
	 * @throws Exception 
	 * */
	@PostMapping
	private List<String> insertFavor(@RequestBody String dong , HttpSession session) throws Exception {
		String memberId = ((MemberDto) session.getAttribute("memberinfo")).getMemberid();
		service.insertFavor(memberId, dong);
		return service.listFavor(memberId);
	}
	/**
	 *  관심 항목 삭제		
	 * @throws Exception 
	 * */
	@DeleteMapping("/{dong}")
	private List<String> deleteFavor(@PathVariable String dong, HttpSession session) throws Exception {
		String memberId = ((MemberDto) session.getAttribute("memberinfo")).getMemberid();
		service.deleteFavor(memberId, dong);
		return service.listFavor(memberId);
	}
	/**
	 *  목록 조회
	 * @throws Exception 
	 * 
	 * */
	@GetMapping
	private List<HouseDealDto> list(@RequestParam String key, @RequestParam String word, @RequestParam String neLa, @RequestParam String neMa, @RequestParam String swLa, @RequestParam String swMa, @RequestParam String dealamount, @RequestParam String area) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("word", word);
		map.put("neLa", neLa);
		map.put("neMa", neMa);
		map.put("swLa", swLa);
		map.put("swMa", swMa);
		map.put("dealamount", dealamount);
		map.put("area",area);
//		Map<String, Object> result = service.listHouseDeal(map);
//		String memberId = ((MemberDto) session.getAttribute("memberinfo")).getMemberid();
//		result.put("flist", service.listFavor(memberId));
		return service.listHouseDeal(map);
	}
	
	// 검색하기
	@GetMapping("/search/{dong}")
	private List<HouseDealDto> selectAll(@PathVariable String dong){
		return service.selectAll(dong);
	}
	
	// 하나 가져오기
	@GetMapping("/select/{no}")
	private HouseDealDto select(@PathVariable int no) {
	    return service.select(no);
	}
	
	@GetMapping("/instagram")
	private  Map<String, Object> instagram(@RequestParam String key) {
	    System.out.println("인스타그램");
	     Map<String, Object> resultM = new HashMap<String, Object>();
	        try {
	            String apiURL = "https://www.instagram.com/explore/tags/nike/?__a=1";
	            System.out.println("유알엘");
	            System.out.println(apiURL);

	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	           
	            con.setRequestMethod("GET");
	            int responseCode = con.getResponseCode();
	            BufferedReader br = null;
	            if(responseCode==200) { // 정상 호출
	                
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            System.out.println(br.toString());
	            String inputLine;
	            StringBuffer res = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                res.append(inputLine);
	            }
	          

	            resultM.put("OUT_DATA", res);
	            
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return resultM;
	}
	// 찜목록
	@GetMapping("/zzim/{memberid}")
	private List<HouseDealDto> zzimList(@PathVariable String memberid){
		return service.zzimList(memberid);
	}
	// 찜하기
	@PostMapping("/zzim")
	private List<HouseDealDto> zzim(@RequestBody Map<String, String> map) {
		service.zzim(map);
		return service.zzimList(map.get("memberid"));
	}
	// 찜취소
	@DeleteMapping("/zzim")
	private List<HouseDealDto> zzimNo(@RequestParam String memberid, @RequestParam String no) {
		Map<String, String> map = new HashMap<String, String>();
	    map.put("memberid", memberid);
	    map.put("no", no);
	    service.zzimNo(map);
		return service.zzimList(memberid);
	}
	// 좋아요 개수
	@GetMapping("/count/{no}")
	private int count(@PathVariable int no) {
		return service.count(no);
	}
	
	@GetMapping("/comment")
	private List<CommentDto> selectComment(@RequestParam String aptno) {
	    return service.selectComment(aptno);
	}

	@PostMapping("/comment")
	private List<CommentDto> insertComment(@RequestBody CommentDto commentDto) {
	    service.insertComment(commentDto);
	    System.out.println(commentDto.getAptno());
	    System.out.println(service.selectComment(commentDto.getAptno()));
	    
	    return service.selectComment(commentDto.getAptno());
	}
	
	@GetMapping("/top")
	private List<HouseDealDto> top(){
		return service.top();
	}
}