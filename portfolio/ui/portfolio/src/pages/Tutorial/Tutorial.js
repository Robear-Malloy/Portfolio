import { useContext } from 'react';
import './Tutorial.css';
import Header from '../../components/Header/Header';
import Footer from '../../components/Footer/Footer';
import { ThemeContext } from '../../utils/ThemeContext';


const Tutorial = () => {
  const { darkMode } = useContext(ThemeContext);
  const copyToClipboard = (text) => {
    navigator.clipboard.writeText(text);
    alert('Copied to clipboard!');
  };

  const getCurlCommand = `curl -X GET http://localhost:8080/api/resume`;
  const postCurlCommand = `curl -X POST http://localhost:8080 \\
  -H "Content-Type: application/json" \\
  -d '{"Name": "Your Name", "Message": "Your Message"}'`;
  const currentDateTime = new Date().toISOString();
  const visitorPostCommand = `curl -X POST http://localhost:8080/api/visitor \\
  -H "Content-Type: application/json" \\
  -d '{"datetime": "${currentDateTime}", "name": ""}'`;

  return (
    <div className={`video-page ${darkMode ? 'dark-mode' : 'light-mode'}`}>
    <div className="tutorial-page">
      <Header />
      <section className="tutorial-api" id="tutorial">
        <h2>API Testing Tutorial</h2>
        <p>You can test the API endpoints using cURL, Postman, or by hitting the endpoint directly in your browser.</p>

        <div className="api-example">
          <h3>GET Endpoint</h3>
          <p>Use this cURL command to fetch your resume:</p>
          <div className="code-box">
            <code>{getCurlCommand}</code>
            <button onClick={() => copyToClipboard(getCurlCommand)}>Copy</button>
          </div>
        </div>

        <div className="api-example">
          <h3>POST Endpoint</h3>
          <p>Use this cURL command to send a POST request with parameters:</p>
          <div className="code-box">
            <code>{postCurlCommand}</code>
            <button onClick={() => copyToClipboard(postCurlCommand)}>Copy</button>
          </div>
        </div>

        <div className="api-example">
          <h3>POST Endpoint with Authorization</h3>
          <p>
            The following POST request attempts to add a visitor. If you do not feel comfortable sharing your name,
            leave it empty, and it will assign "Anonymous" to you. However, if authorization is required, the request
            will be rejected:
          </p>
          <div className="code-box">
            <code>{visitorPostCommand}</code>
            <button onClick={() => copyToClipboard(visitorPostCommand)}>Copy</button>
          </div>
          <p>
            <strong>Note:</strong> If the endpoint requires authorization, you must include an authorization header
            (e.g., <code>-H "Authorization: Bearer &lt;token&gt;"</code>). Without proper credentials, the server will
            deny access.
          </p>
        </div>
      </section>

      <section className="thank-you-note" id="thank-you">
        <h2>Thank You!</h2>
        <p>
          A heartfelt thank you to all the visitors who have taken the time to explore my site. Your support and
          interest mean the world to me, and I hope you find the content here helpful and inspiring. Feel free to
          reach out with any feedback or questions!
        </p>
      </section>

      <Footer className="footer"/>
    </div>
    </div>
  );
};

export default Tutorial;
