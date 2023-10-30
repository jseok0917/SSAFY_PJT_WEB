package com.ssafy.fit.model.dto;

public class Basket {
	private String userId;
	private String youtubeId;
	
	public Basket() {
	
	}

	public Basket(String userId, String youtubeId) {
		super();
		this.userId = userId;
		this.youtubeId = youtubeId;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	@Override
	public String toString() {
		return "Basket [userId=" + userId + ", youtubeId=" + youtubeId + "]";
	}

}
