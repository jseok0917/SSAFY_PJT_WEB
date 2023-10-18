package com.ssafy.fit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.fit.model.dto.Review;
import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.util.DBUtil;

public class ReviewDaoImpl implements ReviewDao {
	
	private DBUtil util = DBUtil.getInstance();
	
	private List<Video> videoList;
	private Map<String, List<Review>> reviewList = new HashMap<>();

	//싱글톤 패턴 구현
	private static ReviewDao reviewInstance = new ReviewDaoImpl();

	public static  ReviewDao getInstance() {
		return reviewInstance;
	}
	

	@Override
	public List<Review> selectVideoReview(String youtubeId) {
		String sql = "SELECT * FROM review WHERE youtubeId = ?";
		List<Review> reviewList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, youtubeId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				
				review.setId(rs.getInt(1));
				review.setWriter(rs.getString(2));
				review.setContent(rs.getString(3));
				review.setRegDate(rs.getString(4));
				review.setYoutubeId(rs.getString(5));
				
				reviewList.add(review);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return reviewList;
	}


	@Override
	public void registVideoReview(Review review) {
		String sql = "INSERT INTO review(writer, content, youtubeId) VALUES(?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getWriter());
			pstmt.setString(2, review.getContent());
			pstmt.setString(3, review.getYoutubeId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		
	}


	@Override
	public void updateVideoReview(Review review) {
		String sql = "UPDATE review SET content = ? WHERE youtubeId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, review.getContent());
			pstmt.setString(2, review.getYoutubeId());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}

	}


	@Override
	public void deleteVideoReview(String youtubeId, int id) {
		String sql = "DELETE FROM review WHERE youtubeId = ? AND id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, youtubeId);
			pstmt.setInt(2, id);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		
	}

	//  
	
	
	
	
	@Override
	public Review selectReview(String youtubeId, int id) {
		String sql = "SELECT * FROM review Where youtubeId = ? AND id = ?";
		Review review = new Review();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, youtubeId);
			pstmt.setInt(2, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				review = new Review();
				
				review.setId(rs.getInt(1));
				review.setWriter(rs.getString(2));
				review.setContent(rs.getString(3));
				review.setRegDate(rs.getString(4));
				review.setYoutubeId(rs.getString(5));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		
		return review;
	}


}
