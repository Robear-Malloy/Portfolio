import React from 'react';
import ReactDOM from 'react-dom';
import './ExperienceModal.css';

const ExperienceModal = ({ isOpen, onClose, job }) => {
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
          {job.position} at {job.company}
        </h2>
        <p className="modal-dates">
          <em>
            {job.dateStarted} - {job.dateEnded || 'Present'}
          </em>
        </p>
        <ul className="modal-responsibilities-list">
          {job.responsibilities?.map((responsibility, index) => (
            <li key={index}>{responsibility}</li>
          ))}
        </ul>
        <button className="modal-close-bottom" onClick={onClose}>
          Close
        </button>
      </div>
    </div>,
    document.body
  );
};

export default ExperienceModal;
