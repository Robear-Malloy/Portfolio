import { useContext } from 'react';
import { useTranslation } from 'react-i18next';
import './Tutorial.css';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import { ThemeContext } from '../../utils/ThemeContext';


const Tutorial = () => {
  const { t } = useTranslation();
  const { darkMode } = useContext(ThemeContext);
  const copyToClipboard = (text) => {
    navigator.clipboard.writeText(text);
    alert(t('tutorial.alert'));
  };

  const getCurlCommand = `curl -X GET http://localhost:8080/api/resume`;
  const postCurlCommand = `curl -X POST http://localhost:8080/api/visitor/demo \\
  -H "Content-Type: application/json" \\
  -d '{"name": "Your Name", "message": "Your Message"}'`;
  const currentDateTime = new Date().toISOString();
  const visitorPostCommand = `curl -X POST http://localhost:8080/api/visitor/auth \\
  -H "Content-Type: application/json" \\
  -d '{"name": "Your name", message: "Your Message"}'`;

  return (
    <div className={`video-page ${darkMode ? 'dark-mode' : 'light-mode'}`}>
    <div className="tutorial-page">
      <Header />
      <section className="tutorial-api" id="tutorial">
        <h2>{t('tutorial.title')}</h2>
        <p>{t('tutorial.intro')}</p>

        <div className="api-example">
          <h3>{t('tutorial.getHeader')}</h3>
          <p>{t('tutorial.getContent')}</p>
          <div className="code-box">
            <code>{getCurlCommand}</code>
            <button onClick={() => copyToClipboard(getCurlCommand)}>{t('tutorial.copyButton')}</button>
          </div>
        </div>

        <div className="api-example">
          <h3>{t('tutorial.postHeader')}</h3>
          <p>{t('tutorial.postContent')}</p>
          <div className="code-box">
            <code>{postCurlCommand}</code>
            <button onClick={() => copyToClipboard(postCurlCommand)}>{t('tutorial.copyButton')}</button>
          </div>
        </div>

        <div className="api-example">
          <h3>{t('tutorial.postAuthHeader')}</h3>
          <p>
          {t('tutorial.postAuthContent')}
          </p>
          <div className="code-box">
            <code>{visitorPostCommand}</code>
            <button onClick={() => copyToClipboard(visitorPostCommand)}>{t('tutorial.copyButton')}</button>
          </div>
          <p dangerouslySetInnerHTML={{ __html: t('tutorial.postAuthNote') }}></p>
        </div>
      </section>

      <section className="thank-you-note" id="thank-you">
        <h2>{t('tutorial.thankYouHeader')}</h2>
        <p>
        {t('tutorial.thankYouContent')}
        </p>
      </section>

      <Footer className="footer"/>
    </div>
    </div>
  );
};

export default Tutorial;
