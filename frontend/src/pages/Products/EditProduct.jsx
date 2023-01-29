import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Header from '../components/Header';

function EditProduct() {
  const navigate = useNavigate();
  const { id } = useParams();
  console.log(id);
  const [product, setProduct] = useState({ name: '', value: 0 });
  function handleChange({ target: { name, value } }) {
    setProduct({ ...product, [name]: value });
  }

  return (
    <div>
      <Header />

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
                onKeyUp={handleChange}
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
                onKeyUp={handleChange}
                required
              />
            </label>
          </div>

          <div className="d-flex justify-content-around py-4">
            <button type="button" className="btn btn-outline-success">
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
