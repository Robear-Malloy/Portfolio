import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './EducationSection.css';
import EducationItem from './EducationItem';
import EducationModal from './EducationModal';

const EducationSection = () => {
  const [educationData, setEducationData] = useState([]);
  const [selectedEducationId, setSelectedEducationId] = useState(null);
  const [certifications, setCertifications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const username = process.env.REACT_APP_API_USERNAME;
  const password = process.env.REACT_APP_API_PASSWORD;
  const encodedAuth = btoa(`${username}:${password}`);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [educationResponse, certificationResponse] = await Promise.all([
          axios.get('http://localhost:8080/api/education', {
            headers: {
              'Authorization': `Basic ${encodedAuth}`,
            },
          }),
          axios.get('http://localhost:8080/api/certification', {
            headers: {
              'Authorization': `Basic ${encodedAuth}`, 
            },
          }),
        ]);
        setEducationData(educationResponse.data);
        setCertifications(certificationResponse.data);
      } catch (err) {
        setError('Failed to fetch data.');
        console.error('Error fetching data:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [encodedAuth]);

  if (loading) {
    return <div className="education-section">Loading data...</div>;
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
      <h2 className="section-title">Education</h2>
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

      <h3 className="certifications-title">Certifications</h3>
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
