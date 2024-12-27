import React from 'react';

const EducationItem = ({ degree, school, gpa, dateStarted, dateEnded }) => {
  return (
    <div className="education-item">
      <h3>{degree} - {school}</h3>
      {gpa && <p>GPA: {gpa.toFixed(2)}</p>}
      <p>
        <em>({dateStarted} - {dateEnded || 'Present'})</em>
      </p>
    </div>
  );
};

export default EducationItem;
