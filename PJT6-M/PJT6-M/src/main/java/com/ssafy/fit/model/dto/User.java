package com.ssafy.fit.model.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String id;
	private String pass;
	private String name;
	private List<String> followingUser; //본인이 팔로우한 유저들 담을 리스트
	
	public User() {}

	public User(String id, String pass, String name) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.followingUser = new ArrayList<>();
	}
	public User(String id,  String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public List<String> getFollowingUser() {
		return followingUser;
	}

	public void setFollowingUser(List<String> followingUser) {
		this.followingUser = followingUser;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", name=" + name + "]";
	}
}