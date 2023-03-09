import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import Header from '../components/Header';
import Message from '../components/Message';
import axios from '../../services/axios';
import { setStorage, getStorage } from '../../helpers/Storage';
import ModalProductMaterials from '../components/ModalProductMaterials';
import TableProductMaterials from '../components/TableProductMaterials';

function CreateProduct() {
  const navigate = useNavigate();
  const [rawMaterials, setRawMaterials] = useState([]);
  const [name, setName] = useState('');
  const [value, setValue] = useState(0);
  const [message, setMessage] = useState('');
  const [show, setShow] = useState(false);
  const [materialList, setMaterialList] = useState([]);
  const [, setRecipeList] = useState([]);

  const handleCloseModal = () => setShow(false);
  const handleShowModal = () => setShow(true);

  const createRecipe = (productId) => {
    const data = materialList.map((item) => ({
      product: { id: productId },
      material: { id: item.id },
      quantity: parseInt(item.quantity, 10),
    }));
    setRecipeList(data);
    setStorage('recipeList', data);
  };

  const addToMaterialListToStorage = (material) => {
    if (!materialList.some((item) => item.id === material.id)) {
      const newMaterial = { ...material };
      setMaterialList([...materialList, newMaterial]);
      setStorage('materialsList', [...materialList, newMaterial]);
    }
  };

  const removeMaterialFromList = (material) => {
    const newList = materialList.filter((item) => item.id !== material.id);
    setMaterialList(newList);
    setStorage('materialsList', newList);
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
  };

  const createNewProduct = async () => {
    const response = await axios.post('product', { name, value });
    if (response.status === 201) {
      setMessage('Successfully registered product!');
    }
    const productId = response.data.id;
    createRecipe(productId);

    const recipes = getStorage('recipeList');
    recipes.forEach(async (item) => {
      await axios.post('recipe', item);
    });

    setName('');
    setValue(0);
    setStorage('materialsList', []);
    setMaterialList([]);
    setStorage('recipeList', []);
  };

  const getMaterials = async () => {
    const { data } = await axios.get('/material');
    setRawMaterials(data);
  };

  useEffect(() => {
    getMaterials();
  }, []);

  return (
    <div>
      <Header />
      <ModalProductMaterials
        show={show}
        handleCloseModal={handleCloseModal}
        addToMaterialListToStorage={addToMaterialListToStorage}
        rawMaterials={rawMaterials}
      />
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

          <h4 className="d-flex justify-content-center py-1">Materials</h4>
          <TableProductMaterials
            materialList={materialList}
            removeMaterialFromList={removeMaterialFromList}
            updateQuantity={updateQuantity}
          />

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
