import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCog } from '@fortawesome/free-solid-svg-icons';
import { useTranslation } from 'react-i18next';
import SettingsModal from '../SettingsModal/SettingsModal';
import './Navbar.css';

const Navbar = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const location = useLocation(); 

  const { t } = useTranslation();
  const toggleMenu = () => setMenuOpen(!menuOpen);
  const toggleModal = () => setModalOpen(!modalOpen);

  const renderNavCenterLinks = () => {
    if (location.pathname === '/video') {
      return (
        <ul className="nav-center">
          <li><a href="#video">{t('navbar.video.video')}</a></li>
        </ul>
      );
    } else if (location.pathname === '/') {
      return (
        <ul className="nav-center">
          <li><a href="#skills">{t('navbar.home.skills')}</a></li>
          <li><a href="#experience">{t('navbar.home.experience')}</a></li>
          <li><a href="#projects">{t('navbar.home.projects')}</a></li>
          <li><a href="#about-me">{t('navbar.home.aboutMe')}</a></li>
          <li><a href="#contact">{t('navbar.home.contact')}</a></li>
        </ul>
      );
    } else if (location.pathname === '/experiences') {
      return (
        <ul className="nav-center">
          <li><a href="#education">{t('navbar.experiences.education')}</a></li>
          <li><a href="#skills">{t('navbar.experiences.skills')}</a></li>
          <li><a href="#experience">{t('navbar.experiences.experience')}</a></li>
          <li><a href="#projects">{t('navbar.experiences.projects')}</a></li>
        </ul>
      );
    } else if (location.pathname === '/tutorial') {
      return (
        <ul className="nav-center">
          <li><a href="#tutorial">{t('navbar.tutorial.tutorial')}</a></li>
          <li><a href="#thank-you">{t('navbar.tutorial.thank')}</a></li>
        </ul>
      );
    }
    return (
      <ul className="nav-center">
        <li><a href="#home">{t('navbar.menu.home')}</a></li>
      </ul>
    );
  };

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <div className={`hamburger-menu ${menuOpen ? 'open' : ''}`} onClick={toggleMenu}>
          <FontAwesomeIcon icon={faBars} size="xl" />
        </div>
      </div>

      {menuOpen && (
        <div className="dropdown-menu">
          <Link to="/">{t('navbar.menu.home')}</Link>
          <Link to="/experiences">{t('navbar.menu.experiences')}</Link>
          <Link to="/video">{t('navbar.menu.video')}</Link>
          <Link to="/tutorial">{t('navbar.menu.tutorial')}</Link>
        </div>
      )}

      {renderNavCenterLinks()}

      <div className="settings-icon" onClick={toggleModal}>
        <FontAwesomeIcon icon={faCog} />
      </div>

      <SettingsModal isOpen={modalOpen} onClose={toggleModal} />
    </nav>
  );
};

export default Navbar;
