package com.robear.portfolio.controller.interfaces;

import com.robear.portfolio.model.Video;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IVideoController {
    ResponseEntity<Video> createVideo(Video video);
    ResponseEntity<List<Video>> getAllVideos();
    ResponseEntity<Video> updateVideo(Video video);
    ResponseEntity<Void> deleteVideo(Long id);
}
