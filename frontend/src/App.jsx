import './App.style.js';
import { Outlet } from 'react-router-dom';
import Header from './components/commons/header/Header';
import { OutletWrapper } from './App.style.js';
import { useState } from 'react';

function App() {

  return (
    <>
      <Header />
      <OutletWrapper>
        <Outlet />
      </OutletWrapper>
    </>
  );
}

export default App;