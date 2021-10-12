package com.ssafy.happyhouse.model.dto;

public class PageResultDto {
	private int pageNo;
	private int count;
	private int listSize;
	private int blockSize;
	private int beginPage;
	private int endPage;
	private int begin;
	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

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

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	private boolean prev, next;
	private String key;
	private String word;

	public PageResultDto(int pageNo, int count) {
		this(pageNo, count, 10, 10);
		
	}

	public PageResultDto(int pageNo, int count, int listSize) {
		this(pageNo, count, listSize, 10);
	}

	public PageResultDto(int pageNo, int count, int listSize, int blockSize) {
		this.pageNo = pageNo;
		this.count = count;
		this.listSize = listSize;
		this.blockSize = blockSize;
		this.begin = listSize * (pageNo - 1);
		calculator();
	}

	private void calculator() {
		int lastPage = (int) Math.ceil(count / (double) listSize);
		// 화면 하단 페이지 블럭 구하기
		int currentBlock = (int) Math.ceil(pageNo / (double) blockSize);
		beginPage = (currentBlock - 1) * blockSize + 1;
		endPage = Math.min(currentBlock * blockSize, lastPage);
		// 이전 페이지와 다음 페이지 설정하기
		prev = beginPage != 1;
		next = endPage != lastPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getCount() {
		return count;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean getPrev() {
		return prev;
	}

	public boolean getNext() {
		return next;
	}
	
	public int getListSize() {
		return listSize;
	}
}