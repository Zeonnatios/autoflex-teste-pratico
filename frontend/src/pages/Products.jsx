import React from 'react';
import Header from './components/Header';

function Products() {
  return (

    <div>
      <Header />

      <h2 className="d-flex justify-content-center py-5">Products Management</h2>

      <div className="container">

        <div className="pb-3">
          <button type="button" className="btn btn-outline-primary">+ Product</button>
        </div>

        <table className="table table-hover table-dark">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Value</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>edit / delete</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

  );
}

export default Products;
