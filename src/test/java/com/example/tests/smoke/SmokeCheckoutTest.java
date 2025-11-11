package com.example.tests.smoke;

import com.example.annotations.MindMap;
import com.example.pages.*;
import com.example.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeCheckoutTest extends TestBase {

  @MindMap(node="Checkout -> Happy Path", priority="P0", ci="PR-smoke")
  @Test(groups = {"smoke","P0"})
  public void addToCartAndCheckout(){
    LoginPage login = new LoginPage(driver);
    InventoryPage inv = new InventoryPage(driver);
    CartPage cart = new CartPage(driver);
    CheckoutPage checkout = new CheckoutPage(driver);

    login.open(); login.login("standard_user","secret_sauce");
    Assert.assertTrue(inv.isOnInventory());
    inv.addToCart("sauce-labs-backpack");
    inv.openCart();
    Assert.assertTrue(cart.isItemInCart("Sauce Labs Backpack"));
    cart.proceedToCheckout();
    checkout.fillInfo("Test","User","560001");
    checkout.finish();
    Assert.assertTrue(checkout.isComplete());
  }
}
