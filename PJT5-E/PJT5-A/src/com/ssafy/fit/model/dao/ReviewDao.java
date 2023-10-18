package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.dto.Review;

public interface ReviewDao {
	
	//리뷰 전체 조회
	List<Review> selectVideoReview(String youtubeId);
	
	//리뷰 등록
	void registVideoReview(Review review);
	
	//리뷰 수정
	void updateVideoReview(Review review);
	
	//리뷰 삭제
	void deleteVideoReview(String youtubeId, int id);
	
	Review selectReview(String youtubeId, int id);
	
	

}
