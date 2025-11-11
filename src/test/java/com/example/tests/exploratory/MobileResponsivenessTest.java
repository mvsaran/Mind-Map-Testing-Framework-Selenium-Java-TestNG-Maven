package com.example.tests.exploratory;

import com.example.annotations.MindMap;
import com.example.pages.LoginPage;
import com.example.tests.TestBase;
import com.example.utils.WaitUtils;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MobileResponsivenessTest extends TestBase {

  @MindMap(node="UI -> Mobile Responsiveness", priority="P2", ci="Exploratory")
  @Test(groups = {"exploratory","P2"})
  public void checkMobileLayout(){
	  // set mobile size
	  driver.manage().window().setSize(new Dimension(375, 812));

	  LoginPage login = new LoginPage(driver);
	  login.open();
	  login.login("standard_user","secret_sauce");

	  // Wait for inventory list visible
	  WaitUtils.waitForVisibility(driver, By.cssSelector(".inventory_list"), 8);

	  // Wait for burger menu button to be visible and clickable
	  By burger = By.id("react-burger-menu-btn");
	  WebElement burgerBtn = WaitUtils.waitForClickable(driver, burger, 8);
	  burgerBtn.click();

	  // Wait for logout link in sidebar to appear
	  boolean present = WaitUtils.waitForVisibility(driver, By.id("logout_sidebar_link"), 8) != null;
	  Assert.assertTrue(present, "Sidebar menu did not open on mobile viewport");

	  // close menu and confirm inventory still visible
	  driver.findElement(By.id("react-burger-cross-btn")).click();
	  WaitUtils.waitForVisibility(driver, By.cssSelector(".inventory_list"), 5);
	}
}
