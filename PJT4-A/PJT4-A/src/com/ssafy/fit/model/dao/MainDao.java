package com.ssafy.fit.model.dao;

import java.util.*;

import com.ssafy.fit.model.dto.Video;

public interface MainDao {
    List<Video> selectInterestViewFitVideo();
    
    List<Video> selectPartFitVideo(String fitPartName);
    
    Video selectVideo(String videoId);
    
    List<Video> selectAllVideo();
    void divideList(int L, int R, List<Video> sortedList);
    void mergeList(int L, int mid, int R, List<Video> sortedList);
    
    List<Video> searchVideo(String keyword);
    
}
