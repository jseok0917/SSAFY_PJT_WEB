package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.VideoDao;
import com.ssafy.fit.model.dto.SearchCondition;
import com.ssafy.fit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	// 의존성 주입
	@Autowired
	private VideoDao dao;

	@Override
	public List<Video> getList() {
		return dao.selectAll();
	}
	
	@Override
	public List<Video> getLatestList() {
		return dao.selectLatestVideo();
	}

	@Override
	public void writeVideo(Video video) {
		dao.insertVideo(video);

	}

	@Override
	public Video getVideo(String youtubeId) {
		dao.updateViewCnt(youtubeId);
		return dao.selectOne(youtubeId);
	}

	@Override
	public void modifyVideo(Video video) {
		dao.updateVideo(video);

	}

	@Override
	public void removeVideo(String youtubeId) {
		dao.deleteVideo(youtubeId);

	}

	@Override
	public List<Video> searchVideo(SearchCondition searchCondition) {
		return dao.searchVideo(searchCondition);
	}

}
