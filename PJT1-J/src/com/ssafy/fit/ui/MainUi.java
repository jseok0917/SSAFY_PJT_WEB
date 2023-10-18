package com.ssafy.fit.ui;

import com.ssafy.fit.util.SsafitUtil;

public class MainUi {
	
	//MainUI를 호출했을때(서비스했을때) 기능 구현
	public void service() {
		
		//불러들일 때는 화면을 깨끗이 정리한다.
		SsafitUtil.screenClear();
		//구분선을 통해 프로그램 콘솔의 가독성을 높여준다 
		SsafitUtil.printLine();
		
		System.out.println("자바로 구현하는 SSAFIT");
		SsafitUtil.printLine();
		SsafitUtil.printLine();
		System.out.println("1. 영상정보");
		System.out.println("0. 종료");
		SsafitUtil.printLine();
		
		//입력을 받아 Video Ui를 호출할 수 있도록 한다.
		int menu = SsafitUtil.inputInt("메뉴를 선택하세요 : ");
		
		// 1을 입력시 Video Ui를 호출하고
		// 다른 경우(0포함)에는 프로그램을 종료한다
		if (menu ==1) {
			
			//캡슐화에 의하여 Video Ui를 직접적으로 호출할 수 없기에,
			//싱글톤 객체를 형성하여 Video Ui를 호출한다.
			VideoUi vu = VideoUi.getInstance();
			SsafitUtil.screenClear(); //가독성을 위해 화면 정리코드를 추가
			vu.service();
		} else {
			SsafitUtil.screenClear();
			exit();
		}
	}
	
	// exit메서드를 호출할 시에 프로그램이 종료되도록
	// main함수를 종료하기위해 void - return 이용
	private void exit() {
		return;
	}
}
