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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Information about services we have in DSP.
 */
public class ServiceInfo
        implements Serializable
{
    private static final long   serialVersionUID          = 2591402321339163832L;

    /**
     * Unique id for the service
     */
    private long                serviceId                 = -1;

    /**
     * Interfaces this service implements
     */
    private String[]            serviceInterfaces;

    /**
     * Service object, which we can use to interact with the service
     */
    transient private Object    serviceObject;

    /**
     * Attribute map of the service
     */
    private Map<String, String> serviceAttributes;

    /**
     * Is this service a factory for services or not
     */
    private boolean             isServiceFactory;

    /**
     * Interface a service factory supports. This will be "" if the service is not a factory.
     */
    private String              supportedServiceInterface = "";                  //$NON-NLS-1$

    /**
     * Is this service remote or local
     */
    private boolean             isLocalService;

    /**
     * Supported service configuration that can be used to create service based on desired configurations
     */
    private List<String>        supportedServiceAttributes;

    /**
     * Supported service capabilities that can be used to create services that match the capabilities desired
     */
    private Map<String, String> supportedServiceCapabilities;

    /**
     * @return serviceObject
     */
    public Object retrieveServiceObject()
    {
        return this.serviceObject;
    }

    /**
     * @param serviceObject - sets serviceObject
     */
    public void setServiceObject(Object serviceObject)
    {
        this.serviceObject = serviceObject;
    }

    /**
     * @return serviceAttributes
     */
    public Map<String, String> getServiceAttributes()
    {
        return this.serviceAttributes;
    }

    /**
     * @param serviceAttributes - sets serviceAttributes
     */
    public void setServiceAttributes(Map<String, String> serviceAttributes)
    {
        this.serviceAttributes = serviceAttributes;
    }

    /**
     * @return isServiceFactory - true or false
     */
    public boolean isServiceFactory()
    {
        return this.isServiceFactory;
    }

    /**
     * @param serviceFactory - sets isServiceFactory
     */
    public void setServiceFactory(boolean serviceFactory)
    {
        this.isServiceFactory = serviceFactory;
    }

    /**
     * @return supportedServiceAttributes
     */
    public List<String> getSupportedServiceAttributes()
    {
        return this.supportedServiceAttributes;
    }

    /**
     * @param supportedServiceAttributes - sets supportedServiceAttributes
     */
    public void setSupportedServiceAttributes(List<String> supportedServiceAttributes)
    {
        this.supportedServiceAttributes = supportedServiceAttributes;
    }

    /**
     * @return supportedServiceCapabilities
     */
    public Map<String, String> getSupportedServiceCapabilities()
    {
        return this.supportedServiceCapabilities;
    }

    /**
     * @param supportedServiceCapabilities - sets supportedServiceCapabilities
     */
    public void setSupportedServiceCapabilities(Map<String, String> supportedServiceCapabilities)
    {
        this.supportedServiceCapabilities = supportedServiceCapabilities;
    }

    /**
     * @return isLocalService
     */
    public boolean isLocalService()
    {
        return this.isLocalService;
    }

    /**
     * @param localService - sets isLocalService
     */
    public void setLocalService(boolean localService)
    {
        this.isLocalService = localService;
    }

    /**
     * @return serviceInterfaces
     */
    public String[] getServiceInterfaces()
    {
        return this.serviceInterfaces;
    }

    /**
     * @param serviceInterfaces - sets serviceInterfaces
     */
    public void setServiceInterfaces(String[] serviceInterfaces)
    {
        if ( serviceInterfaces != null )
        {
            this.serviceInterfaces = new String[serviceInterfaces.length];
            System.arraycopy(serviceInterfaces, 0, this.serviceInterfaces, 0, serviceInterfaces.length);
        }
        else
        {
            this.serviceInterfaces = serviceInterfaces;
        }
    }

    /**
     * @return serviceId
     */
    public long getServiceId()
    {
        return this.serviceId;
    }

    /**
     * @param serviceId - sets serviceId
     */
    public void setServiceId(long serviceId)
    {
        this.serviceId = serviceId;
    }

    /**
     * @return supportedServiceInterface
     */
    public String getSupportedServiceInterface()
    {
        return this.supportedServiceInterface;
    }

    /**
     * @param supportedServiceInterface - sets supportedServiceInterface
     */
    public void setSupportedServiceInterface(String supportedServiceInterface)
    {
        this.supportedServiceInterface = supportedServiceInterface;
    }
}
