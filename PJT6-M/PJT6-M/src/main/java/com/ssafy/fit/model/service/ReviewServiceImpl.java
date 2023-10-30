package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.ReviewDao;
import com.ssafy.fit.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	// 의존성 주입
	@Autowired
	private ReviewDao dao;

	@Override
	public void writeReview(Review review) {
		dao.insertReview(review);
	}

	@Override
	public List<Review> getReview(String videoId) {
		return dao.selectReview(videoId);
	}

	@Override
	public Review getOneReview(String videoId, int reviewNo) {
		return dao.selectOneReview(videoId, reviewNo);

	}

	@Override
	public void modifyReview(String videoId, int reviewNo, String content) {
		dao.updateReview(videoId, reviewNo, content);

	}

	@Override
	public void removeReview(String videoId, int reviewNo) {
		dao.deleteReview(videoId, reviewNo);

	}

}
