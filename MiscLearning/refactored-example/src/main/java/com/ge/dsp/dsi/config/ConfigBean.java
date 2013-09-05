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

/**
 * A bean wrapping all the information from a property file.
 */
public class ConfigBean
        implements Serializable
{
    private static final long serialVersionUID          = -1690500489357629195L;

    /**
     * Suffix key for properties that specify type metadata for another property.
     */
    public static String      TYPE_Key                  = ".type";              //$NON-NLS-1$

    /**
     *
     */
    public static String      VALIDATOR_KEY             = ".validatorString";   //$NON-NLS-1$

    /**
     *
     */
    public static String      MANAGED_KEY               = ".isManaged";         //$NON-NLS-1$

    /**
     * A property type that identifies the related property value as data that needs to be encrypted.
     * This is analogous to the "encrypted" type and exists for backward compatibility.
     */
    public static String      TYPE_PASSWORD             = "password";           //$NON-NLS-1$

    /**
     * A property type that identifies the related property value as data that needs to be encrypted.
     */
    public static String      TYPE_ENCRYPTED            = "encrypted";          //$NON-NLS-1$

    /**
     * A property type that identifies the related property value as data that needs to be salted and hashed.
     */
    public static String      TYPE_HASHED               = "hashed";             //$NON-NLS-1$

    /**
     * key for encrypted data.
     */
    public static String      ENCRYPTED_KEY             = ".encrypted";         //$NON-NLS-1$

    /**
     * key for hashed data.
     */
    public static String      HASHED_KEY                = ".hashed";            //$NON-NLS-1$

    private String            name;
    private String            value                     = "";                   //$NON-NLS-1$

    // property data type, for UI, valid types are java.lang.String,
    // java.lang.Integer, java.lang.Float, java.lang.Boolean.
    private String            type                      = "java.lang.String";   //$NON-NLS-1$

    // the validator string for UI
    private String            validatorStr              = "";                   //$NON-NLS-1$

    // if true then it can be managed by the UI, otherwise it is not.
    private boolean           isManaged                 = true;

    private String            encryptedPassword         = "";                   //$NON-NLS-1$

    private boolean           isStale                   = false;

    /**
     * String command which needs to be followed once any property is changed.
     * Used if set
     */
    public String             attributes                = "";                   //$NON-NLS-1$
    /**
     * Key for the attribute property.
     */
    public static String      ATTRIBUTES_KEY            = ".attributes";        //$NON-NLS-1$

    /**
     * Attribute set to dsp_restart if the container needs to be restarted
     */
    public static String      ATTRIBUTE_DSP_RESTART     = "dsp_restart";        //$NON-NLS-1$

    /**
     * Attributes set to service_restart if the service needs to be restarted from inside the container.
     */
    public static String      ATTRIBUTE_SERVICE_RESTART = "service_restart";    //$NON-NLS-1$

    /**
     * @return property name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @param name
     *            property name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return property value
     */
    public String getValue()
    {
        return this.value;
    }

    /**
     * @param value
     *            property value
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * @return property type
     */
    public String getType()
    {
        return this.type;
    }

    /**
     * @param type
     *            property type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return property validator
     */
    public String getValidatorStr()
    {
        return this.validatorStr;
    }

    /**
     * @param validatorStr
     *            property validator
     */
    public void setValidatorStr(String validatorStr)
    {
        this.validatorStr = validatorStr;
    }

    /**
     * @return true if managed
     */
    public boolean isManaged()
    {
        return this.isManaged;
    }

    /**
     * @param managed
     *            true if managed
     */
    public void setManaged(boolean managed)
    {
        this.isManaged = managed;
    }

    /**
     * @return the encryptedPassword
     */
    public String getEncryptedPassword()
    {
        return this.encryptedPassword;
    }

    /**
     * @param encryptedPassword
     *            the encryptedPassword to set
     */
    public void setEncryptedPassword(String encryptedPassword)
    {
        this.encryptedPassword = encryptedPassword;
    }

    /**
     * @return isStale
     */
    public boolean isStale()
    {
        return this.isStale;
    }

    /**
     * @param isStale
     *            true if the ConfigBean needs to be written to the config file
     */
    public void setStale(boolean isStale)
    {
        this.isStale = isStale;
    }

    /**
     * @param attributes
     *            set the attribute property
     */
    public void setAttributes(String attributes)
    {
        this.attributes = attributes;
    }

    /**
     * @return reads the string set as attributes
     */
    public String getAttributes()
    {
        return this.attributes;
    }

    @Override
    public String toString()
    {
        return "ConfigBean{" + //$NON-NLS-1$
                "name=" + this.name + //$NON-NLS-1$
                ", value=" + this.value + //$NON-NLS-1$
                ", type=" + this.type + //$NON-NLS-1$
                ", validatorString=" + this.validatorStr + //$NON-NLS-1$
                ", isManaged=" + this.isManaged + //$NON-NLS-1$
                ", isStale = " + this.isStale + //$NON-NLS-1$
                ", encryptedPassword = " + this.encryptedPassword + //$NON-NLS-1$
                "}"; //$NON-NLS-1$
    }
}
