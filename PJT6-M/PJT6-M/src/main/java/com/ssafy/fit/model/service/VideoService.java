package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.SearchCondition;
import com.ssafy.fit.model.dto.Video;

public interface VideoService {
	// 비디오 전체 조회
	List<Video> getList();
	
	List<Video> getLatestList();

	// 비디오 부위별 조회
	List<Video> searchVideo(SearchCondition searchCondition);

	// 비디오 등록
	void writeVideo(Video video);

	// 비디오 상세 조회
	Video getVideo(String youtubeId);

	// 비디오 수정
	void modifyVideo(Video video);

	// 비디오 삭제
	void removeVideo(String youtubeId);

}
