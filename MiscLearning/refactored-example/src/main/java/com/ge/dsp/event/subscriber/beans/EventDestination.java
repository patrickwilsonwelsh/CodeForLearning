/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Notification destination class
 * 
 * @author 212304931
 * @since 1.5
 */
@XmlRootElement
public class EventDestination
        implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * Defined the list of supported Notification types.
     * Note: Email and HTTP are the only supported types at this point.
     */
    public enum NotificationType
    {
        /**
         * Email notification type
         */
        EMAIL,

        /**
         * HTTP post notification type
         */
        HTTP
    }

    private NotificationType type;

    private List<String>     value;

    /**
     * @return the type
     */
    public NotificationType getType()
    {
        return this.type;
    }

    /**
     * @param type the type to set
     */
    public void setType(NotificationType type)
    {
        this.type = type;
    }

    /**
     * the value is list of URIs.
     * Currently these URIs are either list of email addresses or URLs
     * 
     * @return the value
     */
    public List<String> getValue()
    {
        return this.value;
    }

    /**
     * the value is list of URIs.
     * Currently these URIs are either list of email addresses or URLs
     * 
     * @param value the value to set
     */
    public void setValue(List<String> value)
    {
        this.value = value;
    }
    
    /**
     * add a single value to a new or existing list
     * @param value the URI
     */
    public void addValue( String value)
    {
        if (this.value == null)
        {
            this.value = new ArrayList<String>();
        }
        
        this.value.add(value);
    }

}
