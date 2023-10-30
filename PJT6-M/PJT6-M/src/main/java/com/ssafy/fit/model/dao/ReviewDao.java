package com.ssafy.fit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.fit.model.dto.Review;

public interface ReviewDao {

	// 리뷰 등록
	void insertReview(Review review);

	// 리뷰 조회
	List<Review> selectReview(String videoId);

	// 리뷰 한개 조회
	Review selectOneReview(@Param("videoId") String videoId,@Param("reviewNo") int reviewNo);
 
	// 리뷰 수정
	void updateReview(@Param("videoId")String videoId, @Param("reviewNo") int reviewNo, @Param("content") String content);

	// 리뷰 삭제
	void deleteReview(@Param("videoId")String videoId, @Param("reviewNo") int reviewNo);

}
