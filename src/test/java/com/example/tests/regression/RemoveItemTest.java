package com.example.tests.regression;

import com.example.annotations.MindMap;
import com.example.pages.*;
import com.example.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveItemTest extends TestBase {

  @MindMap(node="Cart -> Add/Remove", priority="P1", ci="Nightly")
  @Test(groups = {"regression","P1"})
  public void addAndRemoveItem(){
    LoginPage login = new LoginPage(driver);
    InventoryPage inv = new InventoryPage(driver);
    CartPage cart = new CartPage(driver);

    login.open(); login.login("standard_user","secret_sauce");
    inv.addToCart("sauce-labs-backpack");
    inv.openCart();
    org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
    // Ensure item is present before removal
    wait.until(d -> cart.isItemInCart("Sauce Labs Backpack"));
    Assert.assertTrue(cart.isItemInCart("Sauce Labs Backpack"));
    cart.removeItem("sauce-labs-backpack");
    // Wait for item to disappear
    wait.until(d -> !cart.isItemInCart("Sauce Labs Backpack"));
    Assert.assertFalse(cart.isItemInCart("Sauce Labs Backpack"));
  }
}