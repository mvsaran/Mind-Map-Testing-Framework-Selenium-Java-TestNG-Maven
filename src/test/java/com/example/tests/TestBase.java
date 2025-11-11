package com.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.example.utils.WaitUtils;

public abstract class TestBase {
  protected WebDriver driver;
  @BeforeMethod(alwaysRun = true)
  public void clearPopupsBeforeTest() {
    try {
      WaitUtils.clearBlockingDialogs(driver);
    } catch (Exception e) {
      // ignore: defensive logging only
      System.out.println("clearPopupsBeforeTest: " + e.getMessage());
    }
  }
  @BeforeClass(alwaysRun = true)
  @Parameters({"headless"})
  public void beforeClass(@Optional("false") String headless){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @AfterClass(alwaysRun = true)
  public void afterClass(){
    if(driver != null) driver.quit();
  }
}
