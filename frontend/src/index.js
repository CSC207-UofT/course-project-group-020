import React from 'react';
import ReactDOM from 'react-dom';
import Modal from 'react-modal';

import App from './App';
import './style.scss';

const root = document.getElementById('root');

Modal.setAppElement(root);

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  root
);
