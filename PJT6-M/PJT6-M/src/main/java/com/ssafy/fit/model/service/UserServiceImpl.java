package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.UserDao;
import com.ssafy.fit.model.dao.VideoDao;
import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.dto.Video;

@Service
public class UserServiceImpl implements UserService {

	// 의존성 주입
	@Autowired
	private UserDao dao;
	
	@Autowired
	private VideoDao vDao;

	@Override
	public int createUser(User user) {
		return dao.insertUser(user);
	}

	@Override
	public User getUser(String userId) {
		return dao.selectOneUser(userId);
	}

	@Override
	public void modifyUser(String id, String name) {
		dao.updateUser(id, name);
	}
	
	public void removeUser(String id) {
		dao.deleteUser(id);
	}
	
	@Override
	public List<Video> getLikedVideo(String userId) {
		return dao.selectUserVideo(userId);
	}

	@Override
	public void createUserVideo(String userId, String videoId) {
		dao.insertUserVideo(userId, videoId);

	}

	@Override
	public void removeUserVideo(String userId, String youtubeId) {
		dao.deleteUserVideo(userId, youtubeId);
	}

	@Override
	public List<User> getAllUser() {
		return dao.selectAllUser();
	}

	// 다른 회원 팔로우
	@Override
	public void addFollow(String followerId, String followingId) {
		dao.insertFollow(followerId, followingId);
	}
	
	//팔로우 삭제
	public void removeFollow(String followerId, String followingId) {
		dao.deleteFollow(followerId, followingId);
	}
	
	public List<User> getFollowingUser(String followerId){
		return dao.selectFollowingUser(followerId);
	}




}
