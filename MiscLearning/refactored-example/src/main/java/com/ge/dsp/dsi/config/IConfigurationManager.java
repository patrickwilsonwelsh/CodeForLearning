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

import java.util.Map;

import javax.management.MXBean;

/**
 * This is an interface for managing configuration.
 */
@MXBean
public interface IConfigurationManager
{
    /**
     * Get all managed configurations, including DSP bundles as well as other bundles loaded by system.
     * 
     * @return returns managed configurations
     */
    public ManagedNodeConfiguration listAllManagedConfiguration();

    /**
     * Returns configuration identified by pid, or symbolic name
     * 
     * @param id pid of the service, or symbolic name for DSP bundles.
     * @return returns configurations by pid or symbolic name
     */
    public Map<String, String> retrieveModuleConfiguration(String id);

    /**
     * Update the configuration set identified by pid, or symbolic name
     * 
     * @param id pid of the service, or symbolic name for DSP bundles.
     * @param name name of property
     * @param value value of property
     * @throws ConfigurationException throws runtime exception for all the configuration related exceptions
     */
    public void updateConfiguration(String id, String name, String value)
            throws ConfigurationException;
}
