import React from 'react';
import { useTranslation } from 'react-i18next';
import './Hero.css';
import pfpImage from "../../assets/images/hero.JPEG";

const Hero = () => {
  const { t } = useTranslation();

  return (
    <header className="hero-section">
      <div className="hero-content">
        <img src={pfpImage} alt="Robert Malloy" className="hero-image" />
        <h1 className="hero-title">{t('hero.title')}</h1>
        <h2 className="hero-subtitle">{t('hero.subtitle')}</h2>
        <p className="hero-description">{t('hero.description')}</p>
        <div className="cta-buttons">
          <button
            className="cta-button"
            onClick={() =>
              window.scrollTo({
                top: document.getElementById('contact').offsetTop,
                behavior: 'smooth',
              })
            }
          >
            {t('hero.contactButton')}
          </button>
          <button
            className="cta-button"
            onClick={() =>
              window.scrollTo({
                top: document.getElementById('projects').offsetTop,
                behavior: 'smooth',
              })
            }
          >
            {t('hero.projectsButton')}
          </button>
        </div>
      </div>
    </header>
  );
};

export default Hero;
