package com.gfs.spike.pages;

import com.gfs.test.selenium.ui.Application;
import com.thoughtworks.selenium.Selenium;

public class Google extends Application {
    
    public Google(Selenium selenium) {
        super(selenium);
    }
    
    public GoogleHome goHome() {
        return open("/", GoogleHome.class);
    }
}
