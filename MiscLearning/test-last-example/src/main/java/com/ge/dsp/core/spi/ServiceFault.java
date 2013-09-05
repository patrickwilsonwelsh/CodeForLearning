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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.ws.WebFault;

/**
 * 
 * 
 * @author 212322802
 */
@XmlRootElement(name = "servicefault")
@XmlAccessorType(XmlAccessType.FIELD)
@WebFault
public class ServiceFault extends Exception
{

    private String            errorcode;
    private String            errormessage;
    private String            errorType;
    private static final long serialVersionUID = 1L;

    /**
     * default constructor
     */
    public ServiceFault()
    {

    }

    /**
     * constructor takes exception as input
     * 
     * @param e Exception
     */
    public ServiceFault(Exception e)
    {
        this.setErrormessage(e.getMessage());

    }

    /**
     * 
     * @param e Exception
     * @param code error code
     */
    public ServiceFault(Exception e, String code)
    {
        this.setErrormessage(e.getMessage());
        this.setErrorcode(code);
    }

    /**
     * 
     * @param code error code
     * @param message error message
     */
    public ServiceFault(String code, String message)
    {
        this.setErrormessage(message);
        this.setErrorcode(code);
    }

    /**
     * 
     * @param code error code
     * @param message error message
     * @param errorType exception type
     */
    public ServiceFault(String code, String message, String errorType)
    {
        this.setErrormessage(message);
        this.setErrorcode(code);
        this.setErrorType(errorType);
    }

    /**
     * get error code
     * 
     * @return String
     */
    public String getErrorcode()
    {
        return this.errorcode;
    }

    /**
     * set error code
     * 
     * @param errorcode String
     */
    public void setErrorcode(String errorcode)
    {
        this.errorcode = errorcode;
    }

    /**
     * get error message
     * 
     * @return String
     */
    public String getErrormessage()
    {
        return this.errormessage;
    }

    /**
     * set error message
     * 
     * @param errormessage String
     */
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    /**
     * get error type
     * 
     * @return String
     */
    public String getErrorType()
    {
        return this.errorType;
    }

    /**
     * set error type
     * 
     * @param errorType String
     */
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }
}
