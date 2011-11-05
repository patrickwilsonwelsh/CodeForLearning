package com.gfs.spike.pages;

import com.gfs.test.selenium.SeleniumRule;
import com.gfs.test.selenium.ui.Application;
import com.gfs.test.selenium.ui.Button;
import com.gfs.test.selenium.ui.Page;
import com.gfs.test.selenium.ui.TextField;

@SuppressWarnings("all")
public class GoogleImages extends Page {

    public final TextField query;
    public Button<GoogleImageResults> search;

    public GoogleImages(Google app) {
        super(app, "gbar");
        
        query = new TextField(app, "q");
        search = app.newButton("btnG", GoogleImageResults.class);
    }
}
