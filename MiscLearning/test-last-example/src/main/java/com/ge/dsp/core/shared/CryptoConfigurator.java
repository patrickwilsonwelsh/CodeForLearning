/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.core.shared;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ge.dsp.core.localization.CoreResources;

/**
 * This class configures the static MangleUtil and HashUtil properties.
 * It is used by CryptoPropertyPlaceholderConfigurer to configure the static MangleUtil and HashUtil properties.
 * CryptoPropertyPlaceholderConfigurer needs to do this because it is instantiated before configuration manager has a chance to configure the static utilities.
 * 
 * @author 212314537
 */
public class CryptoConfigurator
{
    private static Logger _logger = LoggerFactory.getLogger(CryptoConfigurator.class);

    /**
     * The property key that specifies the location of the crypto configuration property file in the core configuration prooperty file.
     */
    public  static String CRYPTO_CONF_KEY = "dsp.crypto.conf"; //$NON-NLS-1$

    /**
     * The default crypto configuration property file location.
     */
    public  static String DEFAULT_CRYPTO_CONF_VALUE = "restricted/dsp.crypto.conf"; //$NON-NLS-1$

    /**
     * Location of key store that contains encryption keys
     */
    public static final String DSP_CRYPTO_KEYSTORE_FILENAME = "dsp.crypto.keystore.filename";  //$NON-NLS-1$

    /**
     * Password for key store that contains encryption keys
     */
    public static final String DSP_CRYPTO_KEYSTORE_PASSWORD = "dsp.crypto.keystore.password";  //$NON-NLS-1$

    /**
     * Alias of encryption key.
     */
    public static final String DSP_CRYPTO_KEY_ALIAS         = "dsp.crypto.key.alias";        //$NON-NLS-1$

    /**
     * Password that protects encryption key.
     */
    public static final String DSP_CRYPTO_KEY_PASSWORD      = "dsp.crypto.key.password";     //$NON-NLS-1$

    /**
     * Sets the configuration of HashUtil and MangleUtil from a collection of properties.
     * 
     * @param properties java properties
     */
    public void setConfiguration(Properties properties)
    {
        if ( properties.containsKey(DSP_CRYPTO_KEYSTORE_FILENAME) )
        {
            CryptoParams.setKeyStoreFileName(properties.getProperty(DSP_CRYPTO_KEYSTORE_FILENAME));
        }

        if ( properties.containsKey(DSP_CRYPTO_KEYSTORE_PASSWORD) )
        {
            CryptoParams.setKeyStorePassword(properties.getProperty(DSP_CRYPTO_KEYSTORE_PASSWORD));
        }

        if ( properties.containsKey(DSP_CRYPTO_KEY_ALIAS) )
        {
            CryptoParams.setKeyAlias(properties.getProperty(DSP_CRYPTO_KEY_ALIAS));
        }

        if ( properties.containsKey(DSP_CRYPTO_KEY_PASSWORD) )
        {
            CryptoParams.setKeyPassword(properties.getProperty(DSP_CRYPTO_KEY_PASSWORD));
        }
    }

    /**
     * Loads crypto configuration from the specified file.
     * 
     * @param filePath path to crypto configuration properties file
     * @throws IOException if the properties file cannot be found or opened
     */
    public void configureFromFile(String filePath)
            throws IOException
    {
        Properties properties = null;

        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fileInputStream);
        }
        finally
        {
            if ( null != fileInputStream )
            {
                try
                {
                    fileInputStream.close();
                }
                catch (IOException ex)
                {
                    _logger.warn(CoreResources.getInstance().getString("EX_CRYPTO_CLOSE_CONFIG_FILE", filePath)); //$NON-NLS-1$
                }
            }
        }

        setConfiguration(properties);
    }
}
