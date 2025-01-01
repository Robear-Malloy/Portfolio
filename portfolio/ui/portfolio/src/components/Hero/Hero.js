import React from 'react';
import './Hero.css';

const Hero = () => {
  return (
    <header className="hero-section">
        <div className="hero-content">
            <img src="path_to_your_image/pfp.png" alt="Robert Malloy" className="hero-image" />
            <h1 className="hero-title">Howdy, I'm Robert</h1>
            <h2 className="hero-subtitle">Fullstack Engineer</h2>
            <p className="hero-description">
            I'm Robert Malloy, a curious software engineer who enjoys working across all areas of development—from embedded systems to web applications, REST APIs to tutorials. Explore my projects, and feel free to reach out if you'd like to chat!
            </p>
            <div className="cta-buttons">
                <button className="cta-button" onClick={() => window.scrollTo({ top: document.getElementById('contact').offsetTop, behavior: 'smooth' })}>
                  Contact Me
                </button>
                <button className="cta-button" onClick={() => window.scrollTo({ top: document.getElementById('projects').offsetTop, behavior: 'smooth' })}>
                  Projects
                </button>
            </div>
        </div>
    </header>
  );
};

export default Hero;
