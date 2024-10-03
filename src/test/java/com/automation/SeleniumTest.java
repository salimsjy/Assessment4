package com.automation;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

//import static java.time.Duration.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SeleniumTest extends BaseTest{
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test add to cart product")
    public void testLoginPage(){
        openWeb("https://demoblaze.com/");
        selectFirstProductInCategory("Laptops");
        clickAddToCartButton();
        clickCartMenu();
        System.out.println("Item name: "+validateItemName());
    }

    @Step("Open Home Page")
    public void openWeb(String url){
        getDriver().get(url);
    }

    @Step("Select category and choose first product")
    public void selectFirstProductInCategory(String category){
        waitUntilElementVisibleByText(category);
        getDriver().findElement(By.linkText(category)).click();
        waitUntilElementVisibleByText("Sony vaio i5");
        waitUntilElementVisibleByXpath("//div[@id='tbodyid']/div[1]/div//h4/a");
        getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[1]/div//h4/a")).click();
    }

    @Step("Click add to cart button")
    public void clickAddToCartButton(){
        waitUntilElementVisibleByCssSelector(".btn-success");
        getDriver().findElement(By.cssSelector(".btn-success")).click();
//        getDriver().switchTo().alert().accept();
    }

    @Step("Click cart menu")
    public void clickCartMenu(){
        waitUntilElementVisibleById("cartur");
        getDriver().findElement(By.id("cartur")).click();
    }

    @Step("Navigate to the form authentication")
    public void navigateToFormAuthentication(){
        getDriver().findElement(By.linkText("Form Authentication")).click();
    }

    @Step("Fill Login form")
    public void fillLoginForm(String userName, String password){
        getDriver().findElement(By.id("username")).sendKeys(userName);
        getDriver().findElement(By.id("password")).sendKeys(password);
        getDriver().findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Step("Validate login success")
    public String validateLoginSuccess(){
        String succesMsg = getDriver().findElement(By.cssSelector(".flash.success")).getText();
        assertTrue(getDriver().findElement(By.cssSelector(".flash.success")).isDisplayed());
        return  succesMsg;
    }

    public String validateItemName(){
        waitUntilElementVisibleByCssSelector("td:nth-of-type(2)");
        String successMessage = getDriver().findElement(By.cssSelector("td:nth-of-type(2)")).getText();
        assertTrue(getDriver().findElement(By.cssSelector("td:nth-of-type(2)")).isDisplayed());
        return successMessage;
    }

    public void waitUntilElementVisibleById(String element){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
        }catch (Exception e){
            System.out.println("Element "+element+" Not Found");
        }
    }

    public void waitUntilElementVisibleByXpath(String element){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        }catch (Exception e){
            System.out.println("Element "+element+" Not Found");
        }
    }

    public void waitUntilElementVisibleByCssSelector(String element){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
        }catch (Exception e){
            System.out.println("Element "+element+" Not Found");
        }
    }

    public void waitUntilElementVisibleByText(String linkText){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
        }catch (Exception e){
            System.out.println("Link text "+linkText+" Not Found");
        }

    }
}
