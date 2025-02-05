import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import axios from 'axios';
import './Contact.css';

const Contact = () => {
  const { t } = useTranslation();
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    subject: '',
    message: '',
  });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [responseMessage, setResponseMessage] = useState('');

  const username = process.env.REACT_APP_API_USERNAME;
  const password = process.env.REACT_APP_API_PASSWORD;
  const encodedAuth = btoa(`${username}:${password}`);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setResponseMessage('');

    const emailData = {
      subject: formData.subject,       
      body: formData.message,          
      toEmail: formData.email,         
      fromEmail: formData.email,       
      name: formData.name,             
    };

    try {
      const response = await axios.post('http://localhost:8080/api/email/contact', emailData, {
        headers: {
          'Authorization': `Basic ${encodedAuth}`,
        },
      });

      if (response.status === 200) {
        setResponseMessage(t('contact.successMessage'));
      }
    } catch (error) {
      setResponseMessage(t('contact.failMessage'));
      console.error('Error sending message:', error);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <section className="contact-section" id="contact">
      <h2>{t('contact.title')}</h2>
      <form className="contact-form" onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder={t('contact.name')}
          value={formData.name}
          onChange={handleInputChange}
          required 
        />
        <input
          type="email"
          name="email"
          placeholder={t('contact.email')}
          value={formData.email}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="subject"
          placeholder={t('contact.subject')}
          value={formData.subject}
          onChange={handleInputChange}
        />
        <textarea
          name="message"
          placeholder={t('contact.message')}
          value={formData.message}
          onChange={handleInputChange}
          required 
        ></textarea>
        <button type="submit" disabled={isSubmitting || !formData.name || !formData.message}>
          {isSubmitting ? t('contact.sending') : t('contact.sendButton')}
        </button>
      </form>
      {responseMessage && <p className="response-message">{responseMessage}</p>}
    </section>
  );
};

export default Contact;
