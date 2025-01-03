package com.robear.portfolio.service;

import com.robear.portfolio.exception.VideoNotFoundException;
import com.robear.portfolio.model.Video;
import com.robear.portfolio.repository.VideoRepository;
import com.robear.portfolio.service.interfaces.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService implements IVideoService {
    private static Logger logger = LoggerFactory.getLogger(VideoService.class);
    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video addVideo(Video video) {
        try {
            logger.info("Saving new Video: {}", video);
            return videoRepository.save(video);
        } catch (Exception e) {
            logger.error("Error saving new Video to database");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Video> getAllVideos() {
        try {
            logger.info("Returning all videos.");
            List<Video> videos = videoRepository.findAll();
            if (videos.isEmpty()) {
                throw new VideoNotFoundException("None Found");
            }
            return videos;
        } catch (VideoNotFoundException e) {
            logger.warn("No videos found");
            throw e;
        } catch (Exception e) {
            logger.error("Error retrieving all videos.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Video updateVideo(Video video) {
        try {
            logger.info("Updating Video");

            Video existingVideo = videoRepository.findById(video.getId())
                    .orElseThrow(() -> new VideoNotFoundException(video.getId()));

            if (video.getTitle() != null && !video.getTitle().isEmpty()) {
                existingVideo.setTitle(video.getTitle());
            }
            if (video.getDescription() != null && !video.getDescription().isEmpty()) {
                existingVideo.setDescription(video.getDescription());
            }
            if (video.getLink() != null && !video.getLink().isEmpty()) {
                existingVideo.setLink(video.getLink());
            }

            return videoRepository.save(existingVideo);
        } catch (VideoNotFoundException e) {
            logger.warn("Video not found with id: {}", video.getId());
            throw e;
        } catch (Exception e) {
            logger.error("Error Updating Video");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteVideo(Long id) {
        try {
            Video existingVideo = videoRepository.findById(id)
                    .orElseThrow(() -> new VideoNotFoundException(id));
            videoRepository.deleteById(existingVideo.getId());
            logger.info("Deleted video from database id: {}", id);
        } catch (VideoNotFoundException e) {
            logger.warn("No Video found to delete");
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred deleting id.");
            throw new RuntimeException(e);
        }
    }
}
