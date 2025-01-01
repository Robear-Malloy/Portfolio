import React from 'react';

const EducationItem = ({ degree, school, gpa, dateStarted, dateEnded }) => {
  return (
    <div className="education-item">
      <h3 className="education-degree">{degree}</h3>
      <h4 className="education-school">{school}</h4>
      {gpa && <p className="education-gpa">GPA: {gpa.toFixed(2)}</p>}
      <p className="education-dates">
        <em>
          {dateStarted} - {dateEnded || 'Present'}
        </em>
      </p>
    </div>
  );
};

export default EducationItem;
