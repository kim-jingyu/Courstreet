import './App.style.js';
import { Outlet } from 'react-router-dom';
import Header from './components/commons/header/Header';
import NavFooter from './components/commons/navfooter/NavFooter';
import { OutletWrapper } from './App.style.js';

function App() {

  return (
    <>
      <Header />
      <OutletWrapper>
        <Outlet />
      </OutletWrapper>
      <NavFooter />
    </>
  );
}

export default App;