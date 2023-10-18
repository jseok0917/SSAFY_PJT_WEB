package com.ssafy.fit.ui;

import java.util.List;
import java.util.Scanner;

import com.ssafy.fit.model.VideoReview;
import com.ssafy.fit.model.dao.VideoReviewDao;
import com.ssafy.fit.model.dao.VideoReviewDaoImpl;
import com.ssafy.fit.util.SsafitUtil;

public class VideoReviewUi {
	
	//영상리뷰UI 클래스의 필드정보
	//싱글톤 객체 형성을 위한 private 접근제어자 지정
	private VideoReviewDao videoReviewDao;
	private static VideoReviewUi instance = new VideoReviewUi();
	private int videoNo;
	
	//싱글톤 기능 구현을 위해 private으로 생성자 지정
	private VideoReviewUi() {}
	private VideoReviewUi(int videoNo) {}
	
	
	//싱글톤 객체 생성을 위한 메서드 구현
	//프로그램을 지속적으로 사용할 시, 싱글톤 객체를 사용하지 않으면
	//이전으로 돌아가기 기능을 이용할 때
	//UI 계속 객체가 생성되어
	//메모리 관리가 어려워질 수 있으므로 영상리뷰UI클래스에는
	//싱글톤 기능을 꼭 구현하여야 한다.
	
	//특정 영상에 대한 영상리뷰Ui를 구현하기 위해서는, 
	//그 영상이 어떤 영상인지를 알아야하므로
	//싱글톤 객체를 생성할 때도 반드시 영상 번호를 받아 생성하고,
	//생성된 싱글톤 객체에 영상번호 필드정보를 넣어주어야한다!!!
	public static VideoReviewUi getinstance(int videoNo) {
		instance.videoNo = videoNo;
		return instance;
	}
	
	
	//영상리뷰Ui 호출 시에는 자동으로 리뷰등록기능으로 넘어가도록 한다.
	public void service() {
		instance.registReview();
		
	}
	
	//리뷰목록 출력 기능 구현
	private void listReview() {
		
		VideoReviewDaoImpl vrd = VideoReviewDaoImpl.getinstance();
		
		
		SsafitUtil.printLine();
		System.out.println("1. 리뷰목록");
		System.out.println("0. 영상목록으로 돌아가기");
		SsafitUtil.printLine();
		
		//1을 누르면 등록된 리뷰 목록을 보여주고 다시 listReview를 호출
		//다른값을 입력하면(0포함) 다시 영상목록으로 돌아간다.
		int menu = SsafitUtil.inputInt("메뉴를 선택하세요 : ");
		
		
		//리뷰 리스트가 비어있지 않을때만 목록을 보여준다.
		//해당 영상의 리뷰 리스트 가져오기
		if (menu == 1) {
			if (vrd.selectReview(this.videoNo) != null) {
				
				List<VideoReview> reviewlist = vrd.selectReview(this.videoNo);
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
			this.listReview();
			
			
		} else {
	        VideoUi vu = VideoUi.getInstance();
	        vu.service();
		}
		
		
		
		
	}
	
	
	//리뷰등록 기능 구현
	private void registReview() {
		
		
		//리뷰를 등록하기 위해서는 리뷰관리를 위해
		//VideoReview클래스 객체가 필요하므로,
		//해당 클래스의 싱글톤 객체를 생성한다.
		VideoReviewDaoImpl vd = VideoReviewDaoImpl.getinstance();
		
		//등록할 리뷰 객체 생성
		VideoReview review = new VideoReview();
		
		
		//리뷰 닉네임 입력
		String nickName = SsafitUtil.input("닉네임을 입력하세요 : ");
		review.setNickName(nickName);
		System.out.println();
		
		
		//리뷰 내용 입력
		String content = SsafitUtil.input("내용을 입력하세요 : ");
        review.setContent(content);
        
        //등록할 리뷰가 어떤 영상에 대한 리뷰인지 번호 입력
        //영상리뷰Ui 싱글톤 객체 생성 시
        //그 싱글톤 객체에 반드시 videoNo를 넣어줘야하므로
        //아래 코드에는 문제가 없다.
        review.setVideoNo(this.videoNo); 
        
		
		//선택된 영상에 대한 리뷰리스트를 가져오기
        //영상이 있을때만 출력
        //listReview() 기능으로 넣어둬도 문제는 없을 것 같다.
        //리뷰가 잘 등록됐는지 확인용
        
        //만약에 리뷰리스트가 이미 존재한다면
		if (vd.selectReview(this.videoNo) != null) {
			List<VideoReview> reviewlist = vd.selectReview(this.videoNo);
			
			//그 리뷰리스트의 크기보다 1 크도록 리뷰번호를 설정한 후
	        review.setReviewNo(reviewlist.size()+1);
	        
	        //입력한 리뷰를 리뷰db에 삽입한다.
	        vd.insertReview(review);
	        System.out.println("리뷰등록 완료");
			
		//리뷰리스트가 존재하지 않는다면, 리뷰번호를 1로 설정하고
	    //입력한 리뷰를 리뷰db에 삽입한다.
		} else {
			review.setReviewNo(1);
			vd.insertReview(review);
			System.out.println("리뷰등록 완료");
		}
		
		

        // 리뷰 등록이 완료되면 listReview메서드를 호출하여
		// 등록한 리뷰들을 보여준다.
		this.listReview();
        
		
	}

}
