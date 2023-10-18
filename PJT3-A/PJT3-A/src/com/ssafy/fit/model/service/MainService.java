package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.Video;

public interface MainService {
	
	// 게시글 전체 조회
	List<Video> getList();

	// 게시글 상세 조회
	Video getVideo(String youtubeId);
	
	List<Video> getPartList(String fitPartName);
	
	
}
