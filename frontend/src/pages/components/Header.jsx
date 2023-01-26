import React from 'react';
import { Link } from 'react-router-dom';

function Header() {
  return (
    <header>
      <nav className="nav navbar-dark bg-dark justify-content-center p-3">
        <div className="container d-flex flex-wrap">
          <ul className="nav">
            <li className="nav-item">
              <Link className="nav-link link-warning" to="/factory-management">Factory Management</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link link-warning" to="/products">Products</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link link-warning" to="/raw-material">Raw Material</Link>
            </li>
          </ul>
        </div>
      </nav>
    </header>
  );
}

export default Header;
