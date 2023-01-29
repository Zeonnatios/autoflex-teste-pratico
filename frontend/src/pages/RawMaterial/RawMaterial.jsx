import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faPencil, faTrash } from '@fortawesome/free-solid-svg-icons';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';

function RawMaterial() {
  const navigate = useNavigate();

  return (
    <div>
      <Header />

      <h3 className="d-flex justify-content-center py-3">Raw Material Management</h3>

      <div className="container">

        <div className="pb-3">
          <button type="button" className="btn btn-outline-primary" onClick={() => navigate('/create-raw-material')}>
            <FontAwesomeIcon icon={faPlus} />
            {' '}
            Add Raw Material
          </button>
        </div>

        <table className="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Stock</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>
                <button type="button" className="btn btn-outline-warning" onClick={() => navigate('/edit-raw-material/45')}>
                  <FontAwesomeIcon icon={faPencil} />
                  {' '}
                  Edit
                </button>
                {' '}
                /
                {' '}
                <button type="button" className="btn btn-outline-danger">
                  <FontAwesomeIcon icon={faTrash} />
                  {' '}
                  Delete
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default RawMaterial;
