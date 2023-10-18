package com.ssafy.fit.model.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.ssafy.fit.model.Video;

// VideoDao 인터페이스를 상속
public class VideoDaoImpl implements VideoDao{
	
	//필드정보
	private List<Video> list = new ArrayList<>();
	private static VideoDaoImpl instance = new VideoDaoImpl();
	
	//기본생성자 - 생성 시 loadData()메서드를 호출하여 
	//자동으로 영상정보 데이터를 파싱한다.
	private VideoDaoImpl() {
		loadData();
	}
	
	//싱글톤 객체 생성 메서드 구현
	public static VideoDaoImpl getInstance() {
		return instance;
	}
	
	
	//데이터를 파싱하기 위한 loadDate 메서드 구현
	//BufferedReader를 통해 File을 input하고
	//해당 파일을 라인 별로 String으로 읽어들여
	//StringBuilder를 통해 종합한 후
	//gson라이브러리를 통해 
	//StringBuilder로 종합된 String 정보를 이용하여
	//Video 객체를 생성하고 VideoDaoImpl클래스의 필드정보인 list로 읽어들인다
	private void loadData() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/video.json")))){
			StringBuilder sb = new StringBuilder();
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str).append("\n");
			}
			Gson gson = new Gson();
			Video[] templist = gson.fromJson(sb.toString(), Video[].class);
			for (Video v : templist) {
				list.add(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//영상 리스트 반환 메서드 구현
	@Override
	public List<Video> selectVideo() {
		return list;
	}
	
	
	//주어진 영상 번호를 이용하여, 영상 리스트에서
	//해당 번호의 영상을 선택하는 기능 구현
	@Override
	public Video selectVideoByNO(int no) {
		Video result = new Video();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNo() == no) {
				result = list.get(i);
			}
		}
		return result;
	}

}
