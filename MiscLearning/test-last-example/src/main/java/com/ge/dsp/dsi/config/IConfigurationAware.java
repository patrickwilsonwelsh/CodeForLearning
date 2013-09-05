/**
 * C O P Y R I G H T    N O T I C E
 *  (c) Copyright 2012 General Electric Company
 *
 *  All Rights Reserved. No portions of this source code or the resulting compiled
 *  program may be used without express written consent and licensing by General Electric Company.
 */
package com.ge.dsp.dsi.config;

import java.util.Map;

/**
 * An interface that defines what needs to be implemented so when configuration
 * changes, the implementation of this will be called.
 */
public interface IConfigurationAware {
	/**
	 * Called back when the configuration of this bundle is changed. This
	 * implementation has to be done in a separate thread.
	 * 
	 * @param configMap
	 *            configuration map
	 * @throws ConfigurationException
	 *             when configuration is not correct, or failed to change it
	 */
	public void configUpdated(Map<String, String> configMap)
			throws ConfigurationException;

	/**
	 * @param configMap
	 *            configuration map
	 * @throws ConfigurationException
	 *             when configuration is not correct, or failed to change it
	 */
	public void validateConfig(Map<String, String> configMap)
			throws ConfigurationException;
}
