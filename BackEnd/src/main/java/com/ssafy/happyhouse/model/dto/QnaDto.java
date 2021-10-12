package com.ssafy.happyhouse.model.dto;

public class QnaDto {
	private int id;
	private String title;
	private String content;
	private String writer;
	private String completed;
	private String isprivate;
	private String writetime;
	private String answer;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getIsprivate() {
		return isprivate;
	}
	public void setIsprivate(String isprivate) {
		this.isprivate = isprivate;
	}
	public String getWritetime() {
		return writetime;
	}
	public void setWritetime(String writetime) {
		this.writetime = writetime;
	}
	public QnaDto(int id, String title, String content, String writer, String completed, String isprivate,
			String writetime, String answer) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.completed = completed;
		this.isprivate = isprivate;
		this.writetime = writetime;
		this.answer = answer;
	}
	public QnaDto(String title, String content, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	public QnaDto() {
		super();
	}
}
