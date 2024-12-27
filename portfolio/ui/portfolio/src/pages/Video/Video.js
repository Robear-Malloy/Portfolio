import React from 'react';
import './Video.css';
import Header from '../../components/Header/Header';

const Video = () => {
  return (
    <div className="video-page">
      <Header />
      <header className="video-header">
        <h1>Featured Videos</h1>
        <p>Explore video tutorials and projects I’ve worked on.</p>
      </header>
      <section className="video-gallery">
        <div className="video-item">
          <h3>Project Walkthrough</h3>
        </div>
        <div className="video-item">
          <h3>React Tutorial</h3>
        </div>
      </section>
    </div>
  );
};

export default Video;
