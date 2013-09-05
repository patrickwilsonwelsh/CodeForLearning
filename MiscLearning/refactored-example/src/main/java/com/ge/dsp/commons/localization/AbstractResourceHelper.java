/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.commons.localization;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Give easy access to a resource bundle. This abstract class is designed
 * to help simple subclasses to provide basic localization with some bundle helper
 * methods.
 *
 * This class should be subclassed like:
 * <pre><Code>
public class LoggingResources extends AbstractResourceHelper
{
    private static final String BUNDLE = "LoggingResources";

    private static LoggingResources  _resource;    // singleton

    protected LoggingResources()
    {
        try
        {
            super.resources = ResourceBundle.getBundle(BUNDLE);
            // NOTE: this throws a runtime "MissingResourceException".
        }
        catch (MissingResourceException ee)
        {
            // catch this and the error is reported in setBundle if the bundle is null.
        }
        setBundle(super.resources, BUNDLE);
    }

    public static LoggingResources getInstance()
    {
        if (_resource == null)
        {
            _resource = new LoggingResources();
        }
        return _resource;
    }
}

Classes in that package would then do something like:
    private static LoggingResources     _resources = LoggingResources.getInstance();

    // logging.test=Save stuff of \"{0}\" for \"{1}\" aack.
    String message = _resources.getString("logging.key", arg0, arg1);

    or

    // logging.test=Save stuff of \"{0}\" for \"{1}\" aack.
    _logger.error(_resources.getString("logging.key", arg0, arg1));
</code></pre>
 *
 * @author ccollins
 */
public abstract class AbstractResourceHelper
{
    private static Logger    _log = LoggerFactory.getLogger(AbstractResourceHelper.class);

    /** This is the resource bundle to use for a given package/OSGI bundle */
    protected ResourceBundle resources;

    /**
     * Empty Constructor - subclass should create the resource class as a singleton.
     */
    protected AbstractResourceHelper()
    {
        // empty
    }

    /**
     * Set the bundle and get a error log messages if the resource bundle is not found.
     * The "ResourceBundle.getBundle(bundleName);" must be done in the subclass so that the bundle is found
     * by the class loader.
     *
     * @param resource pass ResourceBundle.getBundle(bundleName);
     * @param bundleName pass the name of the bundle so a error can logged if not found.
     */
    protected void setBundle(ResourceBundle resource, String bundleName)
    {
        this.resources = resource;
        if ( this.resources == null )
        {
            // This string is not localized since it doesn't make sense
            // to go to a bundle if you couldn't find the current bundle.
             _log.error("Missing Resource Bundle " + bundleName); //$NON-NLS-1$
         }
    }

    /**
     * Get the static stored resource bundle.
     * @return the ResourceBundle used.
     */
    public ResourceBundle getResourceBundle()
    {
        return this.resources;
    }

    /**
     * Get the property defined in the resource bundle for the given key. This property
     * should be an integer. If none is defined return the default.
     *
     * @param key the key in the resource bundle.
     * @param defaultValue default integer to return if no value is found for the key.
     * @return the integer value for the key in the bundle or defaultValue if none was found.
     */
    public int getValue(String key, int defaultValue)
    {
        String stringValue = getString(key);
        if ( stringValue != null )
        {
            try
            {
                return Integer.parseInt(stringValue);
            }
            catch (NumberFormatException ee)
            {
                // catch and ignore and return the defaultValue
                if (_log.isDebugEnabled()) _log.debug("Bundle failed to get an integer Value for key=" + key); //$NON-NLS-1$
            }
        }
        return defaultValue;
    }

    /**
     * Get the property defined in the resource bundle for the given key. This property
     * should be an long. If none is defined return the default.
     *
     * @param key the key in the resource bundle.
     * @param defaultValue default long to return if no value is found for the key.
     * @return the long value for the key in the bundle or defaultValue if none was found.
     */
    public long getValue(String key, long defaultValue)
    {
        String stringValue = getString(key);
        if ( stringValue != null )
        {
            try
            {
                return Long.parseLong(stringValue);
            }
            catch (NumberFormatException ee)
            {
                // catch and ignore and return the defaultValue
                if (_log.isDebugEnabled()) _log.debug("Bundle failed to get an long Value for key=" + key); //$NON-NLS-1$
            }
        }
        return defaultValue;
    }

