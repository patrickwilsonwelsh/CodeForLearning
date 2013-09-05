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

/**
 * Interface that defines the contract that all service factories have to follow. Once the implementation is published
 * as service, other service consumers can use this to create custom made services, or request services that has the capability
 * matching what is desired.
 * 
 * @param <T> generic service
 */
public interface ICoreServiceFactory<T>
{
    /**
     * Get a service by the specified service configuration.
     * 
     * @param serviceConfiguration service configuration, cannot be null.
     * @return A service that matches the required service configuration.
     * @throws CoreException if failed
     */
    public T getService(ServiceConfiguration serviceConfiguration)
            throws CoreException;

    /**
     * Get service configuration supported by this service factory.
     * 
     * @return service configuration.
     */
    public ServiceConfiguration getSupportedServiceConfiguration();

    /**
     * Get service capabilities supported by this service factory.
     * 
     * @return service capability
     */
    public ServiceCapability getSupportedServiceCapability();

    /**
     * Get service specified by the service capability object. DPS kernel will honor this contract, and return a service
     * that has the capability requested.
     * 
     * @param serviceCapability service capability requested, cannot be null
     * @return service that has the capability matching the requested capability
     * @throws CoreException if failed
     */
    public T getService(ServiceCapability serviceCapability)
            throws CoreException;

    /**
     * Get default service supported by this service factory.
     * 
     * @return default service
     * @throws CoreException if failed
     */
    public T getDefaultService()
            throws CoreException;
}
