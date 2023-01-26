import React from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';
import { Provider } from 'react-redux';
import Login from './pages/Login';
import Register from './pages/Register';
import FactoryManagement from './pages/FactoryManagement';
import Products from './pages/Products';
import RawMaterial from './pages/RawMaterial';
import store from './store';

function Router() {
  return (
    <Provider store={store}>
      <Routes>
        <Route exact path="/" element={<Navigate to="/factory-management" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/factory-management" element={<FactoryManagement />} />
        <Route path="/products" element={<Products />} />
        <Route path="/raw-material" element={<RawMaterial />} />
      </Routes>
    </Provider>
  );
}
export default Router;
