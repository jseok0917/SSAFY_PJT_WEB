package com.ssafy.fit.model.dao;

import java.util.*;

import com.ssafy.fit.model.dto.Video;

public interface MainDao {
    List<Video> selectInterestViewFitVideo();
    
    List<Video> selectPartFitVideo(String fitPartName);
    
    Video selectVideo(String videoId);
    
    List<Video> selectAllVideo();
    
    List<Video> searchVideo(String keyword);
    
}
