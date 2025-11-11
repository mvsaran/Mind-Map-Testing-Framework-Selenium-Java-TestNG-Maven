package com.example.tests.regression;

import com.example.annotations.MindMap;
import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;
import com.example.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import java.util.List;
import java.util.stream.Collectors;

public class SortVerificationTest extends TestBase {

  @MindMap(node="Products -> Sort & Filter", priority="P1", ci="Nightly")
  @Test(groups = {"regression","P1"})
  public void verifySortLowToHigh(){
    LoginPage login = new LoginPage(driver);
    InventoryPage inv = new InventoryPage(driver);
    login.open(); login.login("standard_user","secret_sauce");
    // set sort low->high
    driver.findElement(By.className("product_sort_container")).click();
    driver.findElement(By.xpath("//option[text()='Price (low to high)']")).click();
    List<Double> prices = driver.findElements(By.className("inventory_item_price"))
            .stream().map(e -> Double.parseDouble(e.getText().replace("$","")))
            .collect(Collectors.toList());
    List<Double> sorted = prices.stream().sorted().collect(Collectors.toList());
    Assert.assertEquals(prices, sorted);
  }
}
