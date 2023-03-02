import React from 'react';
import { Modal } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import PropTypes from 'prop-types';

function ModalProductMaterials({
  show, handleCloseModal, addToMaterialListToStorage, rawMaterials,
}) {
  return (
    <Modal show={show} onHide={handleCloseModal}>
      <Modal.Header closeButton>
        <Modal.Title>Bind materials to the product</Modal.Title>
      </Modal.Header>
      <Modal.Body>
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
            {rawMaterials && rawMaterials.map((material, index) => (
              <tr key={material.id}>
                <th scope="row">{index + 1}</th>
                <td>{material.name}</td>
                <td>{material.stock}</td>
                <td>
                  <button
                    type="button"
                    className="btn btn-outline-primary"
                    onClick={() => addToMaterialListToStorage(material)}
                  >
                    <FontAwesomeIcon icon={faPlus} />
                    {' '}
                    Bind to
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </Modal.Body>
    </Modal>

  );
}

ModalProductMaterials.propTypes = {
  show: PropTypes.bool.isRequired,
  handleCloseModal: PropTypes.func.isRequired,
  addToMaterialListToStorage: PropTypes.func.isRequired,
  rawMaterials: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string,
      name: PropTypes.string,
      stock: PropTypes.number,
    }),
  ).isRequired,
};

export default ModalProductMaterials;
