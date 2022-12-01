import './App.css';
import { CssBaseline } from '@mui/material';

import Header from './components/Header';
import Footer from './components/Footer';
import Listings from './pages/Listings';
import Searchbar from './testing/Searchbar';
import PropertyDetails from './testing/PropertyDetails';
import Del from './testing/Del';
import AddNewProperty from './testing/AddNewProperty';
import LocalityDetails from './testing/LocalityDetails';

function App() {
  return (
    <>
    <CssBaseline />
    <Header />
      {/* <Listings /> */}
      {/* <Searchbar /> */}
      {/* <PropertyDetails /> */}
      {/* <Del /> */}
      {/* <AddNewProperty /> */}
      <LocalityDetails />
    {/* <Footer /> */}
    </>
  );
}

export default App;
