
import './css/App.css';
import 'bootstrap/dist/css/bootstrap.css';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './Home';
import ProductPage from './components/product/ProductPage';
import CreateProductForm from './components/admin/CreateProductForm.js';


function App() {
  return (
    <div className="App">
      <Navbar></Navbar>
      <div className='content'>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/items/:productId' element={<ProductPage />} />
          <Route path='/create-product/' element={<CreateProductForm/>} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
