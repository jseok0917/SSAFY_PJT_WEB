package com.ssafy.fit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.dto.Video;

public interface UserDao {

	// 유저 생성
	// 생성시 생성불가하면 DB에서 0이 반환되오는거 맞나?
	int insertUser(User user);

	// 유저 수정
	void updateUser(String id, String name);

	// 유저 삭제
	void deleteUser(String id);

	// 유저 조회
	User selectOneUser(String userId);

	// 유저 목록 가져오기
	List<User> selectAllUser();

	// 유저 팔로우 기능
	void insertFollow(@Param("followerId") String followerId, @Param("followingId") String followingId);

	// 유저 팔로우 삭제
	void deleteFollow(@Param("followerId") String followerId, @Param("followingId") String followingId);

	// 팔로우하고 있는 유저 조회
	List<User> selectFollowingUser(String followerId);

	List<Video> selectVideoByChannelName(List<String> list);
	
	// 유저의 좋아요 영상 추가
	void insertUserVideo(@Param("userId") String userId,@Param("youtubeId") String youtubeId);

	// 유저의 좋아요 영상 삭제
	void deleteUserVideo(@Param("userId") String userId,@Param("youtubeId") String youtubeId);
	
	// 유저의 좋아요한 영상 조회
	List<Video> selectUserVideo(String userId);

}
