import './App.css';
import { CssBaseline } from '@mui/material';
import { Route, Routes, useLocation } from 'react-router-dom';

import city from './assets/city.gif'
import Test from './pages/Test';
import HomePage from './pages/HomePage';
import Login from './pages/Login';
import Header from './components/Header';
import Listings from './pages/Listings';
import PropertyDetails from './pages/PropertyDetails';
import LogOut from './pages/LogOut';
import SignUp from './pages/SignUp';
import MainDetails from './pages/MainDetails';



function App() {
  const location = useLocation();
  const bgStyle = location.pathname === '/' ?  {
    'backgroundImage' : `url(${city})`, height:'100vh',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat'
  } : {background:'transparent' } ;
  return (
      <div style={{...bgStyle }}>
      <CssBaseline />
      <Header />
      <main className='py-3'>
        <Routes>
          <Route exact path='/' element={<HomePage/>}/>
          <Route exact path='/signup' element={<SignUp />}/>
          <Route exact path='/login' element={<Login/>}/>
          <Route exact path='/logout' element={<LogOut />}/>
          <Route exact path='/listings' element={<Listings />}/>
          <Route exact path='/property/:id' element={<PropertyDetails />}/>
          <Route exact path='/mainDetails' element={<MainDetails />}/>
        </Routes>
        {/* <Test /> */}
      </main>
    </div>   
  );
}

export default App;
