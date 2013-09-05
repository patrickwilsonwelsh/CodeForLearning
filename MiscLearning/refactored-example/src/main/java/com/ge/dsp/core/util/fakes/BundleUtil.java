/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.util.fakes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.jkernel.Bundle;

import com.sun.tools.javac.util.Version;

/**
 * A utility class for bundle related work.
 */
public class BundleUtil
{
    private static Logger logger = LoggerFactory.getLogger(BundleUtil.class);

    /**
     * Find the bundle that loads the class.
     * 
     * @param bundleContext
     *            bundle context
     * @param className
     *            fully qualified name of the class
     * @return Bundle object or null if cannot be found
     */
    public static Bundle findBundleByClassName(BundleContext bundleContext, String className)
    {
        
        return null;
    }

    /**
     * Find the bundle that loads the class.
     * 
     * 
     * @param clazz
     *            class to be checked
     * @return Bundle object or null if cannot be found
     */
    public static Bundle findBundleByClassName(Class<?> clazz)
    {
        return null;
    }

    /**
     * Find the bundle version of passed class.
     * 
     * 
     * @param clazz
     *            class to be checked
     * @return Version String or null if cannot be found bundle
     */
    public static Version findBundleVersionByClassName(Class<?> clazz)
    {

        return null;
    }
}
