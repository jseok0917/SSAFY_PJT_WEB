package com.ssafy.fit.model.dto;

import java.util.Date;

public class Review {
	private int no = 1;
	private int id;
	private String writer;
	private String content;
	private String regDate;
	private String youtubeId;
	
	public Review() {}

	public Review(String youtubeId, String writer, String content) {
		super();
		this.id = no++;
		this.youtubeId = youtubeId;
		this.writer = writer;
		this.content = content;
		this.regDate = new Date().toString();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	@Override
	public String toString() {
		return "Review [no=" + no + ", id=" + id + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", youtubeId=" + youtubeId + "]";
	}
	
}