    /**
     * Get the property defined in the resource bundle for the given key. This property
     * should be boolean ("true" of "false"). If none is defined false will be returned.
     *
     * @param key the key in the resource bundle.
     * @return the boolean value for the key in the bundle or false if none was found.
     */
    public boolean isResource(String key)
    {
        return getString(key).equals("" + true); //$NON-NLS-1$
    }

    /**
     * Get the property defined in the resource bundle for the given key. If
     * none is defined (MissingResourceException is caught), return the key.
     *
     * @param key the key in the resource bundle.
     * @return the string value for the key in the bundle or key if none was found.
     */
    public String getString(String key)
    {
        try
        {
            return this.resources.getString(key);
        }
        catch (MissingResourceException ee)
        {
            // catch and ignore and return the defaultValue
            _log.warn("Missing resource key=" + key); //$NON-NLS-1$
            return key;
        }
    }

    /**
     * Build a formated string from the resource bundle.
     *
     * @param key the key into the resource bundle that has the formated string.
     * @param arg0 the first argument.
     * @return the formated string with the argument in-line.
     */
    public String getString(String key, Object arg0)
    {
        Object[] args = new Object[1];
        args[0] = arg0;

        MessageFormat formatter = new MessageFormat(getString(key));
        return formatter.format(args);
    }

    /**
     * Build a formated string from the resource bundle.
     *
     * @param key the key into the resource bundle that has the formated string.
     * @param arg0 the first argument.
     * @param arg1 the second argument.
     * @return the formated string with the arguments in-line.
     */
    public String getString(String key, Object arg0, Object arg1)
    {
        Object[] args = new Object[2];
        args[0] = arg0;
        args[1] = arg1;

        MessageFormat formatter = new MessageFormat(getString(key));
        return formatter.format(args);
    }

    /**
     * Build a formated string from the resource bundle.
     *
     * @param key the key into the resource bundle that has the formated string.
     * @param arg0 the first argument.
     * @param arg1 the second argument.
     * @param arg2 the third argument.
     * @return the formated string with the arguments in-line.
     */
    public String getString(String key, Object arg0, Object arg1, Object arg2)
    {
        Object[] args = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;

        MessageFormat formatter = new MessageFormat(getString(key));
        return formatter.format(args);
    }

    /**
     * Build a formated string from the resource bundle.
     *
     * @param key the key into the resource bundle that has the formated string.
     * @param arg0 the first argument.
     * @param arg1 the second argument.
     * @param arg2 the third argument.
     * @param arg3 the forth argument.
     * @return the formated string with the arguments in-line.
     */
    public String getString(String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        Object[] args = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;

        MessageFormat formatter = new MessageFormat(getString(key));
        return formatter.format(args);
    }

    /**
     * Build a formated string from the resource bundle.
     *
     * @param key the key into the resource bundle that has the formated string.
     * @param arg0 the first argument.
     * @param arg1 the second argument.
     * @param arg2 the third argument.
     * @param arg3 the forth argument.
     * @param arg4 the forth argument.
     * @return the formated string with the arguments in-line.
     */
    public String getString(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4)
    {
        Object[] args = new Object[5];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        args[4] = arg4;

        MessageFormat formatter = new MessageFormat(getString(key));
        return formatter.format(args);
    }

    /**
     * Build a formated string from the resource bundle.
     *
     * @param key the key into the resource bundle that has the formated string.
     * @param args an array of arguments
     * @return the formated string with the arguments in-line.
     */
    public String getString(String key, Object... args)
    {
        MessageFormat formatter = new MessageFormat(getString(key));
        return formatter.format(args);
    }
}
