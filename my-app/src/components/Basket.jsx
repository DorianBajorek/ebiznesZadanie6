import axios from 'axios';
import '../styles/Basket.css';
import Payment from './Payment';
import { useEffect } from "react";
function Basket({ boughtProducts, totalSpentMoney }) {
    
    return (
        <div className="basket-content">
            <h2>Basket</h2>
            <div>
                <h3>Bought Products:</h3>
                <ul>
                    {boughtProducts.map(product => (
                        <div>
                        <li key={product.name}>{product.name} - {product.price}</li>
                        </div>
                    ))}
                </ul>
            </div>
            <Payment totalSpentMoney={totalSpentMoney}/>
        </div>
    );
}

export default Basket;
