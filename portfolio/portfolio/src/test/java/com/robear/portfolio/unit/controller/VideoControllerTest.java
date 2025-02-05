package com.robear.portfolio.unit.controller;

import com.robear.portfolio.controller.VideoController;
import com.robear.portfolio.exception.VideoNotFoundException;
import com.robear.portfolio.model.Video;
import com.robear.portfolio.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VideoControllerTest {

    @InjectMocks
    private VideoController videoController;

    @Mock
    private VideoService videoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateVideoSuccessfully() {
        Video video = createTestVideo();
        when(videoService.addVideo(any(Video.class))).thenReturn(video);

        ResponseEntity<Video> response = videoController.createVideo(video);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(video.getId());
    }

    @Test
    public void testCreateVideoWhenThrowsException() {
        Video video = createTestVideo();
        when(videoService.addVideo(any(Video.class))).thenThrow(new RuntimeException("Service Error"));

        ResponseEntity<Video> response = videoController.createVideo(video);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testGetAllVideosWhenVideosExist() {
        Video video = createTestVideo();
        List<Video> videos = Collections.singletonList(video);
        when(videoService.getAllVideos("en")).thenReturn(videos);

        ResponseEntity<List<Video>> response = videoController.getAllVideos("en");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getId()).isEqualTo(video.getId());
    }

    @Test
    public void testGetAllVideosWhenThrowsException() {
        when(videoService.getAllVideos("en")).thenThrow(new RuntimeException("Service Error"));

        ResponseEntity<List<Video>> response = videoController.getAllVideos("en");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateVideoSuccessfully() {
        Video video = createTestVideo();
        when(videoService.updateVideo(any(Video.class))).thenReturn(video);

        ResponseEntity<Video> response = videoController.updateVideo(video);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(video.getId());
    }

    @Test
    public void testUpdateVideoWhenNotFound() {
        Video video = createTestVideo();
        when(videoService.updateVideo(any(Video.class))).thenThrow(new VideoNotFoundException("Not Found"));

        ResponseEntity<Video> response = videoController.updateVideo(video);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testUpdateVideoWhenThrowsException() {
        Video video = createTestVideo();
        when(videoService.updateVideo(any(Video.class))).thenThrow(new RuntimeException("Service Error"));

        ResponseEntity<Video> response = videoController.updateVideo(video);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteVideoSuccessfully() {
        doNothing().when(videoService).deleteVideo(1L);

        ResponseEntity<Void> response = videoController.deleteVideo(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(videoService).deleteVideo(1L);
    }

    @Test
    public void testDeleteVideoWhenNotFound() {
        doThrow(new VideoNotFoundException("Not Found")).when(videoService).deleteVideo(1L);

        ResponseEntity<Void> response = videoController.deleteVideo(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void testDeleteVideoWhenThrowsException() {
        doThrow(new RuntimeException("Service Error")).when(videoService).deleteVideo(1L);

        ResponseEntity<Void> response = videoController.deleteVideo(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    private Video createTestVideo() {
        Video video = new Video();
        video.setId(1L);
        video.setTitle("Test Video");
        video.setDescription("Test Description");
        video.setLink("http://example.com/video");
        return video;
    }
}
