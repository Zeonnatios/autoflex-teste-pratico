import React from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import FactoryManagement from './pages/FactoryManagement';
import Products from './pages/Products/Products';
import CreateProduct from './pages/Products/CreateProduct';
import EditProudct from './pages/Products/EditProduct';
import RawMaterial from './pages/RawMaterial/RawMaterial';
import CreateRawMaterial from './pages/RawMaterial/CreateRawMaterial';
import EditRawMaterial from './pages/RawMaterial/EditRawMaterial';

function Router() {
  return (
    <Routes>
      <Route exact path="/" element={<Navigate to="/factory-management" />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/factory-management" element={<FactoryManagement />} />
      <Route path="/products" element={<Products />} />
      <Route path="/create-product" element={<CreateProduct />} />
      <Route path="/edit-product/:id" element={<EditProudct />} />
      <Route path="/raw-material" element={<RawMaterial />} />
      <Route path="/create-raw-material" element={<CreateRawMaterial />} />
      <Route path="/edit-raw-material/:id" element={<EditRawMaterial />} />
    </Routes>

  );
}
export default Router;
