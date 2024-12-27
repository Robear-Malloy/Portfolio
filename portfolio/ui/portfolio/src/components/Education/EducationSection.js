import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './EducationSection.css';
import EducationItem from './EducationItem';

const EducationSection = () => {
  const [educationData, setEducationData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchEducation = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/education');
        setEducationData(response.data);
      } catch (err) {
        setError('Failed to fetch education data.');
        console.error('Error fetching education data:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchEducation();
  }, []);

  return (
    <section className="education-section">
      <h2>Education</h2>
      {loading ? (
        <p>Loading education data...</p>
      ) : error ? (
        <p>{error}</p>
      ) : (
        educationData.map((edu) => (
          <EducationItem
            key={edu.id}
            degree={edu.degree}
            school={edu.school}
            gpa={edu.gpa}
            dateStarted={edu.dateStarted}
            dateEnded={edu.dateEnded}
          />
        ))
      )}
    </section>
  );
};

export default EducationSection;
