package com.robear.portfolio.unit.service;

import com.robear.portfolio.exception.VideoNotFoundException;
import com.robear.portfolio.model.Video;
import com.robear.portfolio.repository.VideoRepository;
import com.robear.portfolio.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class VideoServiceTest {
    @InjectMocks
    VideoService videoService;

    @Mock
    VideoRepository videoRepository;

    private final Video existingVideo = new Video();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        existingVideo.setId(1L);
        existingVideo.setTitle("Test Video");
        existingVideo.setDescription("Test Description");
        existingVideo.setLink("https://youtube.com/test");
    }

    @Test
    public void testAddVideoWhenSuccessful() {
        when(videoRepository.save(existingVideo)).thenReturn(existingVideo);

        Video result = videoService.addVideo(existingVideo);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(existingVideo.getId());
        verify(videoRepository).save(existingVideo);
    }

    @Test
    public void testAddVideoWhenThrowsException() {
        when(videoRepository.save(existingVideo)).thenThrow(new RuntimeException("Database Error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            videoService.addVideo(existingVideo);
        });

        assertThat(exception.getMessage()).contains("Database Error");
        verify(videoRepository).save(existingVideo);
    }

    @Test
    public void testGetAllVideosWhenSuccessful() {
        List<Video> videos = Collections.singletonList(existingVideo);
        when(videoRepository.findAll()).thenReturn(videos);

        List<Video> result = videoService.getAllVideos();

        assertThat(result).isNotNull();
        assertThat(result.get(0).getId()).isEqualTo(existingVideo.getId());
        verify(videoRepository).findAll();
    }

    @Test
    public void testGetAllVideosWhenNoneFound() {
        when(videoRepository.findAll()).thenReturn(Collections.emptyList());

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            videoService.getAllVideos();
        });

        assertThat(exception.getMessage()).contains("None Found");
        verify(videoRepository).findAll();
    }

    @Test
    public void testGetAllVideosWhenThrowsException() {
        when(videoRepository.findAll()).thenThrow(new RuntimeException("Error retrieving videos"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            videoService.getAllVideos();
        });

        assertThat(exception.getMessage()).contains("Error retrieving videos");
        verify(videoRepository).findAll();
    }

    @Test
    public void testUpdateVideoWhenSuccessful() {
        Video updatedVideo = new Video();
        updatedVideo.setId(existingVideo.getId());
        updatedVideo.setTitle("Updated Title");

        when(videoRepository.findById(existingVideo.getId())).thenReturn(Optional.of(existingVideo));
        when(videoRepository.save(existingVideo)).thenReturn(existingVideo);

        Video result = videoService.updateVideo(updatedVideo);

        assertThat(result.getTitle()).isEqualTo(updatedVideo.getTitle());
        verify(videoRepository).save(existingVideo);
    }

    @Test
    public void testUpdateVideoWhenNotFound() {
        Video updatedVideo = new Video();
        updatedVideo.setId(2L);

        when(videoRepository.findById(updatedVideo.getId())).thenReturn(Optional.empty());

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            videoService.updateVideo(updatedVideo);
        });

        assertThat(exception.getMessage()).contains("Video not found with ID: 2");
        verify(videoRepository).findById(updatedVideo.getId());
    }

    @Test
    public void testUpdateVideoWhenThrowsException() {
        when(videoRepository.findById(existingVideo.getId())).thenThrow(new RuntimeException("Error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            videoService.updateVideo(existingVideo);
        });

        assertThat(exception.getMessage()).contains("Error");
        verify(videoRepository).findById(existingVideo.getId());
    }

    @Test
    public void testDeleteVideoWhenSuccessful() {
        when(videoRepository.findById(existingVideo.getId())).thenReturn(Optional.of(existingVideo));

        videoService.deleteVideo(existingVideo.getId());

        verify(videoRepository).deleteById(existingVideo.getId());
    }

    @Test
    public void testDeleteVideoWhenNotFound() {
        when(videoRepository.findById(1L)).thenReturn(Optional.empty());

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            videoService.deleteVideo(1L);
        });

        assertThat(exception.getMessage()).contains("Video not found with ID: 1");
        verify(videoRepository).findById(1L);
    }

    @Test
    public void testDeleteVideoWhenThrowsException() {
        when(videoRepository.findById(existingVideo.getId())).thenThrow(new RuntimeException("Error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            videoService.deleteVideo(existingVideo.getId());
        });

        assertThat(exception.getMessage()).contains("Error");
        verify(videoRepository).findById(existingVideo.getId());
    }
}
