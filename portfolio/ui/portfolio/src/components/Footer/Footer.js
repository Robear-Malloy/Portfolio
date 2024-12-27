import React, { useState } from 'react';
import './Footer.css';

const Footer = () => {
  const [privacyModalOpen, setPrivacyModalOpen] = useState(false);

  const togglePrivacyModal = () => {
    setPrivacyModalOpen(!privacyModalOpen);
  };

  return (
    <footer className="footer">
      <div className="footer-left">
        <button className="privacy-policy" onClick={togglePrivacyModal}>
          Privacy Policy
        </button>
      </div>

      <div className="footer-center">
        <a href="https://github.com" target="_blank" rel="noopener noreferrer" className="footer-icon">
          GitHub
        </a>
        <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer" className="footer-icon">
          LinkedIn
        </a>
        <a href="https://youtube.com" target="_blank" rel="noopener noreferrer" className="footer-icon">
          YouTube
        </a>
      </div>

      <div className="footer-right">Healthy</div>

      <div className="footer-about">
        About this site: Built using React on a Java Spring API and PostgreSQL db
      </div>

      {privacyModalOpen && (
        <div className="privacy-modal">
          <div className="privacy-modal-content">
            <h2>Privacy Policy</h2>
            <p>
              This is a sample privacy policy. Please replace this text with your actual privacy policy.
            </p>
            <button onClick={togglePrivacyModal}>Close</button>
          </div>
        </div>
      )}
    </footer>
  );
};

export default Footer;
