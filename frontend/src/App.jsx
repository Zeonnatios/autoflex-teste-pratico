/* eslint-disable import/no-unresolved */
import React from 'react';
import FactoryProvider from './context/FactoryProvider';
import Routes from './Routes';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div>
      <FactoryProvider>
        <Routes />
      </FactoryProvider>
    </div>

  );
}

export default App;
