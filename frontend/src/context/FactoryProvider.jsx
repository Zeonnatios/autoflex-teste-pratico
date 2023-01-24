/* eslint-disable react/jsx-no-constructed-context-values */
import React from 'react';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import FactoryContext from './FactoryContext';

function FactoryProvider({ children }) {
  const navigate = useNavigate();

  const signOut = () => {
    navigate('/login');
  };

  const states = {
    signOut,
  };

  return (
    <FactoryContext.Provider value={states}>
      { children }
    </FactoryContext.Provider>
  );
}

FactoryProvider.propTypes = {
  children: PropTypes.oneOfType([
    PropTypes.arrayOf(PropTypes.node),
    PropTypes.node,
  ]).isRequired,
};

export default FactoryProvider;
