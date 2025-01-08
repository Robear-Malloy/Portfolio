import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import ReactDOM from 'react-dom';
import axios from 'axios';
import './ExperienceModal.css';

const ExperienceModal = ({ isOpen, onClose, job }) => {
  const { t } = useTranslation();
  const [responsibilities, setResponsibilities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const username = process.env.REACT_APP_API_USERNAME;
  const password = process.env.REACT_APP_API_PASSWORD;
  const encodedAuth = btoa(`${username}:${password}`);

  useEffect(() => {
    if (!job || !job.id) return;

    const fetchResponsibilities = async () => {
      setLoading(true);
      try {
        const response = await axios.get(`http://localhost:8080/api/experience-description/${job.id}`, {
          headers: {
            'Authorization': `Basic ${encodedAuth}`, 
          },
        });
        setResponsibilities(response.data);
      } catch (err) {
        setError(t('experienceModal.error'));
        console.error('Error fetching responsibilities:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchResponsibilities();
  }, [job, encodedAuth]);

  if (!isOpen || !job) {
    return null;
  }

  return ReactDOM.createPortal(
    <div className="experience-modal-overlay">
      <div className="experience-modal">
        <button className="modal-close-button" onClick={onClose}>
          &times;
        </button>
        <h2 className="modal-title">
          {job.position} {t('experienceModal.at')} {job.company}
        </h2>
        <p className="modal-dates">
          <em>
            {job.dateStarted} - {job.dateEnded || 'Present'}
          </em>
        </p>
        {loading ? (
          <p>{t('experienceModal.loading')}</p>
        ) : error ? (
          <p>{error}</p>
        ) : (
          <ul className="modal-responsibilities-list">
            {responsibilities.map((responsibility) => (
              <li key={responsibility.id}>{responsibility.description}</li>
            ))}
          </ul>
        )}
        <button className="modal-close-bottom" onClick={onClose}>
        {t('experienceModal.button')}
        </button>
      </div>
    </div>,
    document.body
  );
};

export default ExperienceModal;
