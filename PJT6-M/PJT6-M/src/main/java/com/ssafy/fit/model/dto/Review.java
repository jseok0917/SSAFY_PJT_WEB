package com.ssafy.fit.model.dto;

public class Review {
	private String videoId;//영상 ID - 유튜브 ID
	private int reviewNo; //리뷰 번호
	private String userId;
	private String content;
	private String regDate; //리뷰 등록일
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(String videoId, String userId, String content, String regDate) {
		super();
		this.videoId = videoId;
		this.userId = userId;
		this.content = content;
		this.regDate = regDate;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "Review [videoId=" + videoId + ", reviewNo=" + reviewNo + ", userId=" + userId + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
	
	
}
