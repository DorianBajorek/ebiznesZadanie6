package com.example

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.*
import kotlin.random.Random


@CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
@RestController
class ShopController {
    val products = mutableSetOf(
        Product("Laptop", 999.99, "electronic", 4),
        Product("Smartphone", 699.99, "electronic" ,3),
        Product("Tablet", 399.99, "electronic", 5)
    )

    val person = Person(4000.0)

    @GetMapping("/api/products")
    fun getRanks(): List<Triple<String, Double, Int>> {
        val productList = mutableListOf<Triple<String, Double, Int>>()
        for (product in products) {
            productList.add(Triple(product.name, product.price, product.amount))
        }
        return productList
    }

    @PostMapping("/api/sellProduct")
    fun sellProduct(@RequestBody productToSell: String): Boolean {
        val modifiedProductToSell = productToSell.substring(0, productToSell.length - 1)
        for (product in products) {
            if (product.name == modifiedProductToSell && product.isAvailable()) {
                product.decraseAmount()
                if (!product.isAvailable()) {
                    products.remove(product)
                }
                return true
            }
        }
        return false
    }

    @PostMapping("/api/payForProducts")
    fun payForProducts(@RequestBody valueToPayString: String): Boolean {
        val objectMapper = ObjectMapper()
        val valueToPayNode = objectMapper.readTree(valueToPayString)
        val valueToPay = valueToPayNode.get("valueToPay").asDouble()
        if(person.enoughMoney(valueToPay)){
            person.spendMoney(valueToPay)
            return true
        }
        return false
    }

    @DeleteMapping("/api/deleteProduct")
    fun deleteProduct(): Boolean {
        products.clear()
        return true
    }

    @PutMapping("/api/putProduct")
    fun putProduct(): Boolean {
        var newProduct: Product = Product("Camer", 235.0, "electronic", 1)
        products.add(newProduct)
        return true
    }
}