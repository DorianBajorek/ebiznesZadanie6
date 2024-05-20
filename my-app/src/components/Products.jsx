// Products.jsx
import { useEffect } from "react";
import axios from "axios";
import { useState } from "react";
import '../styles/Products.css';

function Products({ addToBoughtProducts, updateTotalSpentMoney }) {

    const [products, setProducts] = useState([]);

    const fetchProducts = async () => {
        const response = await axios.get('http://localhost:8080/api/products');
        console.log(response.data);
        setProducts(response.data.map(product => ({
            name: product.first,
            price: product.second,
            amount: product.third
        })));
    };

    useEffect(() => {
        fetchProducts();
    }, []);

    const addToBasket = async (product) => {
        var productName = product.name;
        var productPrice = product.price;
        console.log(productName);
        const response = await axios.post('http://localhost:8080/api/sellProduct', productName);
        console.log(response.data);
        if(response.data === true){
            updateTotalSpentMoney(productPrice);
            addToBoughtProducts(product);
            fetchProducts();
        }
    }

    const clearShop = async() => {
        const response = await axios.delete('http://localhost:8080/api/deleteProduct');
        console.log("DONE")
        fetchProducts()
    }

    const addNewProduct = async() => {
        const response = await axios.put('http://localhost:8080/api/putProduct');
        console.log("DONE")
        fetchProducts()
    }

    return (
        <div className="products-content">
            <div className="products-nav">
                Products
            </div>
            <div className="list-of-products">
                {products.map(product => (
                    <div className="product" key={product.name}>
                        <p>{`${product.name} - ${product.price}, amount: ${product.amount}`}</p>
                        <button id="add-basket" onClick={() => addToBasket(product)}>Add to Basket</button>
                    </div>
                    
                ))}
                <button id="remover" onClick={clearShop}>Remove all product</button>
                <button id="adder" onClick={addNewProduct}>Remove all product</button>
            </div>
        </div>
    );
}

export default Products;
