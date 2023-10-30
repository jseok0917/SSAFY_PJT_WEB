package com.ssafy.fit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.fit.model.dto.Review;
import com.ssafy.fit.model.dto.SearchCondition;
import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.dto.Video;

public interface VideoDao {
	// 비디오 전체 조회
	List<Video> selectAll();
	
	// 최근 업로드된 비디오 8개 조회
	List<Video> selectLatestVideo();

	// 비디오 부위별 조회
	List<Video> searchVideo(SearchCondition searchCondition);

	// 비디오 등록
	void insertVideo(Video video);

	// 비디오 상세 조회
	Video selectOne(String youtubeId);

	// 비디오 수정
	void updateVideo(Video video);

	// 비디오 삭제
	void deleteVideo(String youtubeId);

	// 조회수 증가
	void updateViewCnt(String youtubeId);


}
