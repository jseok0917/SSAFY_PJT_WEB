# PJT 01

__✔ 작성 정보__

작 성 자 : 서울 7반 석지명

날 짜 : 2023년 8월 4일

환 경 : Windows 10 Pro

개발도구 : Eclipse


<br>

✔ **프로젝트 요구사항**

- 영상정보 데이터 파일(JSON) 파싱 처리
    - Gson 라이브러리 이용
- 영상 목록 조회 기능
- 영상 상세정보 조회 기능
- 영상 리뷰 등록 기능
<br><br>

✔ **프로젝트 목표**
- 객체지향 개념과 Java 프로그램의 기본 구조 및 흐름의 이해와 활용
- Collection, IO API를 이용하여 데이터를 관리하는 프로그램의 작성
- JSON 데이터를 이용하여 프로그램에 적용
- MVC 구조를 이해하고 프로그램에 적용

---


<br>

✔ **ERD (Entity-Relationship Digram)**

![ClassDiagram](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/a22f3c6c-f05a-4814-a632-a9b218ef5635)


## 🔹구현방법
1. 영상정보 파일을 파싱하기 위하여 영상 클래스, 영상 관리 클래스를 작성한다.
<br><br>
2. 리뷰등록기능 구현을 위해 영상리뷰 클래스, 영상리뷰 관리 클래스를 작성한다.
<br><br>
3. Ui상에서의 입출력기능 구현을 위해 SsafitUtil패키지를 작성한다.
<br><br>
4. Ui패키지를 만들어 메인화면 구현을 위한 MainUi클래스를 작성한다.
<br><br>
5. 메인화면에서 영상 목록과 영상 상세정보를 불러오기 위해 VideoUi클래스를 작성한다.
<br><br>
6. 영상상세 화면에서 리뷰를 등록하기 위해 ReviewUi클래스를 작성한다.

---

## 🔹 전체 코드

📂 **<u>폴더 구조</u>**

```
📂 model
   ㄴ ☕ Video.java
   ㄴ ☕ VideoReview.java
   ㄴ 📂 dao
        ㄴ ☕ VideoDao.java
        ㄴ ☕ VideoDaoImple.java
        ㄴ ☕ VideoReviewDao.javal
        ㄴ ☕ VideoReviewDaoImple.javal

📂 test
   ㄴ ☕ SsafitApplication.java

📂 ui
   ㄴ ☕ MainUi.java
   ㄴ ☕ VideoReviewUi.java
   ㄴ ☕ VideoUi.java

📂 util
   ㄴ ☕ SsafitUtil.java
```
<br>

---

### 1. 영상클래스, 영상관리클래스

📌 <u>**Video.java**</u> 
```Java
package com.ssafy.fit.model;


public class Video {
	
	//영상 클래스의 필드정보
	//캡슐화를 위한 private 접근제어자 지정
	private int no;
	private String title;
	private String part;
	private String url;
	
	
	//기본생성자
	public Video() {
		super();
	}
	
	
	//필드정보를 통한 생성자
	public Video(int no, String title, String part, String url) {
		super();
		this.no = no;
		this.title = title;
		this.part = part;
		this.url = url;
	}

	//getter와 setter를 이용한 캡슐화
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	//객체의 정보를 읽어올 수 있도록
	//toString메서드 오버라이드
	@Override
	public String toString() {
		return "Video [no=" + no + ", title=" + title + ", part=" + part + ", url=" + url + "]";
	}
}

```

📌 <u>**VideoDao.java**</u> 
```Java
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

```

📌 <u>**VideoDaoImpl.java**</u> 
```Java
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
	private VideoDaoImpl(a) {
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

```
<br>

---

### 2. 리뷰, 리뷰관리클래스

📌 <u>**VideoReview.java**</u> 
```Java
package com.ssafy.fit.model;

public class VideoReview {
	
	//영상리뷰 클래스의 필드정보
	//캡슐화를 위한 private 접근제어자 지정
	private int videoNo;
	private int reviewNo;
	private String nickName;
	private String content;
	
	//기본생성자
	public VideoReview() {
		super();
	}

	//생성자
	public VideoReview(int videoNo, int reviewNo, String nickName, String content) {
		super();
		this.videoNo = videoNo;
		this.reviewNo = reviewNo;
		this.nickName = nickName;
		this.content = content;
	}

	
	//getter와 setter를 통한 캡슐화
	public int getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(int videoNo) {
		this.videoNo = videoNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	//객체의 정보를 읽어올 수 있도록
	//toString메서드 오버라이드
	@Override
	public String toString() {
		return "VideoReview [videoNo=" + videoNo + ", reviewNo=" + reviewNo + ", nickName=" + nickName + ", content="
				+ content + "]";
	}
	
	
	
	

}

```

📌 <u>**VideoReviewDao.java**</u> 
```Java
package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.VideoReview;

public interface VideoReviewDao {
	
	//VideoReviewDaoImpl클래스를 위한 interface
	
	//리뷰 삽입기능 구현이 필요하다
	int insertReview(VideoReview videoReview);
	
	//특정 영상을 골랐을 때, 해당 영상의 모든 리뷰 리스트를 반환하는 메서드가 필요하다.
	List<VideoReview> selectReview(int videoNo);
	

}

```

