import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import axios from 'axios';
import './Experience.css';
import ExperienceModal from './ExperienceModal';

const Experience = () => {
  const { t } = useTranslation();
  const [experiences, setExperiences] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedJob, setSelectedJob] = useState(null);

  const username = process.env.REACT_APP_API_USERNAME;
  const password = process.env.REACT_APP_API_PASSWORD;
  const encodedAuth = btoa(`${username}:${password}`);

  useEffect(() => {
    const fetchExperiences = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/experiences/isFeatured', {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${encodedAuth}`,
          },
        });

        const data = await response.json();

        if (data && data.length > 0) {
          setExperiences(data);
        } else {
          setError(t('experience.noneFound'));
        }
      } catch (err) {
        console.error('Error fetching experience data:', err);
        setError(t('experience.error'));
      } finally {
        setLoading(false);
      }
    };

    fetchExperiences();
  }, [encodedAuth]);

  if (loading) {
    return <div className="experience-section">{t('experience.loading')}</div>;
  }

  if (error) {
    return <div className="experience-section">{error}</div>;
  }

  const handleItemClick = (job) => {
    setSelectedJob(job);
    setIsModalOpen(true);
  };

  return (
    <section className="experience-section">
      <h2 className="section-title">{t('experience.title')}</h2>
      <div className="experience-list">
        {experiences.map((experience) => (
          <div
            className="experience-item"
            key={experience.id}
            onClick={() => handleItemClick(experience)}
          >
            <h3 className="experience-position">{experience.position}</h3>
            <h4 className="experience-company">{experience.company}</h4>
            <p className="experience-dates">
              <em>
                {experience.dateStarted} - {experience.dateEnded || 'Present'}
              </em>
            </p>
          </div>
        ))}
      </div>
      <ExperienceModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        job={selectedJob}
      />
      <div className="button-container">
        <a href="http://localhost:3000/experiences" className="no-underline">
          <button className="learn-more-button">{t('experience.button')}</button>
        </a>
      </div>
    </section>
  );
};

export default Experience;
