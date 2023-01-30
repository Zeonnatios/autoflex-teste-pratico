import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Modal } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import Header from '../components/Header';
import Message from '../components/Message';
import axios from '../../services/axios';
import { setStorage } from '../../helpers/Storage';

function CreateProduct() {
  const navigate = useNavigate();
  const [materials, setMaterials] = useState([]);
  const [name, setName] = useState('');
  const [value, setValue] = useState(0);
  const [message, setMessage] = useState('');
  const [show, setShow] = useState(false);
  const [materialList, setMaterialList] = useState([]);

  const handleCloseModal = () => setShow(false);
  const handleShowModal = () => setShow(true);

  const addToMaterialListToStorage = (material) => {
    if (!materialList.some((item) => item.id === material.id)) {
      const newMaterial = { ...material, quantity: 0 };
      setMaterialList([...materialList, newMaterial]);
      setStorage('materialsList', [...materialList, newMaterial]);
    }
  };

  const updateQuantity = (material, quantity) => {
    const updatedMaterialList = materialList.map((item) => {
      const newItem = { ...item };
      if (newItem.id === material.id) {
        newItem.quantity = quantity;
      }
      return newItem;
    });
    setMaterialList(updatedMaterialList);
    setStorage('materialsList', [updatedMaterialList]);
    console.log(materialList);
  };

  const createNewProduct = async () => {
    const response = await axios.post('product', { name, value });
    if (response.status === 201) {
      setMessage('Successfully registered product!');
      setName('');
      setValue(0);
    }
    // if (response.status === 409) setMessage('erro');
  };

  const getMaterials = async () => {
    const { data } = await axios.get('/material');
    setMaterials(data);
  };

  useEffect(() => {
    getMaterials();
  }, []);

  return (
    <div>
      <Header />
      {message !== '' && <Message text={message} />}
      <h3 className="d-flex justify-content-center py-3">Register a new product</h3>

      <div className="container">
        <form className="col-lg-5 mx-auto">
          <div className="form-group row py-2">
            <label id="labelName" htmlFor="name" className="col-lg-12 col-form-label">
              Name:
              <input
                type="text"
                data-testid="name-input"
                className="form-control"
                placeholder="name"
                name="name"
                id="name"
                onChange={(e) => setName(e.target.value)}
                value={name}
                required
              />
            </label>
          </div>

          <div className="form-group row">
            <label id="labelValue" htmlFor="value">
              Value:
              <input
                className="form-control"
                data-testid="value-input"
                placeholder="0"
                type="number"
                name="value"
                id="value"
                min={0}
                onChange={(e) => setValue(e.target.value)}
                value={value}
                required
              />
            </label>
          </div>

          <div className="py-3 d-flex justify-content-end">
            <button type="button" className="btn btn-outline-primary" onClick={handleShowModal}>
              <FontAwesomeIcon icon={faPlus} />
              {' '}
              Bind Raw Materials
            </button>
          </div>

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
                  {materials && materials.map((material, index) => (
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

          {materialList.length > 0 && <h4 className="d-flex justify-content-center py-1">Inform the quantities!</h4>}
          {materialList.length > 0 && materialList.map((material) => (
            <div key={material.id}>
              <div className="form-inline">
                <p>{material.name}</p>

                <input
                  className="form-control"
                  placeholder="0"
                  type="number"
                  name={`${material.name}-value`}
                  id={`${material.name}-value`}
                  min={0}
                  onChange={(e) => updateQuantity(material, e.target.value)}
                  required
                />
              </div>
            </div>

          ))}

          <div className="d-flex justify-content-around py-4">
            <button type="button" className="btn btn-outline-success" onClick={createNewProduct}>
              Submit
            </button>
            <button
              type="button"
              className="btn btn-outline-danger"
              onClick={() => navigate('/products')}
            >
              Cancel

            </button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default CreateProduct;
