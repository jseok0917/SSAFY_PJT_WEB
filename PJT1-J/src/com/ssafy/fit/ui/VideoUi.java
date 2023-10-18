package com.ssafy.fit.ui;
import java.util.List;

import com.ssafy.fit.model.Video;
import com.ssafy.fit.model.VideoReview;
import com.ssafy.fit.model.dao.VideoDao;
import com.ssafy.fit.model.dao.VideoDaoImpl;
import com.ssafy.fit.model.dao.VideoReviewDaoImpl;
import com.ssafy.fit.util.SsafitUtil;

public class VideoUi{
	
	//영상UI 클래스의 필드정보
	
	//VideoUi에서 영상관리 클래스인 VideoDaoImpl을 활용하기 위해
	//VideoDaoImpl의 싱글톤 객체를 클래스 필드 변수로 집어넣는다.
	private VideoDao videoDao = VideoDaoImpl.getInstance();
	
	//싱글톤 객체 형성을 위한 private 접근제어자 지정
	private static VideoUi instance = new VideoUi();
	
	//싱글톤 기능 구현을 위해 private으로 기본생성자 지정
	private VideoUi() {}
	
	//싱글톤 객체 생성을 위한 메서드 구현
	//프로그램을 지속적으로 사용할 시, 싱글톤 객체를 사용하지 않으면
	//이전으로 돌아가기 기능을 이용할 때
	//UI 계속 객체가 생성되어
	//메모리 관리가 어려워질 수 있으므로 영상UI클래스에는
	//싱글톤 기능을 꼭 구현하여야 한다.
	public static VideoUi getInstance() {
		return instance;
	}
	
	
	//영상UI호출 시 기능 구현
	public void service() {
		
		SsafitUtil.screenClear(); //화면을 깨끗이 정리하고,
		SsafitUtil.printLine();   //프로그램 가독성을 높이기 위해 구분선 사용
		
		System.out.println("1. 영상목록");
		System.out.println("0. 이전으로");
		SsafitUtil.printLine();
		
		//1을 입력하면 영상 목록을 불러오고,
		//0을 입력하면 이전으로 돌아가도록 기능 구현
		int menu = SsafitUtil.inputInt("메뉴를 선택하세요 : ");
		if (menu == 1) {
			SsafitUtil.screenClear(); //프로그램 가독성을 높이기 위한 화면 정리
			listVideo(); // 영상목록을 불러오기 위해 listVideo() 메서드 호출
			
		} else {
			//이전으로 돌아가기 위해 MainUi 객체를 새로 생성하고
			//MainUi를 다시 호출한다.
			MainUi mu = new MainUi();
			mu.service();
		}
		
	}
	
	
	//영상목록을 선택했을 때,
	//영상목록을 출력해주는 메서드 구현
	//출력한 영상목록을 통해
	//영상번호를 선택했을 때, 비디오의 상세기능을 볼 수 있도록 추가로 기능을 넣는다.
	private void listVideo() {
		
		//영상목록을 불러오기 위해선 해당 정보를 가지고 있는
		//videoDaoImpl 클래스의 객체가 필요하다.
		//위에서 미리 필드값으로 지정해놓은 videoDaoImpl의 싱글톤 객체,
		//videoDao를 이용하여 영상목록을 불러들여온다
		List<Video> list = videoDao.selectVideo();
		
		//아래 코드를 통해
		//영상목록을 출력해준다
		SsafitUtil.printLine();
		System.out.println("전체 " + list.size() + "개");
		SsafitUtil.printLine();
		
		//영상목록의 각 영상마다 넘버링, 파트, 제목을 출력한다.
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getNo() + " " +list.get(i).getPart() +" "+ list.get(i).getTitle());
		}
		SsafitUtil.printLine();
		
		System.out.println("1. 영상상세");
		System.out.println("0. 이전으로");
		SsafitUtil.printLine();
		
		//1을 입력하면 출력된 영상목록에서 영상 번호를 선택하여
		//해당 영상의 상세정보를 볼 수 있도록
		//detailvideo()메서드를 호출한다.
		int menu = SsafitUtil.inputInt("메뉴를 선택하세요 : ");
		
		if (menu == 1) {
			detailVideo();
			
		//다른 값(0포함)을 입력하면,
		//이전으로 돌아갈 수 있도록
		//이 클래스의 service()메서드를 호출한다.
		} else {
			service();
		}
		
	}
	
	
	//선택된 영상의 상세정보를 보여주기 위한 기능 구현
	//영상정보 뿐만 아니라 영상의 리뷰정보 또한 보여주며,
	//영상의 리뷰를 추가로 등록하기 위해 영상리뷰UI를 마지막에 호출한다.
	private void detailVideo() {
		
		//영상번호를 입력하면
		//해당 번호에 해당하는 영상의 상세정보를 출력한다
		//상세정보를 출력하기 위한 videoDao 싱글톤 객체 사용
		int menu = SsafitUtil.inputInt("영상 번호를 입력하세요 : ");
		SsafitUtil.printLine();
		System.out.println("번 호 : " + videoDao.selectVideoByNO(menu).getNo());
		System.out.println("제 목 : " + videoDao.selectVideoByNO(menu).getTitle()); 
		System.out.println("운 동 : " + videoDao.selectVideoByNO(menu).getPart()); 
		System.out.println("영 상 URL : " + videoDao.selectVideoByNO(menu).getUrl());
		SsafitUtil.printLine();
		
	
		//상세정보에서 영상의 정보에 더해
		//추가로 리뷰까지 보여주기 위해 아래에 추가로 코드 작성
		
		//리뷰목록을 읽어들이기 위해
		//영상리뷰관리 싱글톤객체 생성
		VideoReviewDaoImpl vrd = VideoReviewDaoImpl.getinstance();
	
	
		//해당 영상의 리뷰 리스트 가져오기
		if (vrd.selectReview(menu) != null) {
			
			List<VideoReview> reviewlist = vrd.selectReview(menu);
			System.out.println("------------------------------------------");
			System.out.println("------------------------------------------");
			System.out.println("영 상 리 뷰 : "+reviewlist.size()+"개");
			System.out.println("------------------------------------------");
			
			//아래 코드를 통해 해당 영상의 리뷰 내용 모두 표시
			for (int j = 0; j < reviewlist.size(); j++) {
				System.out.println(reviewlist.get(j).getReviewNo()+" "+reviewlist.get(j).getNickName()+" "+reviewlist.get(j).getContent());
			}
			
			// 만약에 해당 영상의 리뷰가 존재하지 않는다면,
			// 영상리뷰 : 0개를 출력한다.
		} else {
			System.out.println("영상리뷰 : 0개");
		}

		
		//출력 후 리뷰를 등록하기 위한 기능을 구현한다.
		SsafitUtil.printLine();
		System.out.println("1. 리뷰등록");
		System.out.println("0. 이전으로");
		SsafitUtil.printLine();
		
		
		int menu2 = SsafitUtil.inputInt("메뉴를 선택하세요 : ");

		//1을 입력받으면 리뷰를 등록하는 과정으로 넘어가도록 한다.
		if (menu2 == 1) {
			//영상리뷰Ui 객체 생성 후, Ui 호출
			VideoReviewUi vru = VideoReviewUi.getinstance(menu);
			vru.service();
		
		// 0을 넣으면 이전(영상목록)으로 돌아간다.
		} else {
			listVideo();
		}
	}
}
