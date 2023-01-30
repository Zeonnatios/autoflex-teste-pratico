import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faPencil, faTrash } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { Modal } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Header from '../components/Header';
import axios from '../../services/axios';

function RawMaterial() {
  const navigate = useNavigate();
  const [materials, setMaterials] = useState([]);
  const [show, setShow] = useState(false);
  const [selectedId, setSelectedId] = useState('');

  const getMaterials = async () => {
    const { data } = await axios.get('/material');
    setMaterials(data);
  };

  const deleteMaterial = async () => {
    await axios.delete(`/material/${selectedId}`);
  };

  const handleCloseModal = () => setShow(false);
  const handleShowModal = (id) => {
    setShow(true);
    setSelectedId(id);
  };
  const handleConfirmModal = async () => {
    await deleteMaterial();
    handleCloseModal();
    getMaterials();
  };

  useEffect(() => {
    getMaterials();
  }, []);

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
            {materials && materials.map((material, index) => (
              <tr key={material.id}>
                <th scope="row">{index + 1}</th>
                <td>{material.name}</td>
                <td>{material.stock}</td>
                <td>
                  <button type="button" className="btn btn-outline-warning" onClick={() => navigate(`/edit-raw-material/${material.id}`)}>
                    <FontAwesomeIcon icon={faPencil} />
                    {' '}
                    Edit
                  </button>
                  {' '}
                  /
                  {' '}
                  <button type="button" className="btn btn-outline-danger" onClick={() => handleShowModal(material.id)}>
                    <FontAwesomeIcon icon={faTrash} />
                    {' '}
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Modal show={show} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Are you sure ?</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Do you really want to delete this raw material ?
          This process cannot be undone.
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Cancel
          </Button>
          <Button variant="danger" onClick={handleConfirmModal}>
            Confirm
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default RawMaterial;
