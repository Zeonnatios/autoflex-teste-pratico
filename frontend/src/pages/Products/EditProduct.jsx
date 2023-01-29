import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from '../../services/axios';
import Header from '../components/Header';
import Message from '../components/Message';

function EditProduct() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [name, setName] = useState('');
  const [value, setValue] = useState(0);
  const [message, setMessage] = useState('');
  const editProduct = async () => {
    const response = await axios.put(`product/${id}`, { name, value });
    if (response.status === 200) await setMessage('Successfully changed data');
  };

  useEffect(() => {
    const getProductById = async () => {
      const { data } = await axios.get(`/product/${id}`);
      setName(data.name);
      setValue(data.value);
    };

    getProductById();
  }, [id]);

  return (
    <div>
      <Header />
      {message !== '' && <Message text={message} />}

      <h3 className="d-flex justify-content-center py-3">Edit product</h3>

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
                value={name}
                onChange={(e) => setName(e.target.value)}
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
                value={value}
                onChange={(e) => setValue(e.target.value)}
                required
              />
            </label>
          </div>

          <div className="d-flex justify-content-around py-4">
            <button type="button" className="btn btn-outline-success" onClick={editProduct}>
              Submit
            </button>
            <button type="button" className="btn btn-outline-danger" onClick={() => navigate('/products')}>Cancel</button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default EditProduct;
