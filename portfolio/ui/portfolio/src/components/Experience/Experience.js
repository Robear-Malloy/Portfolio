import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Experience.css';
import ExperienceModal from './ExperienceModal';

const Experience = () => {
  const [experiences, setExperiences] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedJob, setSelectedJob] = useState(null);

  useEffect(() => {
    const fetchExperiences = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/experiences');
        setExperiences(response.data);
      } catch (err) {
        setError('Failed to fetch experience data.');
        console.error('Error fetching experience data:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchExperiences();
  }, []);

  if (loading) {
    return <div className="experience-section">Loading experiences...</div>;
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
      <h2 className="section-title">Experience</h2>
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
          <button className="learn-more-button">Learn More</button>
        </a>
      </div>
    </section>
  );
};

export default Experience;
