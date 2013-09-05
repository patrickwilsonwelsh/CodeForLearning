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

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ge.dsp.core.util.CoreConstants;

/**
 * This class is a provider for parsing exceptions to rest json message.
 * 
 * @author 212322802
 */
@Provider
public class FaultProvider
        implements ExceptionMapper<ServiceFault>
{

    public Response toResponse(ServiceFault sf)
    {
        Response.ResponseBuilder builder = null;
        if ( (sf.getErrorType() != null) && sf.getErrorType().equals(CoreConstants.ERROR_TYPE_VALIDATION) )
        {
            builder = Response.status(Response.Status.BAD_REQUEST);
        }
        else
        {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        builder.type("application/json");   //$NON-NLS-1$
        Fault f = new Fault();
        f.setMessage(sf.getErrormessage());
        f.setCode(sf.getErrorcode());
        builder.entity(f);
        Response r = builder.build();
        return r;
    }

}
