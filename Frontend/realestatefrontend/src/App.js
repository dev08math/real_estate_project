import './App.css';
import { CssBaseline } from '@mui/material';
import { Route, Routes } from 'react-router-dom';

import Header from './components/Header';
import Footer from './components/Footer';
import Listings from './pages/Listings';
import Searchbar from './testing/Searchbar';
import PropertyDetails from './testing/PropertyDetails';
import Del from './testing/Del';
import AddNewProperty from './testing/AddNewProperty';
import LocalityDetails from './testing/LocalityDetails';
import Login from './pages/Login';
import HomePage from './pages/HomePage'
import LoadingScreen from './components/LoadingScreen';
import AlertBox from './components/AlertBox';


function App() {
  return (
    <div>
      <CssBaseline />
      <Header />
      <main className='py-3'>
        <Routes>
          <Route exact path='/' element={<HomePage/>}/>
          <Route exact path='/login' element={<Login/>}/>
        </Routes>
      </main>
    </div>

    // <>
    // <CssBaseline />
    //   <Header />
    //   {/* <Listings /> */}
    //   {/* <Searchbar /> */}
    //   {/* <PropertyDetails /> */}
    //   {/* <Del /> */}
    //   {/* <AddNewProperty /> */}
    //   {/* <LocalityDetails /> */}
    //   {/* <Footer /> */}
    //   {/* <Login /> */}
    //   {/* <LoadingScreen /> */}
    //   <AlertBox />
    // </>
  );
}

export default App;
