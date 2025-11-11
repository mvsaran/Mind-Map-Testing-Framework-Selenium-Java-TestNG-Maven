package com.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class WaitUtils {
  // existing methods...

  public static WebElement waitForVisibility(WebDriver driver, By locator, int sec) {
    return new WebDriverWait(driver, Duration.ofSeconds(sec))
      .until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public static WebElement waitForClickable(WebDriver driver, By locator, int sec) {
    return new WebDriverWait(driver, Duration.ofSeconds(sec))
      .until(ExpectedConditions.elementToBeClickable(locator));
  }

  public static boolean waitForInvisibility(WebDriver driver, By locator, int sec) {
    return new WebDriverWait(driver, Duration.ofSeconds(sec))
      .until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  // NEW: safe dismiss of native JS alert if present
  public static boolean dismissIfAlertPresent(WebDriver driver, int sec) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
      wait.until(ExpectedConditions.alertIsPresent());
      Alert alert = driver.switchTo().alert();
      alert.dismiss(); // or alert.accept() depending on dialog semantics
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // NEW: attempt to close common in-page modal using a set of selectors
  // Returns true if any close action was performed
  public static boolean closeKnownInPageModals(WebDriver driver) {
    // add selectors your app uses for such modals (IDs/classes). Update as needed.
    By[] possibleCloseButtons = new By[]{
      By.cssSelector(".modal-close"),
      By.cssSelector(".close"),                  // generic
      By.cssSelector(".cookie-consent__close"),
      By.id("change-password-close"),            // example for change-password popup
      By.cssSelector(".popup-close"),
      By.cssSelector("#closeModal")
    };

    for (By sel : possibleCloseButtons) {
      try {
        if (!driver.findElements(sel).isEmpty()) {
          WebElement btn = driver.findElement(sel);
          if (btn.isDisplayed() && btn.isEnabled()) {
            btn.click();
            // small wait to allow overlay to disappear
            try { Thread.sleep(250); } catch (InterruptedException ignored) {}
            return true;
          }
        }
      } catch (StaleElementReferenceException | ElementClickInterceptedException ignored) { /* try next */ }
    }
    return false;
  }

  // Use both strategies together
  public static void clearBlockingDialogs(WebDriver driver) {
    // 1) Dismiss native alert quickly
    dismissIfAlertPresent(driver, 1);
    // 2) Try closing in-page modals multiple times (safety)
    for (int i=0; i<3; i++) {
      boolean closed = closeKnownInPageModals(driver);
      if (!closed) break;
    }
  }
}
