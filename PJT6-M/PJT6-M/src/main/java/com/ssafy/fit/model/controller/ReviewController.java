package com.ssafy.fit.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.model.dto.Review;
import com.ssafy.fit.model.service.ReviewService;
import com.ssafy.fit.model.service.UserService;
import com.ssafy.fit.model.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-review")
@Api(tags="리뷰 컨트롤러")
@CrossOrigin("*")
public class ReviewController  {
	
	@Autowired
	private VideoService vService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private ReviewService rService;

	//=======================리뷰파트 CURD 구현===========================
	@GetMapping("/list/{youtubeId}")
	@ApiOperation(value="리뷰 조회")
	public ResponseEntity<List<Review>> list(@PathVariable String youtubeId){
		List<Review> reviewList = rService.getReview(youtubeId);
		return new ResponseEntity<List<Review>>(reviewList, HttpStatus.OK);
	}
	
	@PostMapping("/write")
	@ApiOperation(value="리뷰 작성")
	public ResponseEntity<Void> write(@RequestBody Review review){
		rService.writeReview(review);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	@ApiOperation(value="리뷰 수정")
	public ResponseEntity<Void> update(@RequestBody Review review){
		rService.modifyReview(review.getVideoId(), review.getReviewNo(), review.getContent());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{youtubeId}")
	@ApiOperation(value="리뷰 삭제")
	public ResponseEntity<Void> delete(@PathVariable String youtubeId, @RequestParam int reviewNo){
		rService.removeReview(youtubeId, reviewNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

}
