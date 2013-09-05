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

/**
 * 
 * @author 204055423
 */
public final class CryptoParams
{
    private static String keyStoreFileName;
    private static String keyStorePassword;
    private static String keyAlias;
    private static String keyPassword;
    
    /**
     * @param fileName - The name of the key store file.
     */
    public static void setKeyStoreFileName(String fileName)
    {
        CryptoParams.keyStoreFileName = fileName;
    }
    
    /**
     * @param password - The key store password.
     */
    public static void setKeyStorePassword(String password)
    {
        CryptoParams.keyStorePassword = password;
    }

    /**
     * @param alias - The key alias.
     */
    public static void setKeyAlias(String alias)
    {
        CryptoParams.keyAlias = alias;
    }

    /**
     * @param password - The key password.
     */
    public static void setKeyPassword(String password)
    {
        CryptoParams.keyPassword = password;
    }
    
    /**
     * @return - The key store file name.
     */
    protected static String getKeyStoreFileName()
    {
        return CryptoParams.keyStoreFileName;
    }
    
    /**
     * @return - The key store file password.
     */
    protected static String getKeyStorePassword()
    {
        return CryptoParams.keyStorePassword;
    }
    
    /**
     * @return - The key alias.
     */
    protected static String getKeyAlias()
    {
        return CryptoParams.keyAlias;
    }
    
    /**
     * @return - The key password.
     */
    protected static String getKeyPassword()
    {
        return CryptoParams.keyPassword;
    }
    
}
