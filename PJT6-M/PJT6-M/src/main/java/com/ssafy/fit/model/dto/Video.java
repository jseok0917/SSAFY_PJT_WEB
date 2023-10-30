package com.ssafy.fit.model.dto;

import java.util.Date;

public class Video {
	private String title;
	private String fitPartName;
	private String youtubeId;
	private String channelName;
	private String date;
	
	private int viewCnt;

	public Video() {
		// TODO Auto-generated constructor stub
	}

	public Video(String youtubeId, String channelName, String fitPartName, String title, String date) {
		super();
		this.youtubeId = youtubeId;
		this.channelName = channelName;
		this.fitPartName = fitPartName;
		this.title = title;
		this.date = date;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFitPartName() {
		return fitPartName;
	}

	public void setFitPartName(String fitPartName) {
		this.fitPartName = fitPartName;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Video [title=" + title + ", fitPartName=" + fitPartName + ", youtubeId=" + youtubeId + ", channelName="
				+ channelName + ", viewCnt=" + viewCnt + "]";
	}

}
