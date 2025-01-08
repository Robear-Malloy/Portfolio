import React from 'react';
import AppRoutes from './routes';
import { I18nextProvider } from 'react-i18next';
import i18n from './utils/i18n';

function App() {
  return (
    <I18nextProvider i18n={i18n}>
      <div className="App">
        <AppRoutes />
      </div>
    </I18nextProvider>
  );
}

export default App;
