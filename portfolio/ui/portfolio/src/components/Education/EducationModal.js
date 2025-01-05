import React, { useState, useEffect } from 'react';
import ReactDOM from 'react-dom';
import './EducationModal.css';

const EducationModal = ({ isOpen, onClose, educationId }) => {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const username = process.env.REACT_APP_API_USERNAME;
  const password = process.env.REACT_APP_API_PASSWORD;
  const encodedAuth = btoa(`${username}:${password}`);

  useEffect(() => {
    if (!educationId) return;

    const fetchCourses = async () => {
      setLoading(true);
      try {
        const response = await fetch(`http://localhost:8080/api/course/${educationId}`, {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${encodedAuth}`,
          },
        });

        const data = await response.json();
        console.log(data);

        if (data && data.length > 0) {
          setCourses(data);
        } else {
          setError('No courses found.');
        }
      } catch (err) {
        console.error('Error fetching related courses:', err);
        setError('Failed to fetch related courses.');
      } finally {
        setLoading(false);
      }
    };

    fetchCourses();
  }, [educationId, encodedAuth]);

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
        {loading ? (
          <p>Loading courses...</p>
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
          Close
        </button>
      </div>
    </div>,
    document.body
  );
};

export default EducationModal;
