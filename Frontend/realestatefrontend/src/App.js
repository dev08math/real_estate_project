import './App.css';
import { CssBaseline } from '@mui/material';

import Header from './components/Header';
import Footer from './components/Footer';
import Listings from './pages/Listings';
import Searchbar from './testing/Searchbar';

function App() {
  return (
    <>
    <CssBaseline />
    <Header />
      {/* <Listings /> */}
      <Searchbar />
    <Footer />
    </>
  );
}

export default App;
