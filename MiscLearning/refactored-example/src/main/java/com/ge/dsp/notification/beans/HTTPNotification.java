/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.notification.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.ge.dsp.notification.api.INotificationBean;


/**
 * HTTP implementation of Notification API
 * 
 * @author 212307967
 */
public class HTTPNotification
        implements INotificationBean
{

    // List of URL(s) to which the POST message needs to be sent
    private ArrayList<String>   urls;
    // The message body.
    private String              body;
    // The mime type of the HTTP content. Default is application/json.
    private String              mimeType = MediaType.APPLICATION_JSON;
    // The character set of the HTTP content. Default is ISO-8859-1.
    private String              charset;
    
    /**
     * @return the urls
     */
    public List<String> getUrls()
    {
        if (this.urls == null)
        {
            return new ArrayList<String>();
        }
        return this.urls;
    }
    /**
     * @param urls the list of URL(s) to which the POST message needs to be sent
     */
    public void setUrls(List<String> urls)
    {
        this.urls = new ArrayList<String>(urls);
    }
    
    /**
     * Add a URL to the list of URLs.
     * @param url the url to add.
     */
    public void addUrl(String url)
    {
        if (this.urls == null)
        {
            this.urls = new ArrayList<String>();
        }
        this.urls.add(url);
    }
    
    /**
     * Default is application/json.
     * @return the mimeType
     */
    public String getMimeType()
    {
        return this.mimeType;
    }
    /**
     * Default is application/json.
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
    }
    
    /**
     * Default is ISO-8859-1.
     * @return the charset
     */
    public String getCharset()
    {
        return this.charset;
    }
    /**
     * Default is ISO-8859-1.
     * @param charset the charset to set
     */
    public void setCharset(String charset)
    {
        this.charset = charset;
    }
    
    /**
     * @return the body
     */
    public String getBody()
    {
        return this.body;
    }
    /**
     * @param body the body to set
     */
    public void setBody(String body)
    {
        this.body = body;
    }
  

}
