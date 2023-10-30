package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.*;

public interface UserService {

	// 유저 생성
	int createUser(User user);

	// 유저 조회
	User getUser(String userId);

	// 유저 수정
	void modifyUser(String id, String name);

	// 유저 삭제
	void removeUser(String id);

	// 유저 좋아요한 영상 조회
	List<Video> getLikedVideo(String userId);

	// 유저 좋아요 영상 추가
	void createUserVideo(String userid, String videoId);

	// 유저 좋아요 영상 삭제
	void removeUserVideo(String userId, String youtubeId);

	// 전체 유저 조회
	List<User> getAllUser();

	// 팔로우
	void addFollow(String followerId, String followingId);

	// 팔로우 삭제
	void removeFollow(String followerId, String followingId);

	// 유저가 팔로우한 유저 조회
	List<User> getFollowingUser(String followerId);
}
