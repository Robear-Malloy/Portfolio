import React from 'react';
import ReactDOM from 'react-dom';
import { useTranslation } from 'react-i18next';
import './TechStackModal.css';

const TechStackModal = ({ isOpen, onClose, techStack, skills }) => {
  const { t } = useTranslation();

  if (!isOpen || !techStack) {
    return null;
  }
  
  const getSkillName = (skillId) => {
    const skill = skills.find((skill) => skill.id === skillId);
    return skill ? skill.name : `Unknown Skill (ID: ${skillId})`;
  };

  return ReactDOM.createPortal(
    <div className="tech-stack-modal-overlay">
      <div className="tech-stack-modal">
        <button className="modal-close-button" onClick={onClose}>
          &times;
        </button>
        <h2>{t('techStack.title')}</h2>
        <ul>
          {techStack.map((tech) => (
            <li key={tech.id}>{getSkillName(tech.skillId)}</li>
          ))}
        </ul>
        <button onClick={onClose}>{t('techStack.button')}</button>
      </div>
    </div>,
    document.body
  );
};

export default TechStackModal;
