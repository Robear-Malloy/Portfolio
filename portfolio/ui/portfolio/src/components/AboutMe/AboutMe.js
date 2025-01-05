import React, { useState, useEffect } from 'react';
import './AboutMe.css';
import languagesImage from '../../assets/images/cat.JPEG';
import climbingImage from '../../assets/images/hiking.JPEG';
import travelingImage from '../../assets/images/travel.JPEG';

const AboutMe = () => {
  const [currentSlide, setCurrentSlide] = useState(0);

  const slides = [
    {
      title: 'My Cats',
      content: 'My fiance and I rescued two kittens that have now grown into not-so-little rowdy cats that join us in about everything we do. (Even programming lol!). They are a huge part of our little family and are just the beginning.',
      image: languagesImage,
    },
    {
      title: 'Love Hiking the Outdoors',
      content: 'When I\'m not at my computer working on projects, I like to take time to step away from the keyboard and enjoy nature. In Florida, I don\'t have many opportunities for climbs, so I try to go somewhere new every year',
      image: climbingImage, 
    },
    {
      title: 'Adventures in Traveling',
      content: 'Traveling gives me an opportunity to experience life outside my own bubble, and allows me to talk with people from all walks of life. Seeing new places is great, but my favorite is getting to practice language skills with the locals.',
      image: travelingImage,
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
