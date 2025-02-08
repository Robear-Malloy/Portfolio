import { useState, useEffect, useContext } from 'react';
import { useTranslation } from 'react-i18next';
import TechStackModal from './TechStackModal';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress } from '@mui/material';
import './Experiences.css';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import { ThemeContext } from '../../utils/ThemeContext';
import { formatDate } from '../../utils/DateUtil';

const Experiences = () => {
  const { t, i18n } = useTranslation();
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
        const language = i18n.language || 'en'; 

        const [educationRes, skillsRes, experiencesRes, projectsRes] = await Promise.all([
          fetch(`http://localhost:8080/api/education?lang=${language}`, {
            method: 'GET',
            headers: {
              'Authorization': `Basic ${encodedAuth}`,
            },
          }),
          fetch(`http://localhost:8080/api/skills?lang=${language}`, {
            method: 'GET',
            headers: {
              'Authorization': `Basic ${encodedAuth}`,
            },
          }),
          fetch(`http://localhost:8080/api/experiences?lang=${language}`, {
            method: 'GET',
            headers: {
              'Authorization': `Basic ${encodedAuth}`,
            },
          }),
          fetch(`http://localhost:8080/api/projects?lang=${language}`, {
            method: 'GET',
            headers: {
              'Authorization': `Basic ${encodedAuth}`,
            },
          }),
        ]);

        const educationData = await educationRes.json();
        const skillsData = await skillsRes.json();
        const experiencesData = await experiencesRes.json();
        const projectsData = await projectsRes.json();

        setEducation(educationData);
        setSkills(skillsData);
        setExperiences(experiencesData);
        setProjects(projectsData);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [i18n.language]); 

  const handleRowClick = async (type, id) => {
    if (type === 'education' || type === 'skills') {
      return;
    }
  
    try {
      const username = process.env.REACT_APP_API_USERNAME;
      const password = process.env.REACT_APP_API_PASSWORD;
      const encodedAuth = btoa(`${username}:${password}`);
      const language = i18n.language || 'en'; 
  
      const response = await fetch(`http://localhost:8080/api/tech/${type}/${id}?lang=${language}`, {
        method: 'GET',
        headers: {
          'Authorization': `Basic ${encodedAuth}`,
        },
      });
  
      const data = await response.json();
      setTechStack(data);
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
            duration: `${formatDate(row.dateStarted)} - ${row.dateEnded ? formatDate(row.dateEnded) : t('experienceTables.education.present')}`,
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
        ],
        'skills')}
        {renderTable(
          'experience',
          t('experienceTables.experience.title'),
          experiences.map((row) => ({
            ...row,
            duration: `${formatDate(row.dateStarted)} - ${row.dateEnded ? formatDate(row.dateEnded) : t('experienceTables.experience.present')}`,
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
