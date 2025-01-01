import React from 'react';
import ReactDOM from 'react-dom';
import './EducationModal.css';

const EducationModal = ({ isOpen, onClose }) => {
  if (!isOpen) {
    return null;
  }

  return ReactDOM.createPortal(
    <div className="education-modal-overlay">
      <div className="education-modal">
        <button className="modal-close-button" onClick={onClose}>
          &times;
        </button>
        <h2 className="modal-title">Related Courses</h2>
        <ul className="modal-courses-list">
          <li>Introduction to Computer Science</li>
          <li>Data Structures and Algorithms</li>
          <li>Operating Systems</li>
          <li>Database Management Systems</li>
          <li>Software Engineering Principles</li>
        </ul>
        <button className="modal-close-bottom" onClick={onClose}>
          Close
        </button>
      </div>
    </div>,
    document.body 
  );
};

export default EducationModal;
