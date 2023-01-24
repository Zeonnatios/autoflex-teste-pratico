import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import FactoryContext from '../../context/FactoryContext';
import { getStorage } from '../../helpers/Storage';

function Header() {
  const { signOut } = useContext(FactoryContext);
  const user = getStorage('user');

  return (
    <header>
      <nav className="nav navbar-dark bg-dark justify-content-center p-3">
        <div className="container d-flex flex-wrap">
          <ul className="nav">
            <li className="nav-item ">
              <h2 className="text-light">{user.name}</h2>
            </li>
            <li className="nav-item">
              <Link className="nav-link link-warning" to="/factory-management">Profile</Link>
            </li>
            <li className="nav-item">
              <button type="button" className="btn btn-outline-light" onClick={signOut}>Sair</button>
            </li>
          </ul>
        </div>
      </nav>
    </header>
  );
}

export default Header;
