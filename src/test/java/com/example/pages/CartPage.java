package com.example.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
  private WebDriver driver;
  private WebDriverWait wait;
  public CartPage(WebDriver driver){ 
    this.driver = driver; 
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }
  private By checkout = By.cssSelector("[data-test='checkout']");
  private By remove(String id){ return By.id("remove-" + id); }
  private By cartItem(String name){ return By.xpath("//div[contains(@class,'cart_item')]//div[text()='" + name + "']"); }

  public void proceedToCheckout(){
    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkout));
    btn.click();
    // Wait for checkout page to load (first-name field)
    new WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.or(
        ExpectedConditions.visibilityOfElementLocated(By.id("first-name")),
        ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))));
  }
  public void removeItem(String id){
    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(remove(id)));
    btn.click();
    // Wait for item to be removed from cart
    new WebDriverWait(driver, Duration.ofSeconds(5))
      .until(ExpectedConditions.invisibilityOfElementLocated(remove(id)));
  }
  public boolean isItemInCart(String name){
    boolean present = !driver.findElements(cartItem(name)).isEmpty();
    System.out.println("isItemInCart(" + name + "): " + present);
    return present;
  }
}