package com.home.qa.stepdefinitions;

import com.home.qa.actions.admin.HomeActions;
import com.home.qa.base.TestBase;
import com.home.qa.util.TestUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class Home extends TestBase {

    HomeActions homeActions = new HomeActions();

    @Given("blabla")
    public void blabla() {
        System.out.println("blabla");
    }

    @Then("User can see {string}")
    public void userCanSeeText(String text) {
        System.out.println(text);
        TestUtil.wait(10000);
//        Assert.assertEquals("bla", text);

    }

    @Given("User is on homepage")
    public void userIsOnHomepage() {
        System.out.println("Home");
    }
}
