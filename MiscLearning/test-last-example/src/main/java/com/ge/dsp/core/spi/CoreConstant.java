/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.spi;

/**
 * @author 502126727
 *         This file is used to create constant used in kernel code.
 */

public class CoreConstant
{
    /**
     * This constant is used to create custom port for web service call( soap or rest)
     */
    public static final String DSP_CUSTOM_WEB_SERVICE_PORT = "com.ge.dsp.webservice.port";  //$NON-NLS-1$
    /**
     * Use this constant to enable HTTPS.
     */
    public static final String DSP_HTTPS_ENABLE = "com.ge.dsp.webservice.https.enable"; //$NON-NLS-1$
    /**
     * Use this constant to specify a custom (non-default) HTTPS port.
     */
    public static final String DSP_CUSTOM_HTTPS_PORT = "com.ge.dsp.webservice.https.port"; //$NON-NLS-1$
    /**
     * Use this constant to enable authentication and authorization for a web service.
     */
    public static final String DSP_AUTH_ENABLE = "com.ge.dsp.webservice.auth.enable"; //$NON-NLS-1$
}
