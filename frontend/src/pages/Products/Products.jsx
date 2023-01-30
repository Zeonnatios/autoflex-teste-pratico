import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faPencil, faTrash } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { Modal } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Header from '../components/Header';
import axios from '../../services/axios';

function Products() {
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [show, setShow] = useState(false);
  const [selectedId, setSelectedId] = useState('');

  const getProducts = async () => {
    const { data } = await axios.get('/product');
    setProducts(data);
  };

  const deleteProductById = async () => {
    await axios.delete(`/product/${selectedId}`);
  };

  const handleCloseModal = () => setShow(false);
  const handleShowModal = (id) => {
    setShow(true);
    setSelectedId(id);
  };
  const handleConfirmModal = async () => {
    await deleteProductById();
    handleCloseModal();
    getProducts();
  };

  useEffect(() => {
    getProducts();
  }, []);

  return (

    <div>
      <Header />
      <h3 className="d-flex justify-content-center py-3">Products Management</h3>

      <div className="container">

        <div className="pb-3">
          <button type="button" className="btn btn-outline-primary" onClick={() => navigate('/create-product')}>
            <FontAwesomeIcon icon={faPlus} />
            {' '}
            Add Product
          </button>
        </div>

        <table className="table table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Value</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            {products && products.map((product, index) => (
              <tr key={product.id}>
                <th scope="row">{index + 1}</th>
                <td>{product.name}</td>
                <td>{product.value}</td>
                <td>
                  <button type="button" className="btn btn-outline-warning" onClick={() => navigate(`/edit-product/${product.id}`)}>
                    <FontAwesomeIcon icon={faPencil} />
                    {' '}
                    Edit
                  </button>
                  {' '}
                  /
                  {' '}
                  <button type="button" className="btn btn-outline-danger" onClick={() => handleShowModal(product.id)}>
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
          Do you really want to delete this product ?
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

export default Products;
