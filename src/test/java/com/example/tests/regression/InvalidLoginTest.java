package com.example.tests.regression;

import com.example.annotations.MindMap;
import com.example.pages.LoginPage;
import com.example.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends TestBase {

  @MindMap(node="Login -> Invalid", priority="P1", ci="Nightly")
  @Test(groups = {"regression","P1"})
  public void invalidLoginShowsError(){
    LoginPage login = new LoginPage(driver);
    login.open();
    login.login("bad_user","bad_pass");
    String err = login.getErrorText();
    Assert.assertTrue(err.contains("do not match any user"));
  }
}
