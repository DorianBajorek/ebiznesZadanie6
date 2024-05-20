// App.js
import './App.css';
import Basket from './components/Basket';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Products from './components/Products';
import { useState } from 'react';
import { useEffect } from "react";

function App() {
  const [boughtProducts, setBoughtProducts] = useState([]);
  const [totalSpentMoney, setTotalSpentMoney] = useState(0);

  const addToBoughtProducts = (product) => {
    setBoughtProducts([...boughtProducts, product]);
  };

  const updateTotalSpentMoney = (price) => {
    setTotalSpentMoney(totalSpentMoney + price);
  };

  useEffect(() => {
    document.title = "main page";
  }, []);

  return (
    <Router>
      <div className="App">
        <div className="nav">
          <Link to="/shop" id="shop-button">Shop</Link>
          <Link to="/basket" id="basket-button">Basket</Link>
        </div>
        <Routes>
          <Route exact path="/basket" element={<Basket boughtProducts={boughtProducts} totalSpentMoney={totalSpentMoney} />} />
          <Route exact path="/shop" element={<Products addToBoughtProducts={addToBoughtProducts} updateTotalSpentMoney={updateTotalSpentMoney} />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
