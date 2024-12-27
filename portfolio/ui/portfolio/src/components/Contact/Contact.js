import React, { useState } from 'react';
import axios from 'axios';
import './Contact.css';

const Contact = () => {
  const [formData, setFormData] = useState({
    email: '',
    phone: '',
    message: '',
  });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [responseMessage, setResponseMessage] = useState('');

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setResponseMessage('');

    try {
      const response = await axios.post('http://localhost:8080/api/email/test'); // Replace with actual endpoint and payload
      if (response.status === 200) {
        setResponseMessage('Message sent successfully!');
      }
    } catch (error) {
      setResponseMessage('Failed to send the message. Please try again.');
      console.error('Error sending message:', error);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <section className="contact-section" id="contact-section">
      <h2>Contact</h2>
      <form className="contact-form" onSubmit={handleSubmit}>
        <input
          type="email"
          name="email"
          placeholder="Your Email"
          value={formData.email}
          onChange={handleInputChange}
          required
        />
        <input
          type="text"
          name="phone"
          placeholder="Your Phone"
          value={formData.phone}
          onChange={handleInputChange}
          required
        />
        <textarea
          name="message"
          placeholder="Your Message"
          value={formData.message}
          onChange={handleInputChange}
          required
        ></textarea>
        <button type="submit" disabled={isSubmitting}>
          {isSubmitting ? 'Sending...' : 'Send'}
        </button>
      </form>
      {responseMessage && <p className="response-message">{responseMessage}</p>}
    </section>
  );
};

export default Contact;
