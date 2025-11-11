package com.example.pages;

import com.example.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    public CheckoutPage(WebDriver driver){ this.driver = driver; }

    private By fn = By.id("first-name"),
                ln = By.id("last-name"),
                pcode = By.id("postal-code"),
                cont = By.id("continue"),
                finishBtn = By.id("finish"),
                complete = By.className("complete-header");

    public void fillInfo(String first, String last, String postal){
        WaitUtils.waitForVisibility(driver, fn, 10).sendKeys(first);
        driver.findElement(ln).sendKeys(last);
        driver.findElement(pcode).sendKeys(postal);
        driver.findElement(cont).click();
    }

    public void finish(){
    	  By finishBtn = By.id("finish");

    	  // Clear any dialogs that may block interactions (native alert or in-page)
    	  WaitUtils.clearBlockingDialogs(driver);

    	  // ensure presence (retry once if missing)
    	  try {
    	    new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.presenceOfElementLocated(finishBtn));
    	  } catch (TimeoutException e) {
    	    // maybe an in-app modal popped up (change-password). try clearing and wait again
    	    WaitUtils.clearBlockingDialogs(driver);
    	    new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.presenceOfElementLocated(finishBtn));
    	  }

    	  WebElement btn = driver.findElement(finishBtn);
    	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
    	  new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(finishBtn));
    	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    	  WaitUtils.waitForVisibility(driver, By.className("complete-header"), 10);
    	}
    public boolean isComplete(){
        return driver.findElement(complete).getText().contains("THANK YOU");
    }
}
