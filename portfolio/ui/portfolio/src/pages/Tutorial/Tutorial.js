import React from 'react';
import './Tutorial.css';

const Tutorial = () => {
  return (
    <div className="tutorial-page">
      <header className="tutorial-header">
        <h1>Tutorials</h1>
        <p>Learn new skills and techniques through my curated tutorials.</p>
      </header>
      <section className="tutorial-list">
        <div className="tutorial-item">
          <h2>Building a Portfolio with React</h2>
          <p>A step-by-step guide to creating a modern portfolio website.</p>
          <button>Read More</button>
        </div>
        <div className="tutorial-item">
          <h2>Introduction to JavaScript ES6</h2>
          <p>Master the fundamentals of modern JavaScript with this beginner-friendly tutorial.</p>
          <button>Read More</button>
        </div>
      </section>
    </div>
  );
};

export default Tutorial;
