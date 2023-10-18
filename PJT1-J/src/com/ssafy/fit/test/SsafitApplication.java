package com.ssafy.fit.test;


import com.ssafy.fit.ui.MainUi;

public class SsafitApplication {
	public static void main(String[] args) {
		
		
		//프로그램 실행 시 MainUi 객체를 만들고,
		//바로 MainUi를 호출한다(서비스한다)
		MainUi mu = new MainUi();
		mu.service();	
		
	}

}
