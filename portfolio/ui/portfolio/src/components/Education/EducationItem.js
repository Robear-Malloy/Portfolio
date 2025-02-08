import { formatDate } from '../../utils/DateUtil';

const EducationItem = ({ degree, school, gpa, dateStarted, dateEnded }) => {
  return (
    <div className="education-item">
      <h3 className="education-degree">{degree}</h3>
      <h4 className="education-school">{school}</h4>
      {gpa && <p className="education-gpa">GPA: {gpa.toFixed(2)}</p>}
      <p className="education-dates">
        <em>
          {formatDate(dateStarted)} - {dateEnded ? formatDate(dateEnded) : 'Present'}
        </em>
      </p>
    </div>
  );
};

export default EducationItem;
