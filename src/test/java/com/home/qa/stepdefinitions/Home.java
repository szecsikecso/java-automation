package com.home.qa.stepdefinitions;

import com.home.qa.actions.admin.HomeActions;
import com.home.qa.base.TestBase;
import com.home.qa.util.TestUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Home extends TestBase {

    HomeActions homeActions = new HomeActions();

    @Given("blabla")
    public void blabla() {
        System.out.println("blabla");
    }

    @Then("User can see {string}")
    public void userCanSeeText(String text) {
        System.out.println(text);
        WebElement contentElement = driver.findElement(
                By.xpath("//main[@id='wp--skip-link--target']")
        );
        String contentText = wait.until(
                ExpectedConditions.visibilityOf(
                        contentElement
                )
        ).getText();
        System.out.println(contentText);
        TestUtil.wait(1000);
        //Assert.assertEquals(contentText, text);
        boolean returnValue = contentText.contains(text);
        Assert.assertTrue(returnValue);

    }

    @Given("User is on homepage")
    public void userIsOnHomepage() {
        System.out.println("Home");
    }
}
