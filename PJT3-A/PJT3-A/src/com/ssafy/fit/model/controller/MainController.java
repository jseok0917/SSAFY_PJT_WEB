package com.ssafy.fit.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.fit.model.dao.MainDao;
import com.ssafy.fit.model.dao.MainDaoImpl;
import com.ssafy.fit.model.dto.Review;
import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.service.MainService;
import com.ssafy.fit.model.service.MainServiceImpl;
import com.ssafy.fit.model.service.ReviewService;
import com.ssafy.fit.model.service.ReviewServiceImpl;
//
@WebServlet("/ssafit")
public class MainController extends HttpServlet {

    private MainDao mainDao = MainDaoImpl.getInstance();
    private MainService mainService = MainServiceImpl.getInstance();
    private ReviewService reviewService = ReviewServiceImpl.getInstance();
    

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("POST")) {
        	req.setCharacterEncoding("UTF-8");
        }
    	
    	
    	String act = req.getParameter("act");

        switch (act) {
        	case "main":
        		req.getRequestDispatcher("/ssafit/main.jsp").forward(req, resp);
        		break;

            case "video":
                // 부위별 운동 영상 목록을 가져와서 표시합니다.
            	doVideoList(req,resp);
                break;
            case "wholevideo":
            	// 부위별 운동 영상 목록을 가져와서 표시합니다.
            	doWholeVideoList(req,resp);
            	break;
            case "uppervideo":
                // 부위별 운동 영상 목록을 가져와서 표시합니다.
            	doUpperVideoList(req,resp);
                break;
            case "lowervideo":
                // 부위별 운동 영상 목록을 가져와서 표시합니다.
            	doLowerVideoList(req,resp);
                break;
            case "abrominalvideo":
                // 부위별 운동 영상 목록을 가져와서 표시합니다.
            	doAbrominalVideoList(req,resp);
                break;

            case "detailVideo":
                
            	doDetailVideo(req, resp);
                break;

            case "writeReview":
                // 특정 운동 영상에 리뷰를 추가합니다.
            	writeReview(req, resp);
                break;

            case "editReview":
                // 특정 리뷰를 수정합니다.
                doEditReview(req, resp);
                break;

            case "deleteReview":
                // 특정 리뷰를 삭제합니다.
            	doDeleteReview(req, resp);
                break;

             case "signin" :
            	 req.getRequestDispatcher("/ssafit/signin.jsp").forward(req, resp);
            	 break;
             case "signup" :
            	 req.getRequestDispatcher("/ssafit/signup.jsp").forward(req, resp);
            	 break;
        }
    }
    
    protected void doDetailVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String videoId = req.getParameter("videoId");
        Video video = mainDao.selectVideo(videoId);
        int viewCnt = video.getViewCnt();
        video.setViewCnt(viewCnt+1);
        
        List<Review> reviewList = reviewService.getList(videoId);

        req.setAttribute("video", video);
        req.setAttribute("videoId", videoId);
        
        //리뷰리스트 싣기
        req.setAttribute("reviewList", reviewList);
        
        //실어서 보내기
        req.getRequestDispatcher("/ssafit/detailVideo.jsp").forward(req, resp);
    }
    
    

    
    //영상파트(메인파트) 구현
    protected void doVideoList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	List<Video> videoList = mainService.getList();
    	
    	//비디오목록을  request에 담아 전송
    	req.setAttribute("videoList", videoList);	
    	
    	req.getRequestDispatcher("/ssafit/video.jsp").forward(req, resp);
    	
    }
    
    protected void doWholeVideoList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	
    	
    	List<Video> videoList = mainService.getPartList("전신");
    	
    	//비디오목록을  request에 담아 전송
    	req.setAttribute("videoList", videoList);	
    	
    	req.getRequestDispatcher("/ssafit/video.jsp").forward(req, resp);
    	
    }
    
    
    protected void doUpperVideoList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	List<Video> videoList = mainService.getPartList("상체");
    	
    	//비디오목록을  request에 담아 전송
    	req.setAttribute("videoList", videoList);	
    	
    	req.getRequestDispatcher("/ssafit/video.jsp").forward(req, resp);
    	
    }
    
    protected void doLowerVideoList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	List<Video> videoList = mainService.getPartList("하체");
    	
    	//비디오목록을  request에 담아 전송
    	req.setAttribute("videoList", videoList);	
    	
    	req.getRequestDispatcher("/ssafit/video.jsp").forward(req, resp);
    	
    }
    
    protected void doAbrominalVideoList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	List<Video> videoList = mainService.getPartList("복부");
    	
    	//비디오목록을  request에 담아 전송
    	req.setAttribute("videoList", videoList);	
    	
    	req.getRequestDispatcher("/ssafit/video.jsp").forward(req, resp);
    	
    }
    
    
    
    //******************************************//
    //리뷰파트 구현
    protected void writeReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String writer = req.getParameter("writer");
    	String content = req.getParameter("content");
    	String youtubeId = req.getParameter("youtubeId");
    	
    	Review review = new Review(youtubeId, writer, content);
    	reviewService.writeReview(review);
    	
    	List<Review> reviews = reviewService.getList(youtubeId);
    	req.setAttribute("reviewList", reviews);
    	
    	resp.sendRedirect("ssafit?act=detailVideo&videoId="+youtubeId);
    	
    }
    
    protected void doEditReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id = Integer.parseInt(req.getParameter("reviewId"));
    	String youtubeId = req.getParameter("youtubeId");
    	String content = req.getParameter("editedContent");
    	
    	Review review = reviewService.selectReview(youtubeId, id);
    	review.setContent(content);
    	reviewService.modifyReview(review);
    	resp.sendRedirect("ssafit?act=detailVideo&videoId="+youtubeId);
    }
    
    protected void doDeleteReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id = Integer.parseInt(req.getParameter("reviewId"));
    	String youtubeId = req.getParameter("youtubeId");
    	
    	//영상Id를 일단 받지말아보자
    	//리뷰 중에서만 탐색하도록 
    	reviewService.removeReview(youtubeId, id);
    	
    	resp.sendRedirect("ssafit?act=detailVideo&videoId="+youtubeId);
    }
    
    
    
    
    
}


