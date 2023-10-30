package com.ssafy.fit.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.model.dto.SearchCondition;
import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.service.ReviewService;
import com.ssafy.fit.model.service.UserService;
import com.ssafy.fit.model.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags="비디오 컨트롤러")
@CrossOrigin("*")
public class VideoController  {
	
	@Autowired
	private VideoService vService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private ReviewService rService;
	
	//===============영상 R 구현===================
	
	@GetMapping("/list")
	@ApiOperation(value="영상 목록 조회")
	public ResponseEntity<?> list(){
		List<Video> list = vService.getList();
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/latestList")
	@ApiOperation(value="최근 영상 목록 조회")
	public ResponseEntity<?> latestList(){
		List<Video> latestList = vService.getLatestList();
		if(latestList == null || latestList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(latestList, HttpStatus.OK);
	}
	
	@PostMapping("/searchVideo")
	@ApiOperation(value="비디오 검색")
	public ResponseEntity<?> searchVideo(@RequestBody SearchCondition sc){
		List<Video> searchList = vService.searchVideo(sc);
		if(searchList == null || searchList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(searchList, HttpStatus.OK);
	}
	
	@GetMapping("/{youtubeId}")
	@ApiOperation(value="비디오 상세보기")
	public ResponseEntity<?> detail(@PathVariable String youtubeId){
		Video video = vService.getVideo(youtubeId);
		if (video == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}
	

}
