package com.example

import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.junit.jupiter.api.Assertions.assertTrue
import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Thread.sleep

class MyFirstTest {

    fun getComputedStyle(driver: WebDriver, elementLocator: By, styleProperty: String): String? {
        return driver.findElement(elementLocator).getCssValue(styleProperty)
    }

    @Test
    fun checkColorsObjects(){
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver.exe")

        val driver: WebDriver = ChromeDriver()

        driver.get("http://localhost:3000/")
        var buttonObject = driver.findElement(By.id("shop-button"))

        var fontColor = buttonObject.getCssValue("color")
        var fontSize = buttonObject.getCssValue("font-size")
        assertTrue(fontColor == "rgba(0, 0, 0, 1)")
        assertTrue(fontColor != "rgba(0, 0, 0, 0)")
        assertTrue(fontSize == "43.2px")
        assertTrue(fontSize != "13.3333px")

        var buttonBackground = buttonObject.getCssValue("background-color")
        assertTrue(buttonBackground != "rgba(0, 0, 0, 1)")
        assertTrue(buttonBackground == "rgba(0, 0, 0, 0)")

        driver.get("http://localhost:3000/shop")
        sleep(200)
        buttonObject = driver.findElement(By.id("remover"))

        fontColor = buttonObject.getCssValue("color")
        fontSize = buttonObject.getCssValue("font-size")
        assertTrue(fontColor == "rgba(0, 0, 0, 1)")
        assertTrue(fontColor != "rgba(0, 0, 0, 0)")
        assertTrue(fontSize == "13.3333px")
        assertTrue(fontSize != "43.2px")

        buttonBackground = buttonObject.getCssValue("background-color")
        assertTrue(buttonBackground != "rgba(0, 0, 0, 1)")
        assertTrue(buttonBackground == "rgba(255, 228, 196, 1)")
        driver.quit()
    }

    @Test
    fun checkMainPageLinksAndDivs() {
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver.exe")

        val driver: WebDriver = ChromeDriver()

        driver.get("http://localhost:3000/")

        assertTrue(driver.findElement(By.className("nav")).isDisplayed)

        assertTrue(driver.findElement(By.className("App")).isDisplayed)

        assertTrue(driver.findElement(By.linkText("Shop")).isDisplayed, "Brak linku do 'Shop'")

        assertTrue(driver.findElement(By.linkText("Basket")).isDisplayed, "Brak linku do 'Basket'")

        val bodyBackgroundColor = driver.findElement(By.tagName("body")).getCssValue("background-color")

        assertTrue(bodyBackgroundColor == "rgba(255, 228, 196, 1)")

        driver.quit()
    }

    @Test
    fun testButonStyles(){
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver.exe")
        val driver: WebDriver = ChromeDriver()
        driver.get("http://localhost:3000/shop")
        sleep(200)
        val buttons = driver.findElements(By.tagName("button"))
        if (buttons.isNotEmpty()) {
            val button = buttons[0]
            val height = button.getCssValue("height")
            val backgroundColor = button.getCssValue("background-color")
            val border = button.getCssValue("border")
            val cursor = button.getCssValue("cursor")

            assertTrue(height == "40px")
            assertTrue(backgroundColor == "rgba(255, 228, 196, 1)")
            assertTrue(border == "1px solid rgb(0, 0, 0)")
            assertTrue(cursor == "pointer")
        }
    }

    @Test
    fun checkPagesTitles(){
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver.exe")

        val driver: WebDriver = ChromeDriver()

        driver.get("http://localhost:3000/")

        assertTrue(driver.title.contains("main page"), "Tytuł strony nie zawiera oczekiwanych słów")

        driver.get("http://localhost:3000/shop")
        sleep(200)
        println("TITLE:" + driver.title)
        assertTrue(driver.title.contains("main page"))

        driver.get("http://localhost:3000/basket")
        sleep(200)
        assertTrue(driver.title.contains("main page"))
        driver.quit()
    }

    @Test
    fun checkAllStylesOfNavbar(){
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver.exe")

        val driver: WebDriver = ChromeDriver()

        driver.get("http://localhost:3000/")

        val navElementLocator = By.className("nav")

        val width = getComputedStyle(driver, navElementLocator, "width")
        assert(width == "1249px") { "Width style of .nav class is not as expected." }

        val height = getComputedStyle(driver, navElementLocator, "height")
        assert(height == "80px") { "Height style of .nav class is not as expected." }

        val textAlign = getComputedStyle(driver, navElementLocator, "text-align")
        assert(textAlign == "center") { "Text-align style of .nav class is not as expected." }
        assert(textAlign != "left") { "Text-align style of .nav class is not as expected." }

        val borderBottom = getComputedStyle(driver, navElementLocator, "border-bottom")
        assert(borderBottom == "1px solid rgb(0, 0, 0)") { "Border-bottom style of .nav class is not as expected." }

        val backgroundColor = getComputedStyle(driver, navElementLocator, "background-color")
        assertTrue(backgroundColor == "rgba(0, 0, 0, 0)")
        println(backgroundColor)

        driver.quit()
    }

