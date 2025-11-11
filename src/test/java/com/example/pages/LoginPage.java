package com.example.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
  private WebDriver driver;
  private By user = By.id("user-name");
  private By pass = By.id("password");
  private By loginBtn = By.id("login-button");
  private By error = By.cssSelector("[data-test='error']");

  public LoginPage(WebDriver driver){ this.driver = driver; }
  public void open(){ driver.get("https://www.saucedemo.com/"); }
  public void login(String u, String p){
    driver.findElement(user).clear(); driver.findElement(user).sendKeys(u);
    driver.findElement(pass).clear(); driver.findElement(pass).sendKeys(p);
    driver.findElement(loginBtn).click();
  }
  public String getErrorText(){ return driver.findElement(error).getText(); }
}
