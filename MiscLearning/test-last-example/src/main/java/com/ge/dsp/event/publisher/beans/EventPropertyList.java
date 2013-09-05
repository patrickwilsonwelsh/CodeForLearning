/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.publisher.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Define the an API for mapping eventProperties XML elements to Java Objects.
 * The XML Document is defined by MessageEvent Schema
 * 
 * @author 212307967
 * 
 */
@XmlRootElement(name = "eventProperties")
public class EventPropertyList
        implements Serializable
{

    /**
     * the serial version id
     */
    private static final long   serialVersionUID = 1L;

    private List<EventProperty> eventProperties;

    /**
     * @return the eventProperties
     */
    @XmlElement(name = "eventProperty")
    public List<EventProperty> getEventPropertyList()
    {
        return this.eventProperties;
    }

    /**
     * @param eventProperties the eventProperties to set
     */
    public void setEventPropertyList(List<EventProperty> eventProperties)
    {
        this.eventProperties = eventProperties;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("nls")
    @Override
    public String toString()
    {
        return "EventPropertyList [eventPropertyList=" + this.eventProperties + "]";
    }

}
