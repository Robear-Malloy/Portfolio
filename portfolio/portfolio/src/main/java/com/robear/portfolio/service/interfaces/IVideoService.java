package com.robear.portfolio.service.interfaces;

import com.robear.portfolio.model.Video;
import java.util.List;

public interface IVideoService {
    Video addVideo(Video video);
    List<Video> getAllVideos();
    Video updateVideo(Video video);
    void deleteVideo(Long id);
}
