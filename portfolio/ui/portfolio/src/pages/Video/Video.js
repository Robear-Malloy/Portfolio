import { useContext, useEffect, useState } from 'react';
import { ThemeContext } from '../../utils/ThemeContext';
import { useTranslation } from 'react-i18next';
import './Video.css';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';

const Video = () => {
  const { t, i18n } = useTranslation();
  const { darkMode } = useContext(ThemeContext);
  const [videos, setVideos] = useState([]);
  const [currentVideo, setCurrentVideo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchVideos = async () => {
      try {
        const apiUrl = process.env.REACT_APP_API_URL;
        const username = process.env.REACT_APP_API_USERNAME;
        const password = process.env.REACT_APP_API_PASSWORD;
        const encodedAuth = btoa(`${username}:${password}`); 

        const response = await fetch(`${apiUrl}video?lang=${i18n.language}`, {
          headers: {
            'Authorization': `Basic ${encodedAuth}`,
          },
        });

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        setVideos(data);
        setCurrentVideo(data[0]);
        setLoading(false);
      } catch (err) {
        console.error('Failed to fetch videos:', err);
        setError(t('video.error'));
        setLoading(false);
      }
    };

    fetchVideos();
  }, [i18n.language, t]); 

  const getVideoId = (link) => {
    const url = new URL(link);
    if (url.hostname === 'www.youtube.com' && url.pathname.startsWith('/embed/')) {
      return url.pathname.split('/embed/')[1];
    }
    return null;
  };

  if (loading) {
    return <div className="loading">{t('video.loading')}</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className={`video-page ${darkMode ? 'dark-mode' : 'light-mode'}`}>
      <Header />
      <header className="video-header">
        <h1>{t('video.title')}</h1>
        <p>{t('video.text')}</p>
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
                  src={`https://img.youtube.com/vi/${videoId}/0.jpg`}
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
