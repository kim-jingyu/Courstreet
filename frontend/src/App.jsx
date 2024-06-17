import { useState } from 'react';
import './App.css';
import { Outlet, RouterProvider } from 'react-router-dom';

function App() {
  return (
    <>
      <Outlet />
    </>
  );
}

export default App;
