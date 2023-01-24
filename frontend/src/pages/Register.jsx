import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { setStorage, userStorage } from '../helpers/Storage';

function Register() {
  const [register, setRegister] = useState({ name: '', email: '', password: '' });
  const [btnDisable, setBtnDisable] = useState(false);
  const [registerError, setRegisterError] = useState('');

  const navigate = useNavigate();

  function handleChange({ target: { name, value } }) {
    setRegister({ ...register, [name]: value });
  }

  useEffect(() => {
    const { name, email, password } = register;
    const minPasswordLength = 8;
    const emailFormat = /^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,15}$/;

    if (emailFormat.test(email) && password.length >= minPasswordLength && name.length > 0) {
      setBtnDisable(false);
    } else setBtnDisable(true);
  }, [register]);

  const signUp = async () => {
    await axios({
      method: 'post',
      url: 'http://localhost:8080/user/register',
      data: {
        name: register.name, email: register.email, password: register.password,
      },
    })
      .then((response) => {
        if (response) {
          setStorage('user', userStorage(response));
          navigate('/factory-management');
        }
      })
      .catch(({ response }) => {
        setRegisterError(response.data.message);
      });
  };

  return (

    <main id="main" className="container my-5">

      {registerError !== '' && (
      <div className="alert alert-danger" id="register-error-message" role="alert">
        {registerError}
      </div>
      )}

      <section>
        <form>
          <h1 className="h1" id="title">Criar conta</h1>

          <div className="form-group row">
            <label id="labelUserName" htmlFor="name" className="col-lg-12col-form-label">
              Username:
              <div className="col-lg-12">
                <input
                  className="form-control"
                  data-testid="name-input"
                  placeholder="Name"
                  type="name"
                  name="name"
                  id="name"
                  onKeyUp={handleChange}
                />
              </div>
            </label>
          </div>

          <div className="form-group row">
            <label id="labelEmail" htmlFor="email" className="col-lg-12col-form-label">
              Email:
              <div className="col-lg-12">
                <input
                  className="form-control"
                  data-testid="email-input"
                  placeholder="Email"
                  type="email"
                  name="email"
                  id="email"
                  onKeyUp={handleChange}
                />
              </div>
            </label>
          </div>

          <div className="form-group row">
            <label id="labelPassword" htmlFor="password" className="col-lg-12 col-form-label">
              Password:
              <div className="col-lg-12">
                <input
                  className="form-control"
                  data-testid="password-input"
                  placeholder="Password"
                  type="password"
                  name="password"
                  minLength="8"
                  id="password"
                  onKeyUp={handleChange}
                />
              </div>
            </label>
          </div>

          <button
            className="w-100 btn btn-lg btn-success my-3"
            type="button"
            data-testid="register-submit-btn"
            disabled={btnDisable}
            onClick={() => signUp()}
          >
            Cadastrar-se
          </button>

        </form>

      </section>

    </main>
  );
}

export default Register;
