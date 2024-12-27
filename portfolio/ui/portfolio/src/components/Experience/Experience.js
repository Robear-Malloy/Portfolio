import React from 'react';
import './Experience.css';

const Experience = () => {
  return (
    <div className="experience-section">
      <h2>Experience</h2>
      <div className="experience-item">
        <h3>Software Engineer - TechCorp</h3>
        <p><em>(2022 - Present)</em></p>
      </div>
      <div className="experience-item">
        <h3>Intern Developer - DevSolutions</h3>
        <p><em>(2021 - 2022)</em></p>
      </div>
      <button className="learn-more-button">Learn More</button>
    </div>
  );
};

export default Experience;
