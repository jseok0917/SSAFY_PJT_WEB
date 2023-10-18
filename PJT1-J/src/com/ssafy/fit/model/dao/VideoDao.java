package com.ssafy.fit.model.dao;

import java.util.List;
import com.ssafy.fit.model.Video;

public interface VideoDao {
	/**
	 * Video의 전체 리스트를 반환
	 * 
	 * @return Video배열 반환
	 * */
	List<Video> selectVideo();
	
	/**
	 * no에 해당하는 Video를 반환
	 * @param Video에 해당하는 no
	 * 
	 * @return 해당 no에 해당하는 Video 정보
	 * */
	Video selectVideoByNO(int no);
}
