/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.dsi.config;

import com.ge.dsp.core.spi.CoreException;

/**
 * A factory to get configuration service.
 */
public interface IConfigurationServiceFactory
{
    /**
     * Get IConfigurationService.
     * 
     * @return IConfigurationService
     * @throws com.ge.dsp.core.spi.CoreException if something is wrong
     */
    public IConfigurationService getConfigurationService()
            throws CoreException;

    /**
     * Register a listener so notifications can be sent to the object that implements IConfigurationAware.
     * 
     * @param configurationAware the object that implements IConfigurationAware.
     * @throws com.ge.dsp.core.spi.CoreException if something is wrong
     */
    public void registerConfigurationListener(IConfigurationAware configurationAware)
            throws CoreException;
}
