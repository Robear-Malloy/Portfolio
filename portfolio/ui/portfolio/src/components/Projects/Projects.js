import React, {useState} from 'react';
import './Projects.css';

const projectsData = [
  {
    title: 'Project 1',
    description: 'Description of Project 1.',
    demoLink: '#',
    repoLink: '#',
    imageUrl: 'path/to/project1-image.jpg', 
  },
  {
    title: 'Project 2',
    description: 'Description of Project 2.',
    demoLink: '#',
    repoLink: '#',
    imageUrl: 'path/to/project2-image.jpg',
  },
  {
    title: 'Project 3',
    description: 'Description of Project 3.',
    demoLink: '#',
    repoLink: '#',
    imageUrl: 'path/to/project3-image.jpg',
  },
];

const Projects = () => {

    const [currentProjectIndex, setCurrentProjectIndex] = useState(0);

    const handleNextProject = () => {
      setCurrentProjectIndex((prevIndex) => (prevIndex + 1) % projectsData.length);
    };
   
    const handlePrevProject = () => {
      setCurrentProjectIndex((prevIndex) => (prevIndex - 1 + projectsData.length) % projectsData.length);
    };
   
    const currentProject = projectsData[currentProjectIndex];
   
  return (
    <section className="projects-section">
      <h2>Projects</h2>
      <div className="carousel-container">
        <button className="arrow-button left" onClick={handlePrevProject}>←</button>
        <div className="project-card">
          <div className="project-info">
            <h3>{currentProject.title}</h3>
            <p>{currentProject.description}</p>
            <div className="project-links">
              <a 
                href={currentProject.demoLink} 
                className="project-link" 
                target="_blank" 
                rel="noopener noreferrer"
              >
                Demo
              </a>
              <a 
                href={currentProject.repoLink} 
                className="project-link" 
                target="_blank" 
                rel="noopener noreferrer"
              >
                Repo
              </a>
            </div>
          </div>
          <div className="project-image">
            <img src={currentProject.imageUrl} alt={currentProject.title} />
          </div>
        </div>
        <button className="arrow-button right" onClick={handleNextProject}>→</button>
      </div>
    </section>
  );
};

export default Projects;
