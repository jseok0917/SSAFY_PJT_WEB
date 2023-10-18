package com.ssafy.fit.model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ssafy.fit.model.dto.Review;
import com.ssafy.fit.model.dto.Video;

public class MainDaoImpl implements MainDao {
//	//디버깅용
//	public static void main(String[] args) {
//		List<Video> list = mainInstance.selectAllVideo();
//		
//		
//		mainInstance.divideList(0, sortedList.size()-1);
//		
//		
//		
//		for (Video v : list) {
//			System.out.println(v.toString());
//		}
//		
//		System.out.println("===============");
//		for (Video v : sortedList) {
//			System.out.println(v.toString());
//		}
//		
//		
//	}

	private List<Video> videoList;
	private List<Review> reviewList;

	private static MainDao mainInstance = new MainDaoImpl();

	public static MainDao getInstance() {
		return mainInstance;
	}

	private MainDaoImpl() {
		videoList = new ArrayList<>();
		videoList.add(new Video("gMaB-fG4u4g", "전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]", "전신", "ThankyouBUBU", 27));
		videoList.add(new Video("swRNeYw1JkY", "하루 15분! 전신 칼로리 불태우는 다이어트 운동", "전신", "ThankyouBUBU", 15));
		videoList.add(new Video("54tTYO-vU2E", "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]", "상체", "ThankyouBUBU", 36));
		videoList.add(new Video("QqqZH3j_vH0", "상체비만 다이어트 최고의 운동 [상체 핵매운맛]", "상체", "ThankyouBUBU", 11));
		videoList.add(new Video("tzN6ypk6Sps", "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]", "하체", "김강민", 12));
		videoList.add(new Video("u5OgcZdNbMo", "저는 하체 식주의자 입니다", "하체", "GYM종국", 3));
		videoList.add(new Video("PjGcOP-TQPE", "11자복근 복부 최고의 운동 [복근 핵매운맛]", "복부", "ThankyouBUBU", 1));
		videoList.add(new Video("7TLk7pscICk", "(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)", "복부", "SomiFit", 2));

		reviewList = new ArrayList<>();
	}

	public Video selectVideo(String youtubeId) {
		for (int i = 0; i < videoList.size(); i++) {
			Video video = videoList.get(i);
			if (video.getYoutubeId().equals(youtubeId)) {
				return video;
			}

		}

		return null;
	}

	public List<Video> selectAllVideo() {
		return videoList;
	}

	// 조회수 순으로
	// 나오도록 구현
	@Override
	public List<Video> selectInterestViewFitVideo() {
		List<Video> sortedList = new ArrayList<>();
		// 병합정렬을위해 깊은 복사 진행
		for (Video v : mainInstance.selectAllVideo()) {
			sortedList.add(v);
		}
		
		tempList.clear();
		for (int i = 0; i < videoList.size(); i++) {
				tempList.add(null);
		}
	
		
		mainInstance.divideList(0, sortedList.size() - 1, sortedList);

		return sortedList;
	}

	// 병합정렬을 이용하면 메모리가 많이 먹을지도..
	private static List<Video> tempList = new ArrayList<>();

	// 우선 병합정렬을 통해 조회순 정렬을 구현
	// 실제로는 정렬된 리스트를 heap구조로 하나 더 만들어놓던가
	// 삽입할 때마다 이진탐색을 통한 정렬을 구현하면 될 것 같다.
	public void divideList(int L, int R, List<Video> sortedList) {

		if (L < R) {

			int M = (L + R) / 2;
			divideList(L, M, sortedList);
			divideList(M + 1, R, sortedList);
			mergeList(L, M, R, sortedList);
		}
	}


	public void mergeList(int left, int mid, int right, List<Video> sortedList) {
		int L = left;
		int R = mid + 1;
		int idx = left;

		// 모두 정렬한 후
		while (L <= mid && R <= right) {
			Video v1 = sortedList.get(L);
			Video v2 = sortedList.get(R);

			// 순서가 그대로라면
			// 내림차순 정렬을 해야한다.
			if (v1.getViewCnt() >= v2.getViewCnt()) {
				tempList.set(idx++, v1);
				L++;
				// v2가 작다면
			} else {
				tempList.set(idx++, v2);
				R++;
			}

		}

		// 남은 것들을 다 넣어버린다
		while (L <= mid) {
			Video v = sortedList.get(L);
			tempList.set(idx++, v);
			L++;
		}

		while (R <= right) {
			Video v = sortedList.get(R);
			tempList.set(idx++, v);
			R++;
		}
		

		for (int i = left; i <= right; i++) {
			sortedList.set(i, tempList.get(i));
		}

	}

	// 부위별로
	@Override
	public List<Video> selectPartFitVideo(String fitPartName) {
		List<Video> partFitVideo = new ArrayList<>();

		for (int i = 0; i < videoList.size(); i++) {
			Video v = videoList.get(i);
			if (v.getFitPartName().equals(fitPartName)) {
				partFitVideo.add(v);
			}

		}

		return partFitVideo;
	}

}