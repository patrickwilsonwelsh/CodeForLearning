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

import java.util.Map;

import com.ge.dsp.core.spi.CoreException;
import com.ge.dsp.core.spi.IServiceManagerService;

/**
 * 
 * Utility class for getting serviceManagerService and registering service.
 * 
 * @author zhencai
 * 
 */
public class CoreUtil
{

    /**
     * @return service manager service
     */
    public static IServiceManagerService getServiceManagerService()
    {

        return null;
    }

    /**
     * @param serviceObject service object
     * @param serviceAttributes service attributes
     * @throws CoreException kernel exception
     */
    public static void registerService(Object serviceObject, Map<String, String> serviceAttributes)
            throws CoreException
    {
        getServiceManagerService().registerService(serviceObject, serviceAttributes);
    }

}
