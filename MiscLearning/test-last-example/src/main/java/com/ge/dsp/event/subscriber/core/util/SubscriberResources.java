/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.core.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.ge.dsp.commons.localization.AbstractResourceHelper;

/**
 * Publisher Resources are handles by this class
 * @author 212304931
 *
 */
public class SubscriberResources extends AbstractResourceHelper
{

    private static final String BUNDLE = "SubscriberRescources"; //$NON-NLS-1$

    private static SubscriberResources  _resource;    // singleton

    /**
     * Empty Constructor.
     */
    protected SubscriberResources()
    {
        try
        {
            super.resources = ResourceBundle.getBundle(BUNDLE);
            // NOTE: this throws a runtime "MissingResourceException".
        }
        catch (MissingResourceException ee)
        {
            throw ee;
        }
        setBundle(super.resources, BUNDLE);
    }

    /**
     * Get an instance of the LoggingResources object.
     * @return LoggingResources singleton.
     */
    public static SubscriberResources getInstance()
    {
        if (_resource == null)
        {
            _resource = new SubscriberResources();
        }
        return _resource;
    }
}