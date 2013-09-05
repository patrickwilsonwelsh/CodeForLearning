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

import java.util.List;

import javax.management.MXBean;

/**
 * This interface is useful for lookup service information from JMX tools such as JConsole. Combining it with IServiceManagerService
 * will cause problems with JMX.
 */
@MXBean
public interface IServiceQueryService
{
    /**
     * Get all registered services
     * 
     * @return list of ServiceInfo objects, empty if nothing is found
     * @throws CoreException if failed
     */
    public List<ServiceInfo> getRegisteredServices()
            throws CoreException;

    /**
     * Get all registered service factories. Service factory can be used to create more customized services and register them
     * accordingly.
     * 
     * @return list of ServiceInfo objects which can be used to create more services, empty if nothing is found
     * @throws CoreException if failed
     */
    public List<ServiceInfo> getRegisteredServiceFactories()
            throws CoreException;
}