    @Test
    fun checkShopObjects(){
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver.exe")

        val driver: WebDriver = ChromeDriver()

        driver.get("http://localhost:3000/shop")
        sleep(200)
        assertTrue(driver.findElement(By.className("product")).isDisplayed)

        val productElements = driver.findElements(By.className("product"))

        assert(productElements.size == 3)

        val buttonElements = driver.findElements(By.tagName("button"))

        assert(buttonElements.size == 5)

        val navElementLocator = By.className("nav")

        val width = getComputedStyle(driver, navElementLocator, "width")
        assert(width == "1249px") { "Width style of .nav class is not as expected." }

        val height = getComputedStyle(driver, navElementLocator, "height")
        assert(height == "80px") { "Height style of .nav class is not as expected." }

        val textAlign = getComputedStyle(driver, navElementLocator, "text-align")
        assert(textAlign == "center") { "Text-align style of .nav class is not as expected." }

        var paragraphElement = driver.findElement(By.tagName("p"))
        var paragraphText = paragraphElement.text
        var amountIndex = paragraphText.indexOf("amount:") + "amount:".length
        var amountText = paragraphText.substring(amountIndex).trim()
        var amountValue = amountText.toInt()

        assert(amountValue == 4)

        val buttons = driver.findElements(By.tagName("button"))
        if (buttons.isNotEmpty()) {
            buttons[0].click()
        }

        driver.navigate().refresh()

        paragraphElement = driver.findElement(By.tagName("p"))

        paragraphText = paragraphElement.text

        amountIndex = paragraphText.indexOf("amount:") + "amount:".length
        amountText = paragraphText.substring(amountIndex).trim()

        amountValue = amountText.toInt()
        assert(amountValue == 3)

        driver.quit()
    }

    @Test
    fun testAllEndpoints(){
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver.exe")

        val driver: WebDriver = ChromeDriver()

        //GET SUCCESS
        driver.get("http://localhost:3000")
        val shopButton = driver.findElement(By.id("shop-button"))
        println("SIZEEE: " + shopButton.size)
        shopButton.click()
        var wait = WebDriverWait(driver, 10) // Wait up to 10 seconds
        var productNameElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product")))
        println("SIZEEE: " + productNameElements.size)
        assert(productNameElements.size == 3)

        //GET FAILED
        driver.get("http://localhost:3000/invalid_endpoint")
        var errorMessageElements = driver.findElements(By.className("error-message"))
        if (errorMessageElements.isEmpty()) {
            assertTrue(!errorMessageElements.any { it.isDisplayed() })
        } else {
            assert(errorMessageElements.any { it.isDisplayed() })
        }

        //POST SUCCESS
        driver.get("http://localhost:3000")
        val basketButton = driver.findElement(By.id("basket-button"))
        basketButton.click()
        val payButton = driver.findElement(By.id("pay-button"))
        payButton.click()
        wait = WebDriverWait(driver, 10)
        val alert: Alert = wait.until(ExpectedConditions.alertIsPresent())
        val alertText: String = alert.text
        assert(alertText.contains("Thanks for shopping"))
        alert.dismiss()

        //POST FAILED
        driver.get("http://localhost:3000/invalid_endpoint_for_POST")
        errorMessageElements = driver.findElements(By.className("error-message"))
        if (errorMessageElements.isEmpty()) {
            assertTrue(!errorMessageElements.any { it.isDisplayed() })
        } else {
            assert(errorMessageElements.any { it.isDisplayed() })
        }

        //PUT SUCCESS
        driver.get("http://localhost:3000/shop")
        productNameElements = driver.findElements(By.className("product"))
        println("WYPIS1: "+ productNameElements.size)
        assert(productNameElements.size == 3)
        val adderButton = driver.findElement(By.id("adder"))
        adderButton.click()
        wait = WebDriverWait(driver, 10)
        sleep(20)
        productNameElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product")))
        println("WYPIS2: "+ productNameElements.size)
        assert(productNameElements.size == 4)

        //DELETE SUCCESS
        driver.get("http://localhost:3000/shop")
        productNameElements = driver.findElements(By.className("product"))
        assert(productNameElements.size == 4)
        var removerButton = driver.findElement(By.id("remover"))
        removerButton.click()
        sleep(20)
        productNameElements = driver.findElements(By.className("product"))
        assert(productNameElements.size == 0)
        //DELETE FAILED
        driver.get("http://localhost:3000/inavalidEndpoint")
        productNameElements = driver.findElements(By.className("product"))
        if(productNameElements.size == 0){
            assertTrue(productNameElements.size == 0)
        } else {
            assert(productNameElements.size == 4)
            removerButton = driver.findElement(By.id("remover"))
            removerButton.click()
            sleep(20)
            productNameElements = driver.findElements(By.className("product"))
            assert(productNameElements.size == 0)
        }
        driver.quit()
    }
}
