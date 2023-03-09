import React from 'react';
import PropTypes from 'prop-types';

function TableProductMaterials({ materialList, removeMaterialFromList, updateQuantity }) {
  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Name</th>
          <th scope="col">Stock</th>
          <th scope="col">Quantity</th>
          <th scope="col">Action</th>
        </tr>
      </thead>
      <tbody>
        {materialList && materialList.map((material, index) => (
          <tr key={material.id}>
            <th scope="row">{index + 1}</th>
            <td>{material.name}</td>
            <td>{material.stock}</td>
            <td>
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
            </td>
            <td>
              <button
                type="button"
                className="btn btn-outline-danger"
                onClick={() => removeMaterialFromList(material)}
              >
                Remove
              </button>

            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

TableProductMaterials.propTypes = {
  removeMaterialFromList: PropTypes.func.isRequired,
  updateQuantity: PropTypes.func.isRequired,
  materialList: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string,
      name: PropTypes.string,
      stock: PropTypes.number,
    }),
  ).isRequired,
};

export default TableProductMaterials;
