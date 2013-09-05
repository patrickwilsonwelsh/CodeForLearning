/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.core.spi;

/**
 * Enumerations which represents the various states of a feature.
 * 
 * @author 212314537
 */
public enum FeatureState
{
    /**
     * The feature is disabled and thus unavailable to services that want to use it.
     */
    disabled,

    /**
     * The feature is passive and thus available to services that want to use it but off by default.
     */
    passive,

    /**
     * The feature is active and thus available to services that want to use it and on by default.
     */
    active,

    /**
     * The feature is enforced and thus always on regardless of whether the service explicitly specifies it wants it on.
     */
    enforced
}
