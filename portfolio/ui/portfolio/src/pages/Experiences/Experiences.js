import React from 'react';
import './Experiences.css';
import Header from '../../components/Header/Header';

const Experiences = () => {
  return (
    <div className="experiences-page">
      <Header />
      <header className="experiences-header">
        <h1>My Experiences</h1>
        <p>A showcase of my professional journey and achievements.</p>
      </header>
      <section className="experiences-list">
        <div className="experience-item">
          <h2>Software Engineer at TechCorp</h2>
          <p>Developed scalable web applications and led a team of developers to build a real-time analytics platform.</p>
        </div>
        <div className="experience-item">
          <h2>Frontend Developer at WebWorks</h2>
          <p>Designed and implemented responsive user interfaces for e-commerce platforms.</p>
        </div>
      </section>
    </div>
  );
};

export default Experiences;
