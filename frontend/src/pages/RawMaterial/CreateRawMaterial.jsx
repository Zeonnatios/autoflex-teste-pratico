import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import Message from '../components/Message';

function CreateRawMaterial() {
  const navigate = useNavigate();

  const [name, setName] = useState('');
  const [stock, setStock] = useState(0);
  const [message, setMessage] = useState('');

  const createNewMaterial = async () => {
    const newRawMaterial = {
      name,
      stock: parseInt(stock, 10),
    };
    const request = await fetch('http://localhost:8080/material', {
      method: 'POST',
      headers: { Accept: 'application/json', 'Content-Type': 'application/json' },
      body: JSON.stringify(newRawMaterial),
    });

    const response = await request.json();
    if (response.status === 201) {
      setMessage('Successfully registered material!');
      setName('');
      setStock(0);
    }

    if (response.status === 400) {
      setName('');
      setStock(0);
      setMessage(`${response.error}`);
    }
  };

  return (
    <div>
      <Header />
      {message !== '' && <Message text={message} />}

      <h3 className="d-flex justify-content-center py-3">Register a new raw material</h3>

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
            <label id="labelStock" htmlFor="stock">
              Stock:
              <input
                className="form-control"
                data-testid="stock-input"
                placeholder="0"
                type="number"
                name="stock"
                id="stock"
                min={0}
                onChange={(e) => setStock(e.target.value)}
                value={stock}
                required
              />
            </label>
          </div>

          <div className="d-flex justify-content-around py-4">
            <button type="button" className="btn btn-outline-success" onClick={createNewMaterial}>
              Submit
            </button>
            <button type="button" className="btn btn-outline-danger" onClick={() => navigate('/raw-material')}>Cancel</button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default CreateRawMaterial;
