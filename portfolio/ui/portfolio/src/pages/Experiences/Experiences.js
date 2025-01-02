import React, { useEffect, useState, useContext } from 'react';
import axios from 'axios';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress } from '@mui/material';
import './Experiences.css';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import { ThemeContext } from '../../utils/ThemeContext';

const Experiences = () => {
  const { darkMode } = useContext(ThemeContext);
  const [education, setEducation] = useState([]);
  const [skills, setSkills] = useState([]);
  const [experiences, setExperiences] = useState([]);
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [educationRes, skillsRes, experiencesRes, projectsRes] = await Promise.all([
          axios.get('http://localhost:8080/api/education'),
          axios.get('http://localhost:8080/api/skills'),
          axios.get('http://localhost:8080/api/experiences'),
          axios.get('http://localhost:8080/api/projects'),
        ]);

        setEducation(educationRes.data);
        setSkills(skillsRes.data);
        setExperiences(experiencesRes.data);
        setProjects(projectsRes.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const renderTable = (id, title, data, columns) => (
    <div id={id} className={`table-section ${darkMode ? 'dark-mode' : ''}`}>
      <h2>{title}</h2>
      {loading ? (
        <CircularProgress />
      ) : (
        <TableContainer component={Paper} style={{ marginBottom: '20px' }}>
          <Table className={`mui-table ${darkMode ? 'dark-mode' : ''}`}>
            <TableHead>
              <TableRow>
                {columns.map((column, index) => (
                  <TableCell key={index}>{column}</TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {data.map((row, index) => (
                <TableRow key={index}>
                  {columns.map((column, colIndex) => (
                    <TableCell key={colIndex}>{row[column.toLowerCase()]}</TableCell>
                  ))}
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      )}
    </div>
  );
  
  

  return (
    <div className={`experiences-page ${darkMode ? 'dark-mode' : 'light-mode'}`}>
      <Header />
      <section className="tables-container">
        {renderTable('education', 'Education', education, ['Institution', 'Degree', 'Year'])}
        {renderTable('skills', 'Skills', skills, ['Skill', 'Level'])}
        {renderTable('experience', 'Experience', experiences, ['Company', 'Role', 'Duration'])}
        {renderTable('projects', 'Projects', projects, ['Name', 'Description', 'Year'])}
      </section>

      <Footer />
    </div>
  );
};

export default Experiences;
