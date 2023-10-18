package com.ssafy.fit.model.dto;

import java.util.List;

public class User {
	
	private String Id;
	private String password;
	private String nickname;
	private List<Video> LikeVideoList;
	private List<User> followerList;
	private List<User> followingList;
	
	public User(String id, String password, String nickname) {
		super();
		Id = id;
		this.password = password;
		this.nickname = nickname;
	}

	public User(String id, String password, String nickname, List<Video> likeVideoList, List<User> followerList,
			List<User> followingList) {
		super();
		Id = id;
		this.password = password;
		this.nickname = nickname;
		LikeVideoList = likeVideoList;
		this.followerList = followerList;
		this.followingList = followingList;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<Video> getLikeVideoList() {
		return LikeVideoList;
	}

	public void setLikeVideoList(List<Video> likeVideoList) {
		LikeVideoList = likeVideoList;
	}

	public List<User> getFollowerList() {
		return followerList;
	}

	public void setFollowerList(List<User> followerList) {
		this.followerList = followerList;
	}

	public List<User> getFollowingList() {
		return followingList;
	}

	public void setFollowingList(List<User> followingList) {
		this.followingList = followingList;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", password=" + password + ", nickname=" + nickname + ", LikeVideoList="
				+ LikeVideoList + ", followerList=" + followerList + ", followingList=" + followingList + "]";
	}


}
