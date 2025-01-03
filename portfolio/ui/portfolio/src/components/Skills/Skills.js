import React, { useState, useEffect, useRef } from 'react';
import { FaNode, FaReact, FaDatabase, FaQuestion } from 'react-icons/fa'; 
import './Skills.css';

const Skills = () => {
  const [skills, setSkills] = useState([]);
  const [randomSkill, setRandomSkill] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const randomSkillBoxRef = useRef(null); 

  useEffect(() => {
    const fetchSkills = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/skills');
        const data = await response.json();
        setSkills(data);
      } catch (error) {
        console.error('Error fetching skills:', error);
      }
    };

    fetchSkills();
  }, []);

  const backendSkills = skills.filter(skill => skill.type === 'BACKEND');
  const frontendSkills = skills.filter(skill => skill.type === 'FRONTEND');
  const otherSkills = skills.filter(skill => skill.type === 'OTHER');

  const handleRandomSkillClick = () => {
    if (isLoading) return;

    setIsLoading(true);

    if (randomSkillBoxRef.current) {
      randomSkillBoxRef.current.classList.add('flashing');
    }

    setTimeout(() => {
      const randomIndex = Math.floor(Math.random() * otherSkills.length);
      setRandomSkill(otherSkills[randomIndex]);

      if (randomSkillBoxRef.current) {
        randomSkillBoxRef.current.classList.remove('flashing');
      }
      setIsLoading(false);
    }, 1000); 
  };

  return (
    <section className="skills-section" id="skills">
      <h2>Skills</h2>
      <div className="skills-container">
        <div className="skill-box">
          <h3>Backend</h3>
          <div className="icons-container">
            {backendSkills.map(skill => (
              <div key={skill.id} className="icon-box" data-tooltip={skill.name}>
                <FaNode size={40} />
              </div>
            ))}
          </div>
        </div>

        <div className="skill-box">
          <h3>Frontend</h3>
          <div className="icons-container">
            {frontendSkills.map(skill => (
              <div key={skill.id} className="icon-box" data-tooltip={skill.name}>
                <FaReact size={40} />
              </div>
            ))}
          </div>
        </div>
      </div>

      <div className="random-skill-box" onClick={handleRandomSkillClick}>
  <div className="random-skill-container" ref={randomSkillBoxRef}>
    <h3>Mystery Box</h3>
      {isLoading ? (
        <div className="loading-spinner">
          <FaQuestion size={40} />
        </div>
        ) : randomSkill ? (
        <div className="icon-box" data-tooltip={randomSkill.name}>
          <FaDatabase size={40} />
        </div>
          ) : (
          <div className="icon-box">
            <FaQuestion size={40} />
          </div>
          )}
        </div>
      </div>
      <button className="see-more-button">See More</button>
    </section>
  );
};

export default Skills;
