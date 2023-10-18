package com.ssafy.fit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.fit.model.dto.Review;
import com.ssafy.fit.model.dto.Video;
import com.ssafy.fit.model.util.DBUtil;

public class MainDaoImpl implements MainDao {
	// DBUtile 들고와야겠다.
	private DBUtil util = DBUtil.getInstance();


	private List<Video> videoList;
	private List<Review> reviewList;

	//기본생성자
	private MainDaoImpl() {
	}
	//싱글톤패턴 구현
	private static MainDao mainInstance = new MainDaoImpl();

	public static MainDao getInstance() {
		return mainInstance;
	}


	public Video selectVideo(String youtubeId) {
		String sql = "SELECT * FROM video WHERE youtubeId = ?";
		Video video = new Video();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2. 데이터베이스 연결
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, youtubeId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				video.setTitle(rs.getString("title"));
				video.setFitPartName(rs.getString("fitPartName"));
				video.setYoutubeId(rs.getString("youtubeId"));
				video.setChannelName(rs.getString("channelName"));
				video.setViewCnt(rs.getInt("viewCnt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return video;
	}

	public List<Video> selectAllVideo() {
		String sql = "SELECT * FROM video";
		List<Video> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 2. 데이터베이스 연결
		try {
			conn = util.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Video video = new Video();
				video.setTitle(rs.getString(1));
				video.setFitPartName(rs.getString(2));
				video.setYoutubeId(rs.getString(3));
				video.setChannelName(rs.getString(4));
				video.setViewCnt(rs.getInt(5));

				list.add(video);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, stmt, conn);
		}
		return list;
	}

	// 조회수 순으로
	// 나오도록 구현
	@Override
	public List<Video> selectInterestViewFitVideo() {
		String sql = "SELECT * FROM video ORDER BY viewCnt DESC";
		List<Video> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 2. 데이터베이스 연결
		try {
			conn = util.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Video video = new Video();
				video.setTitle(rs.getString(1));
				video.setFitPartName(rs.getString(2));
				video.setYoutubeId(rs.getString(3));
				video.setChannelName(rs.getString(4));
				video.setViewCnt(rs.getInt(5));

				list.add(video);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, stmt, conn);
		}
		
		
		return list;
			

	}
	
	



	// 부위별로
	@Override
	public List<Video> selectPartFitVideo(String fitPartName) {
		
		
		List<Video> partFitVideo = new ArrayList<>();

		String sql = "SELECT * FROM video WHERE fitPartName = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2. 데이터베이스 연결
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fitPartName);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Video video = new Video();
				video.setTitle(rs.getString(1));
				video.setFitPartName(rs.getString(2));
				video.setYoutubeId(rs.getString(3));
				video.setChannelName(rs.getString(4));
				video.setViewCnt(rs.getInt(5));

				partFitVideo.add(video);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}

		return partFitVideo;
	}
	

	//키워드로 검색했을 때 
	//비디오 리스트에서, 검색한 키워드를 갖고있는 비디오들만 찾아서 비디오 리스트로 반환
	@Override
	public List<Video> searchVideo(String keyword) {
		List<Video> searchList = new ArrayList<>();

		String sql = "SELECT * FROM video WHERE title LIKE ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2. 데이터베이스 연결
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%'+keyword+'%');
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Video video = new Video();
				video.setTitle(rs.getString(1));
				video.setFitPartName(rs.getString(2));
				video.setYoutubeId(rs.getString(3));
				video.setChannelName(rs.getString(4));
				video.setViewCnt(rs.getInt(5));

				searchList.add(video);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}

		return searchList;
	}


}