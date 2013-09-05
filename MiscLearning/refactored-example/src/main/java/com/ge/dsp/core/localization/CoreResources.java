/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.localization;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.ge.dsp.core.shared.AbstractResourceHelper;

/**
 * 
 * @author 212306156
 */
public class CoreResources extends AbstractResourceHelper
{

    private static final String  BUNDLE = "CoreResources"; //$NON-NLS-1$

    private static CoreResources _resource;               // singleton

    /**
     * Empty Constructor.
     */
    protected CoreResources()
    {
        try
        {
            super.resources = ResourceBundle.getBundle(BUNDLE);
            // NOTE: this throws a runtime "MissingResourceException".
        }
        catch (MissingResourceException ee)
        {
            // catch this and the error is reported in setBundle.
        }
        setBundle(super.resources, BUNDLE);
    }

    /**
     * Get an instance of the CoreResources object.
     * 
     * @return CoreResources singleton.
     */
    public static CoreResources getInstance()
    {
        if ( _resource == null )
        {
            _resource = new CoreResources();
        }
        return _resource;
    }

}
