package com.home.qa.pages;

import com.home.qa.base.TestBase;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    public HomePage(){
        PageFactory.initElements(driver, this);
    }
}
