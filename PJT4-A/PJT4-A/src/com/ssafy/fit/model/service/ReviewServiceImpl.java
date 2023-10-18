package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dao.ReviewDao;
import com.ssafy.fit.model.dao.ReviewDaoImpl;
import com.ssafy.fit.model.dto.Review;

public class ReviewServiceImpl implements ReviewService{
	
	//싱글톤 패턴
	 private static ReviewService reviewService =  new ReviewServiceImpl();
	 
	 public static ReviewService getInstance() {
		 return reviewService;
	 }
	
	 //데이터베이스에서 데이터를 받아와야한다.
	 private ReviewDao reviewDao = ReviewDaoImpl.getInstance();
	 

	 //CRUD 구현
	@Override
	public List<Review> getList(String youtubeId) {
		return reviewDao.selectVideoReview(youtubeId);
	}

	@Override
	public void writeReview(Review review) {
		reviewDao.registVideoReview(review);
	}

	@Override
	public void modifyReview(Review review) {
		reviewDao.updateVideoReview(review);
		
	}

	@Override
	public void removeReview(String youtubeId, int id) {
		reviewDao.deleteVideoReview(youtubeId, id);
	}

	@Override
	public Review selectReview(String youtubeId, int id) {
			
		return reviewDao.selectReview(youtubeId, id);
	}
	
}
