import './App.css';
import { Outlet } from 'react-router-dom';
import Footer from './components/commons/footer/Footer';
import Header from './components/commons/header/Header';
import NavFooter from './components/commons/navfooter/NavFooter';

function AppContent() {
  return(
    <>
      <Outlet />
      <Footer />
    </>
  )
}

function App() {
  return (
    <>
      <Header />
      <AppContent />
      <NavFooter />
    </>
  );
}

export default App;
