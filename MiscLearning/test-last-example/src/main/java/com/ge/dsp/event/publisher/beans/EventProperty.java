/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.publisher.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Define the an API for mapping eventProperty XML elements to Java Objects.
 * The XML Document is defined by MessageEvent Schema
 * 
 * @author 212307967
 */

@XmlRootElement(name = "eventProperty")
public class EventProperty
        implements Serializable
{

    /**
     * Serial version ID
     */
    private static final long serialVersionUID = 1L;

    private String            name;

    private String            value;

    /**
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue()
    {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("nls")
    @Override
    public String toString()
    {
        return "EventProperty [name=" + this.name + ", value=" + this.value + "]";
    }

}
