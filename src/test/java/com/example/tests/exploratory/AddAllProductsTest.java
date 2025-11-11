package com.example.tests.exploratory;

import com.example.annotations.MindMap;
import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;
import com.example.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class AddAllProductsTest extends TestBase {

  @MindMap(node="Cart -> Add All Products", priority="P2", ci="Exploratory")
  @Test(groups = {"exploratory","P2"})
  public void addAllProductsToCart(){
    LoginPage login = new LoginPage(driver);
    InventoryPage inv = new InventoryPage(driver);
    login.open(); login.login("standard_user","secret_sauce");
    // Ensure all items are interactable before clicking
    inv.items().forEach(i -> {
      org.openqa.selenium.WebElement btn = ((org.openqa.selenium.WebElement)i).findElement(By.tagName("button"));
      if (btn.isDisplayed() && btn.isEnabled()) btn.click();
    });
    int expected = inv.items().size();
    String badge = driver.findElement(By.className("shopping_cart_badge")).getText();
    Assert.assertEquals(Integer.parseInt(badge), expected);
  }
}