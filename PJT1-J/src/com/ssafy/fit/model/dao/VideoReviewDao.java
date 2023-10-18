package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.VideoReview;

public interface VideoReviewDao {
	
	//VideoReviewDaoImpl클래스를 위한 interface
	
	//리뷰 삽입기능 구현이 필요하다
	int insertReview(VideoReview videoReview);
	
	//특정 영상을 골랐을 때, 해당 영상의 모든 리뷰 리스트를 반환하는 메서드가 필요하다.
	List<VideoReview> selectReview(int videoNo);
	

}
