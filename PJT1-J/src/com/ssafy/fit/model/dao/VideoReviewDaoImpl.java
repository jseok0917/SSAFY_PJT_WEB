package com.ssafy.fit.model.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.fit.model.VideoReview;

public class VideoReviewDaoImpl implements VideoReviewDao {
	
	private static int reviewNo;
	
	//영상 리뷰를 관리하기 위한 Db 필드 변수 생성
	private Map<Integer, List<VideoReview>> videoReviewDb = new HashMap<>();
	
	
	
	
	//싱글톤 패턴 구현
	private static VideoReviewDaoImpl instance = new VideoReviewDaoImpl();
	
	private VideoReviewDaoImpl() {}
	
	public static VideoReviewDaoImpl getinstance() {
		return instance;
	}

	// 리뷰를 리스트에 삽입하는 메서드 구현
	@Override
	public int insertReview(VideoReview videoReview) {
		
		//videoReviewDb 필드 변수가 생성될 때,
		//videoReviewDb가 null이라면
		//value값으로 ArrayList가 존재하질 않아
		//Exception오류가 뜨므로
		//Map클래스의 getOrDefault메서드를 사용한다.
		List<VideoReview> list = videoReviewDb.getOrDefault(videoReview.getVideoNo(), new ArrayList<>());
		list.add(videoReview);
		videoReviewDb.put(videoReview.getVideoNo(), list);
		return list.size();
		
		
	}
	// 리뷰 선택(영상 번호 선택 시, 해당 영상에 대한 리뷰 목록을 보여줌)
	
	@Override
	public List<VideoReview> selectReview(int videoNo) {
		return videoReviewDb.get(videoNo);
	}
	
	

	
	
	
	

}
