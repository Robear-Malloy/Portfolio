import { useCallback, useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import './EducationSection.css';
import EducationItem from './EducationItem';
import EducationModal from './EducationModal';

const EducationSection = () => {
  const { t, i18n } = useTranslation();
  const [educationData, setEducationData] = useState([]);
  const [selectedEducationId, setSelectedEducationId] = useState(null);
  const [certifications, setCertifications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const apiUrl = process.env.REACT_APP_API_URL;
  const username = process.env.REACT_APP_API_USERNAME;
  const password = process.env.REACT_APP_API_PASSWORD;
  const encodedAuth = btoa(`${username}:${password}`);

  const fetchData = useCallback(async (language) => {
    try {
      setLoading(true);
      const [educationResponse, certificationResponse] = await Promise.all([
        fetch(`${apiUrl}education?lang=${language}`, {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${encodedAuth}`,
          },
        }),
        fetch(`${apiUrl}certification?lang=${language}`, {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${encodedAuth}`,
          },
        }),
      ]);

      if (!educationResponse.ok || !certificationResponse.ok) {
        throw new Error('Failed to fetch data');
      }

      const educationData = await educationResponse.json();
      const certificationData = await certificationResponse.json();

      setEducationData(educationData);
      setCertifications(certificationData);
    } catch (err) {
      setError(t('educationSection.error'));
      console.error('Error fetching data:', err);
    } finally {
      setLoading(false);
    }
  }, [encodedAuth, t, apiUrl]); 

  useEffect(() => {
    fetchData(i18n.language);

    const handleLanguageChange = (lng) => {
      fetchData(lng);
    };

    i18n.on('languageChanged', handleLanguageChange);

    return () => {
      i18n.off('languageChanged', handleLanguageChange);
    };
  }, [i18n, fetchData]);

  if (loading) {
    return <div className="education-section">{t('educationSection.loading')}</div>;
  }

  if (error) {
    return <div className="education-section">{error}</div>;
  }

  const handleItemClick = (id) => {
    setSelectedEducationId(id);
    setIsModalOpen(true);
  };

  return (
    <section className="education-section">
      <h2 className="section-title">{t('educationSection.title')}</h2>
      <div className="education-list">
        {educationData.map((edu) => (
          <div key={edu.id} onClick={() => handleItemClick(edu.id)}>
            <EducationItem
              degree={edu.degree}
              school={edu.school}
              gpa={edu.gpa}
              dateStarted={edu.dateStarted}
              dateEnded={edu.dateEnded}
            />
          </div>
        ))}
      </div>

      <EducationModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        educationId={selectedEducationId}
      />

      <h3 className="certifications-title">{t('educationSection.certs')}</h3>
      <ul className="certifications-list">
        {certifications.map((cert) => (
          <li key={cert.id} className="certification-item">
            <span className="certification-name">{cert.name}</span>
            <span className="certification-date">{cert.dateCompleted}</span>
          </li>
        ))}
      </ul>
    </section>
  );
};

export default EducationSection;
