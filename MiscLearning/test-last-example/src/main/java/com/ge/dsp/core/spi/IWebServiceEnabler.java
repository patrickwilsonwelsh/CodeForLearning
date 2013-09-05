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

import java.util.Map;

/**
 * This is an interface that defines the way to enable a service via SOAP or RESTful web services.
 */
public interface IWebServiceEnabler
{
    /**
     * Create a SOAP web service using the given object. The object will have to have some standard jax-ws annotations, such as
     * having @WebService at the class level, etc. Any sample WS code from CXF should work.
     * 
     * @param serviceObject service object
     * @param address address for this service, this will be appended to the fully qualified address space DSP is maintaining.
     * @throws com.ge.dsp.core.spi.CoreException
     *             if failed to register this as a web service using SOAP
     */
    public void createSoapWebService(Object serviceObject, String address)
            throws CoreException;

    /**
     * Create a SOAP web service using the given object. The object will have to have some standard jax-ws annotations, such as
     * having @WebService at the class level, etc. Any sample WS code from CXF should work.
     * 
     * @param serviceObject service object
     * @param serviceAttributeMap map of serviceAttributes
     * @param address address for this service, this will be appended to the fully qualified address space DSP is maintaining.
     * @throws com.ge.dsp.core.spi.CoreException
     *             if failed to register this as a web service using SOAP
     */
    public void createSoapWebService(Object serviceObject, Map<String, String> serviceAttributeMap, String address)
            throws CoreException;

    /**
     * Remove the web service under the address
     * 
     * @param address address for the web service
     * @throws CoreException if failed to remove the service
     */
    public void removeSoapWebService(String address)
            throws CoreException;

    /**
     * Create a restful service. JSR-311 specification needs to be followed(annotation, etc).
     * 
     * @param serviceObject service object
     * @throws CoreException if failed
     */
    public void createRestWebService(Object serviceObject)
            throws CoreException;

    /**
     * Create a restful service. JSR-311 specification needs to be followed(annotation, etc).
     * 
     * @param serviceObject service object
     * @param serviceAttributeMap map of serviceAttributes
     * @throws CoreException if failed
     */
    public void createRestWebService(Object serviceObject, Map<String, String> serviceAttributeMap)
            throws CoreException;
            
    /**
     * Create a restful service and register the service with custom attribute type.
     * JSR-311 specification needs to be followed(annotation, etc).
     * @param serviceObject service object
     * @param serviceAttributeMap map of serviceAttributes
     * @throws CoreException if failed
     */
    public void createRestWebServiceWithCustomAttributeType(Object serviceObject, Map<String, ?> serviceAttributeMap)
    		throws CoreException;
}
