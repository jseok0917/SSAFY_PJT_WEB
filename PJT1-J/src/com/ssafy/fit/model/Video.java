package com.ssafy.fit.model;


public class Video {
	
	//영상 클래스의 필드정보
	//캡슐화를 위한 private 접근제어자 지정
	private int no;
	private String title;
	private String part;
	private String url;
	
	
	//기본생성자
	public Video() {
		super();
	}
	
	
	//필드정보를 통한 생성자
	public Video(int no, String title, String part, String url) {
		super();
		this.no = no;
		this.title = title;
		this.part = part;
		this.url = url;
	}

	//getter와 setter를 이용한 캡슐화
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	//객체의 정보를 읽어올 수 있도록
	//toString메서드 오버라이드
	@Override
	public String toString() {
		return "Video [no=" + no + ", title=" + title + ", part=" + part + ", url=" + url + "]";
	}
}
