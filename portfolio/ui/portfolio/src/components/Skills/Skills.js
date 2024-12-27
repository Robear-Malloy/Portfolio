import React from 'react';
import { FaNode, FaPython, FaDocker, FaReact, FaDatabase } from 'react-icons/fa';
import './Skills.css';

const Skills = () => {
  return (
    <section className="skills-section">
      <h2>Skills</h2>
      <div className="skills-container">
        <div className="skill-box">
          <h3>Backend</h3>
          <div className="icons-container">
            <div className="icon-box" data-tooltip="Node.js">
              <FaNode size={40} />
            </div>
            <div className="icon-box" data-tooltip="Python">
              <FaPython size={40} />
            </div>
            <div className="icon-box" data-tooltip="Docker">
              <FaDocker size={40} />
            </div>
          </div>
        </div>

        <div className="skill-box">
          <h3>User Experience</h3>
          <div className="icons-container">
            <div className="icon-box" data-tooltip="React">
              <FaReact size={40} />
            </div>
            <div className="icon-box" data-tooltip="Vue.js">
              <FaReact size={40} />
            </div>
          </div>
        </div>

        <div className="skill-box">
          <h3>Certifications</h3>
          <div className="icons-container">
            <div className="icon-box" data-tooltip="AWS Certified">
              <FaDatabase size={40} />
            </div>
            <div className="icon-box" data-tooltip="Google Cloud Certified">
              <FaDatabase size={40} />
            </div>
          </div>
        </div>
      </div>
      <button className="see-more-button">See More</button>
    </section>
  );
};

export default Skills;
