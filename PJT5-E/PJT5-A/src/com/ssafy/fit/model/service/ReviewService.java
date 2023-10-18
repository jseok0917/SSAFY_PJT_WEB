package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.Review;

public interface ReviewService {
	
	// 리뷰 전체 조회
	List<Review> getList(String youtubeId);
	
	//리뷰 선택
	Review selectReview(String youtubeId, int id);

	// 리뷰 등록
	void writeReview(Review review);

	// 리뷰 수정
	void modifyReview(Review review);

	// 리뷰 삭제
	void removeReview(String youtubeId, int id);
	

}
