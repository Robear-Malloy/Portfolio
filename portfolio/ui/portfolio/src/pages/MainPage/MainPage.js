import React, { useState, useContext, useEffect } from 'react';
import './MainPage.css';
import { ThemeContext } from '../../utils/ThemeContext';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import EducationSection from '../../components/Education/EducationSection';
import Experience from '../../components/Experience/Experience';
import Hero from '../../components/Hero/Hero';
import Skills from '../../components/Skills/Skills';
import Projects from '../../components/Projects/Projects';
import Contact from '../../components/Contact/Contact';
import AboutMe from '../../components/AboutMe/AboutMe';

const MainPage = () => {
  const { darkMode } = useContext(ThemeContext);
 
  return (
    <div className={`main-page ${darkMode ? 'dark-mode' : 'light-mode'}`}>
      <Header />
      <Hero />
      <Skills />
      <section className="experience-education-section" id="experience">
        <Experience />
        <EducationSection/>
      </section> 
      <Projects />
      <AboutMe />
      <Contact />
      <Footer />
    </div>
  );
};

export default MainPage;
