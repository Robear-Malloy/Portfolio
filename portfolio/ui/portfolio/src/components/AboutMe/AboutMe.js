import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import './AboutMe.css';
import catImage from '../../assets/images/cat.JPEG';
import climbingImage from '../../assets/images/hiking.JPEG';
import travelingImage from '../../assets/images/travel.JPEG';

const AboutMe = () => {
  const { t } = useTranslation();
  const [currentSlide, setCurrentSlide] = useState(0);

  const slides = [
    {
      title: t('aboutMe.card1.title'),
      content: t('aboutMe.card1.content'),
      image: catImage,
    },
    {
      title: t('aboutMe.card2.title'),
      content: t('aboutMe.card2.content'),
      image: climbingImage, 
    },
    {
      title: t('aboutMe.card3.title'),
      content: t('aboutMe.card3.content'),
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
      <h2>{t('aboutMe.title')}</h2>
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
