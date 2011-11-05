package com.gfs.spike;

import org.junit.Rule;
import org.junit.Test;

import com.gfs.spike.pages.Google;
import com.gfs.spike.pages.GoogleHome;
import com.gfs.spike.pages.GoogleImageResults;
import com.gfs.spike.pages.GoogleImages;
import com.gfs.test.selenium.SeleniumConfig;
import com.gfs.test.selenium.SeleniumRule;

@SuppressWarnings("all")
@SeleniumConfig(url = "http://www.google.com", browsers = "*firefox")
public class GoogleTest {

    @Rule
    public SeleniumRule selenium = new SeleniumRule();
    
    Google app = new Google(selenium);

    @Test
    public void testHome() {
        GoogleHome home = app.goHome();
        GoogleImages images = home.images.click();
        images.query.type("puppies");
        GoogleImageResults results = images.search.click();
    }
}
