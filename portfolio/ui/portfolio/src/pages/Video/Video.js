import React, { useContext, useEffect, useState } from 'react';
import { ThemeContext } from '../../utils/ThemeContext';
import './Video.css';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';

const Video = () => {
  const { darkMode } = useContext(ThemeContext);
  const [videos, setVideos] = useState([]);
  const [currentVideo, setCurrentVideo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchVideos = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/video');
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        setVideos(data);
        setCurrentVideo(data[0]);
        setLoading(false);
      } catch (err) {
        console.error('Failed to fetch videos:', err);
        setError('Failed to load videos. Please try again later.');
        setLoading(false);
      }
    };

    fetchVideos();
  }, []);

  const getVideoId = (link) => {
    const url = new URL(link);
    if (url.hostname === 'www.youtube.com' && url.pathname.startsWith('/embed/')) {
      return url.pathname.split('/embed/')[1];
    }
    return null;
  };
  
  if (loading) {
    return <div className="loading">Loading videos...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className={`video-page ${darkMode ? 'dark-mode' : 'light-mode'}`}>
      <Header />
      <header className="video-header">
        <h1>Featured Video</h1>
        <p>Explore video tutorials and projects I’ve worked on.</p>
      </header>
      <section className="video-thumbnails">
        <div className="thumbnails-container">
          {videos.map((video) => {
            const videoId = getVideoId(video.link);
            return (
              <div
                key={video.id}
                className="thumbnail-item"
                onClick={() => setCurrentVideo(video)}
              >
                <img
                  src={`https://img.youtube.com/vi/${getVideoId(video.link)}/0.jpg`}
                  alt={video.title}
                  className="thumbnail-image"
                />
                <p>{video.title}</p>
              </div>
            );
          })}
        </div>
      </section>
      {currentVideo && (
        <section className="video-content">
          <div className="video-item">
            <h3>{currentVideo.title}</h3>
            <iframe
              width="1903"
              height="776"
              src={`https://www.youtube.com/embed/${getVideoId(currentVideo.link)}`}
              title={currentVideo.title}
              frameBorder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
              referrerPolicy="strict-origin-when-cross-origin"
              allowFullScreen
            ></iframe>
            <p>{currentVideo.description}</p>
          </div>
        </section>
      )}
      <Footer />
    </div>
  );
};

export default Video;
