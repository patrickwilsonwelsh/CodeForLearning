package com.gfs.spike.pages;
import com.gfs.test.selenium.ui.Link;
import com.gfs.test.selenium.ui.Page;

public class GoogleHome extends Page {
    
    public final Link<GoogleImages> images;
    
    public GoogleHome(Google app) {
        super(app, "gbar");
        images = app.newLink("jquery=#gbar .gb1:eq(1)", GoogleImages.class);
    }

}
