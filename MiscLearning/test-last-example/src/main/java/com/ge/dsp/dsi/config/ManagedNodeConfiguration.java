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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple Java bean that contains all the configuration information for services using DSP's config service.
 */
public class ManagedNodeConfiguration
        implements Serializable
{
    private static final long                    serialVersionUID = -4167127967971716088L;

    // a map with key as bundle symbolic name, values as key value pairs in the bundle configuration file.
    private Map<String, Map<String, ConfigBean>> nodeConfigMap;
    private String                               kernelUuid;

    /**
     * Constructor.
     * 
     * @param kernelUuid unique UUID for the running kernel
     */
    public ManagedNodeConfiguration(String kernelUuid)
    {
        this.kernelUuid = kernelUuid;
        this.nodeConfigMap = new HashMap<String, Map<String, ConfigBean>>();
    }

    /**
     * Get a map with key as bundle symbolic name, values as key value pairs in the bundle configuration file.
     * For other non-dsp bundles the key is the pid.
     * 
     * @return Map<String, Map<String, ConfigBean>>
     */
    public Map<String, Map<String, ConfigBean>> getNodeConfigMap()
    {
        return this.nodeConfigMap;
    }

    /**
     * Get the UUID for this node
     * 
     * @return UUID
     */
    public String getKernelUuid()
    {
        return this.kernelUuid;
    }

    /**
     * Add bundle configuration to the node configuration
     * 
     * @param symbolicName symbolic name of the bundle
     * @param bundleConfigMap config map for this bundle
     */
    public void addBundleConfiguration(String symbolicName, Map<String, ConfigBean> bundleConfigMap)
    {
        this.nodeConfigMap.put(symbolicName, bundleConfigMap);
    }

    @Override
    public String toString()
    {
        return "ManagedNodeConfiguration{" + "nodeConfigMap=" + this.nodeConfigMap + ", kernelUuid=" + this.kernelUuid //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + '}';
    }
}
