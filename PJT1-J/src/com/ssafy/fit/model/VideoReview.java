package com.ssafy.fit.model;

public class VideoReview {
	
	//영상리뷰 클래스의 필드정보
	//캡슐화를 위한 private 접근제어자 지정
	private int videoNo;
	private int reviewNo;
	private String nickName;
	private String content;
	
	//기본생성자
	public VideoReview() {
		super();
	}

	//생성자
	public VideoReview(int videoNo, int reviewNo, String nickName, String content) {
		super();
		this.videoNo = videoNo;
		this.reviewNo = reviewNo;
		this.nickName = nickName;
		this.content = content;
	}

	
	//getter와 setter를 통한 캡슐화
	public int getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(int videoNo) {
		this.videoNo = videoNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	//객체의 정보를 읽어올 수 있도록
	//toString메서드 오버라이드
	@Override
	public String toString() {
		return "VideoReview [videoNo=" + videoNo + ", reviewNo=" + reviewNo + ", nickName=" + nickName + ", content="
				+ content + "]";
	}
	
	
	
	

}
