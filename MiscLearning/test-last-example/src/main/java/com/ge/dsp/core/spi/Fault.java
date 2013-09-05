/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.spi;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 
 * @author 212322802
 */
@XmlRootElement
public class Fault
{

    private String code;
    private String message;

    /**
     * get method for error code
     * 
     * @return String
     */
    public String getCode()
    {
        return this.code;
    }

    /**
     * set method for error code
     * 
     * @param code error code
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * get method for error message
     * 
     * @return String
     */
    public String getMessage()
    {
        return this.message;
    }

    /**
     * set method for error message
     * 
     * @param message String
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

}
