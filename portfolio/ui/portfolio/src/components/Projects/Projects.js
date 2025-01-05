import React, { useState, useEffect } from 'react';
import './Projects.css';

const Projects = () => {
  const [projectsData, setProjectsData] = useState([]);
  const [currentProjectIndex, setCurrentProjectIndex] = useState(0);

  const username = process.env.REACT_APP_API_USERNAME;
  const password = process.env.REACT_APP_API_PASSWORD;
  const encodedAuth = btoa(`${username}:${password}`); 

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/projects', {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${encodedAuth}`, 
          },
        });
        const data = await response.json();
        const featuredProjects = data.filter(project => project.isFeatured);
        setProjectsData(featuredProjects);
      } catch (error) {
        console.error('Error fetching projects:', error);
      }
    };

    fetchProjects();
  }, [encodedAuth]);

  const handleNextProject = () => {
    setCurrentProjectIndex((prevIndex) => (prevIndex + 1) % projectsData.length);
  };

  const handlePrevProject = () => {
    setCurrentProjectIndex((prevIndex) => (prevIndex - 1 + projectsData.length) % projectsData.length);
  };

  if (projectsData.length === 0) {
    return (
      <section className="projects-section">
        <h2>Projects</h2>
        <p>Loading projects...</p>
      </section>
    );
  }

  const currentProject = projectsData[currentProjectIndex];
  const imagePath = require(`../../assets/images/${currentProject.photoFile}`);

  return (
    <section className="projects-section" id="projects"> 
      <h2>Projects</h2>
      <div className="carousel-container">
        <button className="arrow-button left" onClick={handlePrevProject}>←</button>
        <div className="project-card">
          <div className="project-info">
            <h3>{currentProject.title}</h3>
            <p>{currentProject.description}</p>
            <div className="project-links">
              {currentProject.demoLink && (
                <a 
                  href={currentProject.demoLink} 
                  className="project-link" 
                  target="_blank" 
                  rel="noopener noreferrer"
                >
                  Demo
                </a>
              )}
              {currentProject.repoLink && (
                <a 
                  href={currentProject.repoLink} 
                  className="project-link" 
                  target="_blank" 
                  rel="noopener noreferrer"
                >
                  Repo
                </a>
              )}
            </div>
          </div>
          <div className="project-image">
            <img src={imagePath} alt={currentProject.title} />
          </div>
        </div>
        <button className="arrow-button right" onClick={handleNextProject}>→</button>
      </div>
    </section>
  );
};

export default Projects;
