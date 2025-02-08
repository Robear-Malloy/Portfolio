import { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import './Footer.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGithub, faLinkedin, faYoutube } from '@fortawesome/free-brands-svg-icons';

const Footer = () => {
  const { t } = useTranslation();
  const [privacyModalOpen, setPrivacyModalOpen] = useState(false);
  const [healthStatus, setHealthStatus] = useState(null);

  const togglePrivacyModal = () => {
    setPrivacyModalOpen(!privacyModalOpen);
  };

  useEffect(() => {
    const fetchHealthStatus = async () => {
      try {
        const username = process.env.REACT_APP_API_USERNAME;
        const password = process.env.REACT_APP_API_PASSWORD;
        const encodedAuth = btoa(`${username}:${password}`);

        const response = await fetch('http://localhost:8080/api/health', {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${encodedAuth}`,
          },
        });

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
          {t('footer.privacyPolicy.title')}
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
            <span className="health-dot healthy"></span> {t('footer.statusHealthy')}
          </div>
        ) : healthStatus === 'unhealthy' ? (
          <div className="health-status">
            <span className="health-dot unhealthy"></span> {t('footer.statusUnhealthy')}
          </div>
        ) : (
          <div className="health-status">{t('footer.loading')}</div>
        )}
      </div>

      <div className="footer-about">
      {t('footer.aboutSite')}
      </div>

      {privacyModalOpen && (
        <div className="privacy-modal">
          <div className="privacy-modal-content">
            <h2>{t('footer.privacyPolicy.title')}</h2>
            <p>
            {t('footer.privacyPolicy.privacyValue')}
            </p>
            <ul>
              <li>{t('footer.privacyPolicy.use1')}</li>
              <li>{t('footer.privacyPolicy.use2')}</li>
            </ul>
            <p>
            {t('footer.privacyPolicy.dataStorage')}
            </p>
            <p>
            {t('footer.privacyPolicy.sharePolicy')}
            </p>
            <button onClick={togglePrivacyModal}>{t('footer.privacyPolicy.button')}</button>
          </div>
        </div>
      )}
    </footer>
  );
};

export default Footer;
