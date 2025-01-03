package com.robear.portfolio.controller;

import com.robear.portfolio.controller.interfaces.IVideoController;
import com.robear.portfolio.exception.VideoNotFoundException;
import com.robear.portfolio.model.Video;
import com.robear.portfolio.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController implements IVideoController {
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

    @PostMapping
    @Override
    public ResponseEntity<Video> createVideo(Video video) {
        try {
            logger.info("Creating a new video: {}", video);
            Video result = videoService.addVideo(video);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Unexpected error while creating a video.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Video>> getAllVideos() {
        try {
            logger.info("Getting All Videos.");
            List<Video> result = videoService.getAllVideos();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception e) {
            logger.error("Error getting all videos.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Video> updateVideo(Video video) {
        try {
            logger.info("Updating video: {} with content: {}", video.getId(), video);
            Video result = videoService.updateVideo(video);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (VideoNotFoundException e) {
            logger.warn("No video was found with id {}", video.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error attempting to update video {}", video.getId());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteVideo(Long id) {
        try {
            logger.info("Deleting video id: {}", id);
            videoService.deleteVideo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VideoNotFoundException e) {
            logger.warn("No video found for id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred deleting video.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
