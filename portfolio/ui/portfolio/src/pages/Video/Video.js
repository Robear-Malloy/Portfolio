import React, { useContext, useState } from 'react';
import { ThemeContext } from '../../utils/ThemeContext';
import './Video.css';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';

const Video = () => {
  const { darkMode } = useContext(ThemeContext);
  const [currentVideo, setCurrentVideo] = useState({
    title: 'Augmented Reality World Builder Game Demo',
    src: 'https://www.youtube.com/embed/n5ujR4VVQkE',
    description: 'This demo showcases an augmented reality world builder game, highlighting its features and gameplay.'
  });

  const videos = [
    {
      title: 'Augmented Reality World Builder Game Demo',
      src: 'https://www.youtube.com/embed/n5ujR4VVQkE',
      description: 'This demo showcases an augmented reality world builder game, highlighting its features and gameplay.'
    },
    {
      title: 'Final Project Submission',
      src: 'https://www.youtube.com/embed/aEPRrOdGc5c',
      description: 'Final project submission demonstrating the culmination of development efforts.'
    }
  ];

  return (
    <div className={`video-page ${darkMode ? 'dark-mode' : 'light-mode'}`}>
      <Header />
      <header className="video-header">
        <h1>Featured Video</h1>
        <p>Explore video tutorials and projects I’ve worked on.</p>
      </header>
      <section className="video-thumbnails">
        <div className="thumbnails-container">
          {videos.map((video, index) => (
            <div
              key={index}
              className="thumbnail-item"
              onClick={() => setCurrentVideo(video)}
            >
              <img
                src={`https://img.youtube.com/vi/${video.src.split('/embed/')[1]}/0.jpg`}
                alt={video.title}
                className="thumbnail-image"
              />
              <p>{video.title}</p>
            </div>
          ))}
        </div>
      </section>
      <section className="video-content">
        <div className="video-item">
          <h3>{currentVideo.title}</h3>
          <iframe
            width="1903"
            height="776"
            src={currentVideo.src}
            title={currentVideo.title}
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerPolicy="strict-origin-when-cross-origin"
            allowFullScreen
          ></iframe>
          <p>{currentVideo.description}</p>
        </div>
      </section>
      <Footer />
    </div>
  );
};

export default Video;
