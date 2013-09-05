/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.util.fakes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import clover.com.lowagie.tools.plugins.Split;

/**
 * Allows for "javasimon" monitoring of methods.
 */
public class MonitorUtil
{
    private static Logger logger = LoggerFactory.getLogger(MonitorUtil.class);
    private final static int    TIME   = 1000000;

    private String              functionName;
    private String              callerName;
    private StopWatch           stopwatch;
    private Split               split;

    /**
     * create an new monitor for this function.
     * 
     * @param functionName function to monitor.
     */
    public MonitorUtil(String functionName)
    {
        this(functionName, null);
    }

    /**
     * create an new monitor for this function.
     * 
     * @param functionName function to monitor.
     * @param callerName the name of the class or service that is calling this function.
     */
    public MonitorUtil(String functionName, String callerName)
    {
        this.functionName = functionName;
        this.callerName = callerName;
    }

    /**
     * @return the functionName
     */
    public String getFunctionName()
    {
        return this.functionName;
    }

    /**
     * @return the callerName
     */
    public String getCallerName()
    {
        return this.callerName;
    }

    /**
     * @param callerName the callerName to set
     */
    public void setCallerName(String callerName)
    {
        this.callerName = callerName;
    }

    /**
     * Start runtime statistics for a function
     */
    public void startMonitor()
    {
    }

    /**
     * Stop monitoring thread and collect runtime statistics for a function
     */
    public void stopMonitor()
    {
    }

	public static boolean monitorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void start(String mONITOR_GROUP_NAME, String classname,
			String string) {
		// TODO Auto-generated method stub
		
	}

	public static void stop(String mONITOR_GROUP_NAME, String classname,
			String string, Object object) {
		// TODO Auto-generated method stub
		
	}

	public static void fail(String mONITOR_GROUP_NAME, String classname,
			String string, Object object) {
		// TODO Auto-generated method stub
		
	}
}
