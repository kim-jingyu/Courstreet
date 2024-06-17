import './App.style.js';
import { Outlet } from 'react-router-dom';
import Footer from './components/commons/footer/Footer';
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
      <Footer />
      <NavFooter />
    </>
  );
}

export default App;
