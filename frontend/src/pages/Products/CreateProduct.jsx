import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import Message from '../components/Message';
import axios from '../../services/axios';

function CreateProduct() {
  const navigate = useNavigate();

  const [name, setName] = useState('');
  const [value, setValue] = useState(0);
  const [message, setMessage] = useState('');

  const createNewProduct = async () => {
    const response = await axios.post('product', { name, value });
    if (response.status === 201) {
      setMessage('successfully registered product!');
      setName('');
      setValue(0);
    }
    // if (response.status === 409) setMessage('erro');
  };

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

          <div className="d-flex justify-content-around py-4">
            <button type="button" className="btn btn-outline-success" onClick={createNewProduct}>
              Submit
            </button>
            <button type="button" className="btn btn-outline-danger" onClick={() => navigate('/products')}>Cancel</button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default CreateProduct;
