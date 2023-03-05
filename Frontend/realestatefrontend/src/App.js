import './App.css';
import { CssBaseline } from '@mui/material';
import { Route, Routes, useLocation } from 'react-router-dom';

import Header from './components/Header';
import Footer from './components/Footer';
import List from './pages/Listings';
import SearchbarTest from './testing/SearchbarTest';
import PropertyDetails from './testing/PropertyDetails';
import Del from './testing/Del';
import AddNewProperty from './testing/AddNewProperty';
import LocalityDetails from './testing/LocalityDetails';
import Login from './pages/Login';
import HomePage from './pages/HomePage'
import LoadingScreen from './components/LoadingScreen';
import AlertBox from './components/AlertBox';

import city from './assets/city.gif'
import Listings from './pages/Listings';


function App() {
  const location = useLocation();
  const bgStyle = location.pathname === '/' ?  {
    'backgroundImage' : `url(${city})`, height:'100vh',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat'
  } : {background:'transparent' } ;
  return (
    //   <div style={{...bgStyle }}>
    //   <CssBaseline />
    //   <Header />
    //   <main className='py-3'>
    //     <Routes>
    //       <Route exact path='/' element={<HomePage/>}/>
    //       <Route exact path='/login' element={<Login/>}/>
    //     </Routes>
    //   </main>
    // </div>

    <>
    <CssBaseline />
      <Header />
      {/* <Listings /> */}
      {/* <Searchbar /> */}
      {/* <PropertyDetails /> */}
      {/* <Del /> */}
      {/* <AddNewProperty /> */}
      {/* <LocalityDetails /> */}
      {/* <Footer /> */}
      {/* <Login /> */}
      {/* <LoadingScreen /> */}
      {/* <AlertBox /> */}
      {/* <HomePage /> */}
      <Listings />
     
    </>
  );
}

export default App;
