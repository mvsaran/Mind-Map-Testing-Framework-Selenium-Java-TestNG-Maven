package com.example.tests.exploratory;

import com.example.annotations.MindMap;
import com.example.pages.*;
import com.example.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BackNavTest extends TestBase {

  @MindMap(node="Checkout -> Back Navigation", priority="P2", ci="Exploratory")
  @Test(groups = {"exploratory","P2"})
  public void navigateBackDuringCheckout(){
    LoginPage login = new LoginPage(driver);
    InventoryPage inv = new InventoryPage(driver);
    CartPage cart = new CartPage(driver);
    CheckoutPage checkout = new CheckoutPage(driver);

    login.open(); login.login("standard_user","secret_sauce");
    inv.addToCart("sauce-labs-backpack");
    inv.openCart();
    cart.proceedToCheckout();
    driver.navigate().back();
    Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    Assert.assertTrue(cart.isItemInCart("Sauce Labs Backpack"));
    driver.navigate().forward();
    checkout.fillInfo("Back","Nav","560001");
    checkout.finish();
    Assert.assertTrue(checkout.isComplete());
  }
}
