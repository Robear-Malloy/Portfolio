import React from 'react';
import './MainPage.css';

const MainPage = () => {
  return (
    <div className="main-page">
      <header className="hero-section">
        <h1 className="hero-title">Welcome to My Portfolio</h1>
        <p className="hero-subtitle">Showcasing my projects, experiences, and tutorials.</p>
        <button className="cta-button">Explore More</button>
      </header>
      <section className="about-section">
        <h2>About Me</h2>
        <p>
          Hi, I'm Robert Malloy, a passionate developer with a knack for creating engaging and functional
          web applications. Check out my experiences, projects, and tutorials to learn more about my journey.
        </p>
      </section>
    </div>
  );
};

export default MainPage;
