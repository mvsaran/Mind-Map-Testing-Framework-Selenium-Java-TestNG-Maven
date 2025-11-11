package com.example.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class InventoryPage {
  private WebDriver driver;
  public InventoryPage(WebDriver driver){ this.driver = driver; }
  private By item = By.className("inventory_item");
  private By cartLink = By.className("shopping_cart_link");
  private By addBtn(String id){ return By.id("add-to-cart-" + id); }

  public void addToCart(String productId){ driver.findElement(addBtn(productId)).click(); }
  public void openCart(){ driver.findElement(cartLink).click(); }
  public boolean isOnInventory(){ return driver.getCurrentUrl().contains("inventory.html"); }
  public List<?> items(){ return driver.findElements(item); }
}
