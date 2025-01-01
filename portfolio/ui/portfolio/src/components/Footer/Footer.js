import React, { useState, useEffect } from 'react';
import './Footer.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGithub, faLinkedin, faYoutube } from '@fortawesome/free-brands-svg-icons';

const Footer = () => {
  const [privacyModalOpen, setPrivacyModalOpen] = useState(false);
  const [healthStatus, setHealthStatus] = useState(null);

  const togglePrivacyModal = () => {
    setPrivacyModalOpen(!privacyModalOpen);
  };

  useEffect(() => {
    const fetchHealthStatus = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/health');
        if (response.ok) {
          setHealthStatus('healthy');
        } else {
          setHealthStatus('unhealthy');
        }
      } catch (error) {
        setHealthStatus('unhealthy');
        console.error('Error fetching health status:', error);
      }
    };

    fetchHealthStatus();
    const interval = setInterval(fetchHealthStatus, 10000); 
    return () => clearInterval(interval); 
  }, []);

  return (
    <footer className="footer">
      <div className="footer-left">
        <button className="privacy-policy" onClick={togglePrivacyModal}>
          Privacy Policy
        </button>
      </div>

      <div className="footer-center">
        <a
          href="https://github.com/Robear-Malloy"
          target="_blank"
          rel="noopener noreferrer"
          className="footer-icon"
        >
          <FontAwesomeIcon icon={faGithub} size="2x" />
        </a>
        <a
          href="https://www.linkedin.com/in/robert-a-malloy/"
          target="_blank"
          rel="noopener noreferrer"
          className="footer-icon"
        >
          <FontAwesomeIcon icon={faLinkedin} size="2x" />
        </a>
        <a
          href="https://youtube.com"
          target="_blank"
          rel="noopener noreferrer"
          className="footer-icon"
        >
          <FontAwesomeIcon icon={faYoutube} size="2x" />
        </a>
      </div>

      <div className="footer-right">
        {healthStatus === 'healthy' ? (
          <div className="health-status">
            <span className="health-dot healthy"></span> Status: Healthy 
          </div>
        ) : healthStatus === 'unhealthy' ? (
          <div className="health-status">
            <span className="health-dot unhealthy"></span> Status: Unhealthy 
          </div>
        ) : (
          <div className="health-status">Checking...</div>
        )}
      </div>

      <div className="footer-about">
        About this site: Built using React on a Java Spring API and PostgreSQL Database
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
