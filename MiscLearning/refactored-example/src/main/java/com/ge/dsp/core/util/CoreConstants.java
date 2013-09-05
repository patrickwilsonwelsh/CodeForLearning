/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.util;

/**
 * 
 * Generic core constants for other services.
 */
@SuppressWarnings("nls")
public class CoreConstants
{
    /**
     * Services path for core services.
     */
    public static final String SERVICE_ROOT          = "/service";

    /**
     * Service path for cache service.
     */
    public static final String CACHE_SERVICE_ROOT    = "cache";

    /**
     * FaultProvider response type for mapping errors to REST Response.Status.BAD_REQUEST.
     */
    public static final String ERROR_TYPE_VALIDATION = "input_validation_error";
}
