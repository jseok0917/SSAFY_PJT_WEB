package com.ssafy.fit.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.fit.model.dto.Review;
import com.ssafy.fit.model.dto.Video;

public class ReviewDaoImpl implements ReviewDao {
	
	private List<Video> videoList;
	private Map<String, List<Review>> reviewList = new HashMap<>();

	//싱글톤 패턴 구현
	private static ReviewDao reviewInstance = new ReviewDaoImpl();

	public static  ReviewDao getInstance() {
		return reviewInstance;
	}
	

	@Override
	public List<Review> selectVideoReview(String youtubeId) {
		
		return reviewList.get(youtubeId);
	}


	@Override
	public void registVideoReview(Review review) {
		//영상에 대한 리뷰목록이 없으면 생성하고 넣는다
		if (!(reviewList.containsKey(review.getYoutubeId()))) {
			reviewList.put(review.getYoutubeId(), new ArrayList<Review>());
		}
		reviewList.get(review.getYoutubeId()).add(review);
	}


	@Override
	public void updateVideoReview(Review review) {
		String youtubeId = review.getYoutubeId();
		for (int i = 0; i < reviewList.get(youtubeId).size(); i++) {
			Review rv = reviewList.get(youtubeId).get(i);
			if (rv.getId() == review.getId()) {
				rv.setContent(review.getContent());
				return;
			}
			
		}	
	}


	@Override
	public void deleteVideoReview(String youtubeId, int id) {
		
		
		for (int i = 0; i < reviewList.get(youtubeId).size(); i++) {
			Review rv = reviewList.get(youtubeId).get(i);
			if (rv.getId() == id) {
				reviewList.get(youtubeId).remove(i);
				return;
			}
			
		}	
		
	}


	@Override
	public Review selectReview(String youtubeId, int id) {
		for (int i = 0; i < reviewList.get(youtubeId).size(); i++) {
			Review rv = reviewList.get(youtubeId).get(i);
			if (rv.getId() == id) {
				return rv;
			}
			
		}
		return null;
	}


}
