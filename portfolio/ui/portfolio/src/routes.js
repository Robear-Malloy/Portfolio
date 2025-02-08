import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import MainPage from './pages/MainPage/MainPage';
import Experiences from './pages/Experiences/Experiences';
import Video from './pages/Video/Video';
import Tutorial from './pages/Tutorial/Tutorial';

const AppRoutes = () => (
  <Router>
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/experiences" element={<Experiences />} />
      <Route path="/video" element={<Video />} />
      <Route path="/tutorial" element={<Tutorial />} />
    </Routes>
  </Router>
);

export default AppRoutes;
