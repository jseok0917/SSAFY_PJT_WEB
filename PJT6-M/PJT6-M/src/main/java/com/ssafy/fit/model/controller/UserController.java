package com.ssafy.fit.model.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.service.ReviewService;
import com.ssafy.fit.model.service.UserService;
import com.ssafy.fit.model.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-user")
@Api(tags="유저 컨트롤러")
@CrossOrigin("*")
public class UserController  {
	
	@Autowired
	private VideoService vService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private ReviewService rService;

	//=======================유저파트 CURD 구현===========================
	@GetMapping("/{id}")
	@ApiOperation(value="유저 정보 조회")
	public ResponseEntity<?> list(@PathVariable String id){
		User user = uService.getUser(id);
		if (user == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);			
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	@ApiOperation(value="회원가입")
	public ResponseEntity<Void> signup(@RequestBody User user){
		int result = uService.createUser(user);
		//0이면 중복된 id가 존재한다는 것임
		//사실 중복id체크하는거 따로 만드는게 맞긴함
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	@ApiOperation(value="유저 정보 수정")
	public ResponseEntity<Void> update(@RequestBody User user){
		uService.modifyUser(user.getId(), user.getName());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 유저 목록 가져오기
	@GetMapping("/users")
	@ApiOperation(value="유저 목록 조회")
	public ResponseEntity<List<User>> allUsers(){
		List<User> users = uService.getAllUser();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	// 유저 팔로우 기능
	@PostMapping("/{followingId}/follow")
	@ApiOperation(value="유저 팔로우")
	//user는 현재 로그인 정보를 가져온다
	public ResponseEntity<Void> follow(@PathVariable String followingId, @RequestBody User user){
		uService.addFollow(user.getId(), followingId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 유저 팔로우 삭제
	@DeleteMapping("/deletefollowing")
	@ApiOperation(value="팔로잉 삭제")
	public ResponseEntity<Void> deletefollowing(@RequestParam String followingId, @RequestBody User user){
		uService.removeFollow(user.getId(), followingId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 팔로우하고 있는 유저 조회
	@GetMapping("/{userId}/followings")
	@ApiOperation(value="팔로잉하고 있는 유저들")
	public ResponseEntity<List<User>> allFollowings(@PathVariable String userId){
		List<User> followings = uService.getFollowingUser(userId);
		return new ResponseEntity<List<User>>(followings, HttpStatus.OK);
		
	}
	
	
	// 유저의 좋아요 영상 추가
	@PostMapping("/like/{youtubeId}")
	@ApiOperation(value="좋아요 영상 추가")
	public ResponseEntity<Void> likeVideo(@PathVariable String youtubeId, @RequestBody User user){
		uService.createUserVideo(user.getId(), youtubeId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 유저의 좋아요 영상 삭제
	@DeleteMapping("/delete/{youtubeId}")
	@ApiOperation(value="좋아요 영상 삭제")
	public ResponseEntity<Void> deleteLikedVideo(@PathVariable String youtubeId, @RequestBody User user){
		uService.removeUserVideo(user.getId(), youtubeId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 유저의 좋아요한 영상 조회
	@GetMapping("/{userId}/LikedVideo")
	@ApiOperation(value="좋아요 영상 조회")
	public ResponseEntity<List<Video>> LikedVideo(@PathVariable String userId){
		List<Video> likedVideoList = uService.getLikedVideo(userId);
		return new ResponseEntity<List<Video>>(likedVideoList, HttpStatus.OK);
	}
	
	//로그아웃 구현 지금은 안될듯... WebSecurity를 알아야함
	//회원탈퇴 구현안함
	

//	case "myPage": // 마이 페이지
//	myPage(req, resp);
//	break;
//case "like": // 좋아용
//	like(req, resp);
//	break;
//case "deleteLike": // 좋아용 삭제
//	deleteLike(req, resp);
//	break;
//case "recommend":
//	doRecommendList(req, resp);

//default:
//	break;
//}

}
