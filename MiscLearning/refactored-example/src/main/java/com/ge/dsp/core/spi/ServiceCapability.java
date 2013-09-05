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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An abstract class that defines the capability of a service. This describes what a service is capable of.
 */
public abstract class ServiceCapability
{

    private String serviceVersion;

    /**
     * get version of the service.
     * 
     * @return serviceVersion
     */
    public String getServiceVersion()
    {
        return this.serviceVersion;
    }

    private Map<String, String> capabilityMap = new ConcurrentHashMap<String, String>();

    /**
     * Returns service capability map, describing what the service is capable of
     * 
     * @return capability map of <String, String>
     */
    public Map<String, String> getSupportedServiceCapabilityMap()
    {
        return this.capabilityMap;
    }

    /**
     * Add service capability map that is required for the service returned
     * 
     * @param capMap capability map of <String, String>
     */
    public void addRequiredServiceCapability(Map<String, String> capMap)
    {
        this.capabilityMap.putAll(capMap);
    }
}
