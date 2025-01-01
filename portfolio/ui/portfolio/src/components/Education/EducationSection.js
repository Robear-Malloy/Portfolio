import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './EducationSection.css';
import EducationItem from './EducationItem';
import EducationModal from './EducationModal';

const EducationSection = () => {
  const [educationData, setEducationData] = useState([]);
  //const [certifications, setCertifications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchEducation = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/education');
        setEducationData(response.data);
        //setCertifications(response.data.certifications);
      } catch (err) {
        setError('Failed to fetch education data.');
        console.error('Error fetching education data:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchEducation();
  }, []);

  if (loading) {
    return <div className="education-section">Loading education data...</div>;
  }

  if (error) {
    return <div className="education-section">{error}</div>;
  }

  const handleItemClick = () => {
    setIsModalOpen(true);
  };

  return (
    <section className="education-section">
      <h2 className="section-title">Education</h2>
      <div className="education-list">
        {educationData.map((edu) => (
          <div key={edu.id} onClick={handleItemClick}>
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

      <EducationModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} />

      <h3 className="certifications-title">Certifications</h3>
      <ul className="certifications-list">
        <li className="certification-item">
          <span className="certification-name">Azure 900</span>
          <span className="certification-date">03/12/24</span>
        </li>
        <li className="certification-item">
          <span className="certification-name">CompTIA Security+</span>
          <span className="certification-date">09/12/23</span>
        </li>
        <li className="certification-item">
          <span className="certification-name">PSD 1</span>
          <span className="certification-date">12/12/24</span>
        </li>
      </ul>
      {/* <ul className="certifications-list">
        {certifications.map((cert, index) => (
          <li key={index} className="certification-item">
            <span className="certification-name">{cert.name}</span>
            <span className="certification-date">{cert.date}</span>
          </li>
        ))}
      </ul> */}
    </section>
  );
};

export default EducationSection;
