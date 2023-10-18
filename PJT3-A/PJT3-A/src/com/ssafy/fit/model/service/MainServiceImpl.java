package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dao.MainDao;
import com.ssafy.fit.model.dao.MainDaoImpl;
import com.ssafy.fit.model.dto.Video;

public class MainServiceImpl implements MainService {
	
	//데이터가 있어야하므로 DAO가 있어야한다.
		private MainDao mainDao = MainDaoImpl.getInstance();
		
		private MainServiceImpl() {}

		
		//싱글톤 패턴 구현
		private static MainService mainService = new MainServiceImpl();
		
		public static MainService getInstance() {
			return mainService;
		}


		@Override
		public List<Video> getList() {
			return mainDao.selectInterestViewFitVideo();
		}
		
		@Override
		public List<Video> getPartList(String fitPartName) {
			return mainDao.selectPartFitVideo(fitPartName);
		}


		@Override
		public Video getVideo(String youtubeId) {
			
			return mainDao.selectVideo(youtubeId);
		}

	

	
	

}
