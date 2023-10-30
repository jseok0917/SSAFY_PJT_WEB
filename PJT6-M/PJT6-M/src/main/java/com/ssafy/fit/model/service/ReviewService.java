package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.*;

public interface ReviewService {
	// 리뷰 등록
	void writeReview(Review review);

	// 리뷰 조회
	List<Review> getReview(String videoId);

	// 리뷰 수정
	void modifyReview(String videoId, int reviewNo, String content);

	// 리뷰 한개 조회
	Review getOneReview(String videoId, int reviewNo);

	// 리뷰 삭제
	void removeReview(String videoId, int reviewNo);

}
