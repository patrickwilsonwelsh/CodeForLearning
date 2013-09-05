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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class defines an abstract class that all service factories should take, and use to create service consumer specific
 * services. For example, LogService should implement this class to tailor the configuration to its own purpose.
 */
public class ServiceConfiguration
{

    private Map<String, String> requiredServiceAttributes  = new ConcurrentHashMap<String, String>();

    private List<String>        supportedServiceAttributes = new ArrayList<String>();

    /**
     * Provides a list of supported service attributes. This list should include all attributes that the service factory supports.
     * Each service factory supports different attributes, for example, log service will have log file location, log level, while
     * data source service will have configuration such as db name, driver location, etc.
     * 
     * @return supportedServiceAttributes
     */
    public List<String> getSupportedServiceAttributes()
    {
        return this.supportedServiceAttributes;
    }

    /**
     * Set the supported service attributes
     * 
     * @param supportedServiceAttributes - sets List of supportedServiceAttributes
     */
    public void setSupportedServiceAttributes(List<String> supportedServiceAttributes)
    {
        this.supportedServiceAttributes = supportedServiceAttributes;
    }

    /**
     * This method is used by the service consumer, that want to specifically order a tailored service. This method can be
     * used repeatedly to add service attributes wanted.
     * 
     * @param name name of the service attribute, case sensitive, should be in the supported service attribute list.
     * @param value value of the service attribute.
     */
    public void addRequiredServiceAttribute(String name, String value)
    {
        this.requiredServiceAttributes.put(name, value);
    }

    /**
     * This method is used by the service consumer, that want to specifically order a tailored service. This method can be
     * used to add service attributes wanted.
     * 
     * @param attributesMap map containing service name and their values. This map is added to the existing service attribute map.
     */
    public void addRequiredServiceAttributes(Map<String, String> attributesMap)
    {
        this.requiredServiceAttributes.putAll(attributesMap);
    }

    /**
     * Used by the service factory, this method can be used to decide how to create the service that is requested by service requestors.
     * 
     * @return a map containing requested service attribute name and value.
     */
    public Map<String, String> getRequiredServiceAttributes()
    {
        return this.requiredServiceAttributes;
    }
}
