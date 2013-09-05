/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ge.dsp.core.localization.CoreResources;

/**
 * A utility class for logging related tasks.
 */
public class LoggingUtil
{
    private static String LOCAL_HOST_NAME = "NO_HOST_NAME";                            //$NON-NLS-1$
    private static Logger logger          = LoggerFactory.getLogger(LoggingUtil.class);

    static
    {
        try
        {
            InetAddress localIp = InetAddress.getLocalHost();
            LOCAL_HOST_NAME = localIp.getHostName();
        }
        catch (UnknownHostException e)
        {
            logger.error(CoreResources.getInstance().getString("FAILED_TO_GET_IP_ADD"), e); //$NON-NLS-1$
        }
    }

    /**
     * @return local host name
     */
    public static String getLocalHostName()
    {
        return LOCAL_HOST_NAME;
    }
}
