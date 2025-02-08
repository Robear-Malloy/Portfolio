import { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import ReactDOM from 'react-dom';
import './EducationModal.css';

const EducationModal = ({ isOpen, onClose, educationId }) => {
  const { t, i18n } = useTranslation();
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const apiUrl = process.env.REACT_APP_API_URL;
  const username = process.env.REACT_APP_API_USERNAME;
  const password = process.env.REACT_APP_API_PASSWORD;
  const encodedAuth = btoa(`${username}:${password}`);

  useEffect(() => {
    if (!educationId) return;

    const fetchCourses = async () => {
      setLoading(true);
      setError(null); 
      try {
        const response = await fetch(
          `${apiUrl}course/${educationId}?lang=${i18n.language}`,
          {
            method: 'GET',
            headers: {
              'Authorization': `Basic ${encodedAuth}`,
            },
          }
        );

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();

        if (data && data.length > 0) {
          setCourses(data);
        } else {
          setError(t('educationModal.noneFound'));
        }
      } catch (err) {
        console.error('Error fetching related courses:', err);
        setError(t('educationModal.error'));
      } finally {
        setLoading(false);
      }
    };

    fetchCourses();
  }, [educationId, i18n.language, encodedAuth, t, apiUrl]);

  if (!isOpen) {
    return null;
  }

  return ReactDOM.createPortal(
    <div className="education-modal-overlay">
      <div className="education-modal">
        <button className="modal-close-button" onClick={onClose}>
          &times;
        </button>
        <h2 className="modal-title">{t('educationModal.title')}</h2>
        {loading ? (
          <p>{t('educationModal.loading')}</p>
        ) : error ? (
          <p>{error}</p>
        ) : (
          <ul className="modal-courses-list">
            {courses.map((course) => (
              <li key={course.id}>{course.name}</li>
            ))}
          </ul>
        )}
        <button className="modal-close-bottom" onClick={onClose}>
          {t('educationModal.button')}
        </button>
      </div>
    </div>,
    document.body
  );
};

export default EducationModal;
