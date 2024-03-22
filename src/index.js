import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

reportWebVitals();
/**
 index.html -> index.js -> App.jsx
 import ReactDOM from 'react-dom/client'; 을 통해 리액트 전반적인 라이브러리를 가져온다.
 이후 App.js를 가져와서 렌더링한다.


 */