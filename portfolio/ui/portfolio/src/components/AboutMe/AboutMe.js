import React, { useState, useEffect } from 'react';
import './AboutMe.css';

const AboutMe = () => {
  const [currentSlide, setCurrentSlide] = useState(0);

  const slides = [
    {
      title: 'Passion for Learning Languages',
      content: 'I love diving into new languages, exploring their grammar, and understanding different cultures through words. It’s like unlocking a new way to think and connect with the world.',
      image: 'path/to/languages.jpg', // Replace with your image path
    },
    {
      title: 'Love for Rock Climbing',
      content: 'Rock climbing challenges me both mentally and physically. Reaching the top of a route is incredibly rewarding, and it’s a constant reminder of perseverance and focus.',
      image: 'path/to/climbing.jpg', // Replace with your image path
    },
    {
      title: 'Adventures in Traveling',
      content: 'Traveling opens my eyes to the beauty and diversity of the world. From bustling cities to serene landscapes, every journey is a story waiting to be told.',
      image: 'path/to/traveling.jpg', // Replace with your image path
    },
  ];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentSlide((prevSlide) => (prevSlide + 1) % slides.length);
    }, 10000);

    return () => clearInterval(interval);
  }, [slides.length]);

  const handleDotClick = (index) => {
    setCurrentSlide(index);
  };

  return (
    <div id="about-me" className="about-me-container">
      <h2>About Me</h2>
      <div className="about-me-card">
        <img src={slides[currentSlide].image} alt={slides[currentSlide].title} className="about-me-image" />
        <div className="about-me-content">
          <h2 className="about-me-title">{slides[currentSlide].title}</h2>
          <p className="about-me-description">{slides[currentSlide].content}</p>
        </div>
      </div>

      <div className="dots-container">
        {slides.map((_, index) => (
          <span
            key={index}
            className={`dot ${currentSlide === index ? 'active' : ''}`}
            onClick={() => handleDotClick(index)}
          ></span>
        ))}
      </div>
    </div>
  );
};

export default AboutMe;