📌 <u>**VideoReviewDaoImpl.java**</u> 
```Java
package com.ssafy.fit.model.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.fit.model.VideoReview;

public class VideoReviewDaoImpl implements VideoReviewDao {
	
	private static int reviewNo;
	
	//영상 리뷰를 관리하기 위한 Db 필드 변수 생성
	private Map<Integer, List<VideoReview>> videoReviewDb = new HashMap<>();
	
	
	
	
	//싱글톤 패턴 구현
	private static VideoReviewDaoImpl instance = new VideoReviewDaoImpl();
	
	private VideoReviewDaoImpl() {}
	
	public static VideoReviewDaoImpl getinstance() {
		return instance;
	}

	// 리뷰를 리스트에 삽입하는 메서드 구현
	@Override
	public int insertReview(VideoReview videoReview) {
		
		//videoReviewDb 필드 변수가 생성될 때,
		//videoReviewDb가 null이라면
		//value값으로 ArrayList가 존재하질 않아
		//Exception오류가 뜨므로
		//Map클래스의 getOrDefault메서드를 사용한다.
		List<VideoReview> list = videoReviewDb.getOrDefault(videoReview.getVideoNo(), new ArrayList<>());
		list.add(videoReview);
		videoReviewDb.put(videoReview.getVideoNo(), list);
		return list.size();
		
		
	}
	// 리뷰 선택(영상 번호 선택 시, 해당 영상에 대한 리뷰 목록을 보여줌)
	
	@Override
	public List<VideoReview> selectReview(int videoNo) {
		return videoReviewDb.get(videoNo);
	}
	
	

	
	
	
	

}

```

<br>

---

### 3. 입출력 기능

📌 <u>**SsafitUtil.java**</u> 
```Java
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

```


<br>

---

### 4~6. 메인화면, Ui



📌 <u>**SsafitApplication.java**</u> 
```Java
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

```



📌 <u>**MainUi.java**</u> 
```Java
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

```

📌 <u>**VideoUi.java**</u> 
```Java
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

```

📌 <u>**VideoReviewUi.java**</u> 
```Java
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

```






## 🔹구현결과

### 실행화면<br>
![1](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/aba740e9-8abd-49fd-b865-17ff13d02b0e)
<br><br><br>

---

### 메인화면<br>
![2](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/e560f754-2c6a-4e11-9bce-e35fbae315a0)
<br><br><br>

---

### 영상목록 화면<br>
![3](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/37e74274-2568-439d-a5dc-eb69d079afe2)
![4](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/c5f92c75-d6b8-4604-b803-0dee3f710f15)
<br><br><br>

---

### 영상상세 화면 <br>
![5](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/959bf975-69e0-434b-95ed-90ff8f988b7b)
<br><br><br>

---

### 리뷰등록 화면<br>
![6](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/3091a29f-1ec3-49c6-b670-bd8495df11ff)
<br><br><br>

---

### 리뷰등록 완료시<br>
![7](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/94a155eb-02c9-4033-93ac-5a0c89e19b7f)
<br><br><br>

---

### 리뷰목록 화면<br>
![8](https://github.com/ssafy10-seoul07/PJT1-J/assets/139304962/6f380c9b-d96b-4e90-a316-304b954b39cd)
<br><br><br>


---
---
---



## 🤷‍♀️ 느낀 점
---
&nbsp;자바에 입문하고 나서 처음으로 Ui와 기능을 갖춘 프로그램을 만들어보았습니다. 명세서만 보았을 때는 앞이 캄캄했지만, 참고용 화면 예시를 보며 그동안 배웠던 것들을 총동원해가며 한땀 한땀 프로그램을 구현해낼 수 있었습니다. 누군가가 볼 땐 굉장히 간단하고 볼품없는 프로그램일거라 생각합니다. 하루종일 머리를 싸매가며 구현해낸 게 이렇게나 별 것 없는 것이라는 데에 조금 허망하다는 기분이 듭니다. 그럼에도 온힘을 다해 이 하나의 프로그램을 완성해낸 지금, 큰 산을 하나 넘어선 듯한 느낌을 받고 있습니다.
<br><br>
&nbsp;&nbsp;프로그래밍을 시작하고 나서 첫 프로젝트이고, 시간이 빠듯했기에 깃허브 사용과 협업에 관한 부분은 큰 문제를 느끼지 못했습니다. 대신, 자바 언어에 대해 좀 더 깊은 이해를 가져갈 수 있었습니다. 가장 체감한 부분은 이 프로그램을 만들면서 싱글톤 패턴이 왜 필요한지 확실히 이해할 수 있었고, 패키지 별로 다르게 코드를 작성해야하는 이유를 뼈저리게 느꼈다는 것입니다. 또, 따로따로 만든 클래스들에서 활용되는 메서드를 통한 패키지 관리와 데이터베이스 관리의 용이함 속에서, 객체지향 언어의 장점을 확실하게 알 수 있었던 프로젝트였다고 생각합니다.
<br><br>
&nbsp;&nbsp;이번 관통PJT는 제자신이 좀 더 발전할 수 있었던 것 같고, 간단한 프로그램 작성 하나도 굉장히 많은 노력이 들어감을 느꼈던 것 같습니다.

