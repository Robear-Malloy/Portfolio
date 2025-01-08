import React, { useState, useEffect, useContext } from 'react';
import { useTranslation } from 'react-i18next';
import axios from 'axios';
import TechStackModal from './TechStackModal';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress } from '@mui/material';
import './Experiences.css';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import { ThemeContext } from '../../utils/ThemeContext';

const Experiences = () => {
  const { t } = useTranslation();
  const { darkMode } = useContext(ThemeContext);
  const [education, setEducation] = useState([]);
  const [skills, setSkills] = useState([]);
  const [experiences, setExperiences] = useState([]);
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [techStack, setTechStack] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const username = process.env.REACT_APP_API_USERNAME;
        const password = process.env.REACT_APP_API_PASSWORD;
        const encodedAuth = btoa(`${username}:${password}`); 

        const [educationRes, skillsRes, experiencesRes, projectsRes] = await Promise.all([
          axios.get('http://localhost:8080/api/education', {
            headers: { 'Authorization': `Basic ${encodedAuth}` }
          }),
          axios.get('http://localhost:8080/api/skills', {
            headers: { 'Authorization': `Basic ${encodedAuth}` }
          }),
          axios.get('http://localhost:8080/api/experiences', {
            headers: { 'Authorization': `Basic ${encodedAuth}` }
          }),
          axios.get('http://localhost:8080/api/projects', {
            headers: { 'Authorization': `Basic ${encodedAuth}` }
          }),
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

  const handleRowClick = async (type, id) => {
    try {
      const username = process.env.REACT_APP_API_USERNAME;
      const password = process.env.REACT_APP_API_PASSWORD;
      const encodedAuth = btoa(`${username}:${password}`);

      const response = await axios.get(`http://localhost:8080/api/tech/${type}/${id}`, {
        headers: { 'Authorization': `Basic ${encodedAuth}` }
      });
      setTechStack(response.data);
      setModalOpen(true);
    } catch (error) {
      console.error(`Error fetching tech stack for ${type} ID ${id}:`, error);
    }
  };

  const renderTable = (id, title, data, columns, type) => (
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
                  <TableCell key={index}>{column.header}</TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {data.map((row, index) => (
                <TableRow
                  key={index}
                  onClick={() => handleRowClick(type, row.id)}
                  style={{ cursor: 'pointer' }}
                >
                  {columns.map((column, colIndex) => (
                    <TableCell key={colIndex}>{row[column.key]}</TableCell>
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
        {renderTable(
          'education',
          t('experienceTables.education.title'),
          education.map((row) => ({
            ...row,
            duration: `${row.dateStarted} - ${row.dateEnded || t('experienceTables.education.present')}`,
          })),
          [
            { key: 'school', header: t('experienceTables.education.school') },
            { key: 'degree', header: t('experienceTables.education.degree') },
            { key: 'duration', header: t('experienceTables.education.duration') },
          ],
          'education'
        )}
        {renderTable('skills', t('experienceTables.skill.title'), skills, [
          { key: 'name', header: t('experienceTables.skill.name') },
          { key: 'type', header: t('experienceTables.skill.type') },
          //{ key: 'level', header: 'Level' },
        ])}
        {renderTable(
          'experience',
          t('experienceTables.experience.title'),
          experiences.map((row) => ({
            ...row,
            duration: `${row.dateStarted} - ${row.dateEnded || t('experienceTables.experience.present')}`,
          })),
          [
            { key: 'company', header: t('experienceTables.experience.company') },
            { key: 'position', header: t('experienceTables.experience.role') },
            { key: 'duration', header: t('experienceTables.experience.duration') },
          ],
          'experience'
        )}
        {renderTable('projects', t('experienceTables.project.title'), projects, [
          { key: 'title', header: t('experienceTables.project.name') },
          { key: 'description', header: t('experienceTables.project.description') },
        ], 'project')}
      </section>
      <Footer />
      {modalOpen && (
        <TechStackModal
          isOpen={modalOpen}
          onClose={() => setModalOpen(false)}
          techStack={techStack}
          skills={skills}
      />
      )}
    </div>
  );
};

export default Experiences;
