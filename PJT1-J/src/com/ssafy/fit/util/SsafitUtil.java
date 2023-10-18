package com.ssafy.fit.util;

import java.util.Scanner;

public class SsafitUtil {
	
	//입력을 받을 때마다 모든 패키지에서 Scanner 객체를 형성하는 것이 매우 비효율적이므로
	//Scanner 객체를 전역변수로 지정하고
	//SsafitUtil 클래스를 통해 단 하나의 Scanner객체에 접근할 수 있도록
	//입력만을 위한 패키지를 생성한다.
	private static Scanner sc = new Scanner(System.in);
	
	//기본생성자 - 싱글톤 같은 느낌이므로 Private 으로 지정
	private SsafitUtil() {
		
	}
	
	//입력을 받기위한 메서드
	//입력은 모든 Ui에서 사용할 수 있어야 하므로 
	//static으로 지정하여야 한다.
	
	//메서드에 넣은 String메시지를 출력하고 String입력을 받는다.
	public static String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	
	//메서드에 넣은 String메시지를 출력하고 int입력을 받는다
	public static int inputInt(String msg) {
		System.out.print(msg);
		String input = sc.nextLine();
		int result = Integer.parseInt(input);
		return result;
	}
	
	//가독성을 높이기 위한 구분선 생성 메서드
	public static void printLine() {
		System.out.println("---------------------------------------------------------");
	}
	
	//가독성을 높이기 위한 콘솔 창 비우기 메서드
	public static void screenClear() {
		for (int i = 0; i < 50; i++) {
			System.out.println("");
		}
	}

}
