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

import com.ge.dsp.core.spi.CoreException;

/**
 * A universal configuration tool for all modules to deal with the configuration of their own. The configurations are
 * all segregated, the name space of module configuration is the symbolic name of each bundle. All configurations here
 * are only related to the bundle calling this. So configurations from other bundles are not available using this interface.
 * Configuration can also be targeted to each running instance of DSP server. This means that a configuration parameter,
 * say numberOfThread, are unique when combined with runtime DSP kernel + bundle symbolic name.
 * 
 * Please note that all the methods in this interface deals with configuration on the running instance. For massively affect
 * configurations on all running instances, admin tools shall be used.
 */
public interface IConfigurationService
{
    /**
     * Returns the bundle symbolic name for this configuration service.
     * 
     * @return bundle symbolic name.
     */
    public String getSymbolicName();

    /**
     * Save the configuration for this running instance.
     * 
     * @param configMap a map of configurations to save.
     * @throws com.ge.dsp.core.spi.CoreException if something happened
     */
    public void saveConfiguration(Map<String, String> configMap)
            throws CoreException;

    /**
     * Save the configuration for this running instance.
     * 
     * @param name name of the property
     * @param value value of the configuration
     * @throws com.ge.dsp.core.spi.CoreException if something happened
     */
    public void saveConfiguration(String name, String value)
            throws CoreException;

    /**
     * Save the configuration as a unit.
     * 
     * @param configBean configbean
     * @throws CoreException if something is wrong
     */
    public void saveConfiguration(ConfigBean configBean)
            throws CoreException;

    /**
     * Get configuration as a map for this running instance.
     * 
     * @return configuration map, key is property name and value is property value
     * @throws com.ge.dsp.core.spi.CoreException if something is wrong
     */
    public Map<String, String> getConfiguration()
            throws CoreException;

    /**
     * Get configuration as a map for this running instance.
     * 
     * @return configuration map, key is the property name, value is the ConfigBean which contains more information on
     *         the property, such as validator string, data type, whether it can be managed from console, etc.
     * @throws com.ge.dsp.core.spi.CoreException if something is wrong
     */
    public Map<String, ConfigBean> getConfigurationSpec()
            throws CoreException;

    /**
     * Delete a configuration for this running instance.
     * 
     * @param name name of the property
     * @throws com.ge.dsp.core.spi.CoreException if something is wrong
     */
    public void deleteConfiguration(String name)
            throws CoreException;
}
