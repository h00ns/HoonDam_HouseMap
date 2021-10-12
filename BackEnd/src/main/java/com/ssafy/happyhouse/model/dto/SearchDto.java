package com.ssafy.happyhouse.model.dto;

public class SearchDto {
	private String key;
	private String word;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public SearchDto(String key, String word) {
		super();
		this.key = key;
		this.word = word;
	}
	public SearchDto() {
		super();
	}
}
