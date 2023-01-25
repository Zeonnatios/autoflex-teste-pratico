import React from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import FactoryManagement from './pages/FactoryManagement';

function Router() {
  return (
    <Routes>
      <Route exact path="/" element={<Navigate to="/factory-management" />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/factory-management" element={<FactoryManagement />} />
    </Routes>
  );
}
export default Router;
