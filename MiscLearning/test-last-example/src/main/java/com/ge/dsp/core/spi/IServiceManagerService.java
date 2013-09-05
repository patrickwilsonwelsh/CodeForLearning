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
 * This service is responsible for providing service related tasks. Users can register a service, find a service, or
 * retrieve the information about all services currently available in the system.
 */
public interface IServiceManagerService
        extends IServiceQueryService
{
    /**
     * Register a service.
     * 
     * @param serviceObject serviceObject the object that provides the service, all interfaces starting with com.ge will this service be exported under.
     * @param serviceAttributes attributes belong to this service object
     * @throws CoreException if failed
     */
    public void registerService(Object serviceObject, Map<String, String> serviceAttributes)
            throws CoreException;
    
    /**
     * Register a service with an attribute map that has string keys and an unknown type for values
     * 
     * @param serviceObject is the object that provides the service, all interfaces starting with com.ge will this service be exported under.
     * @param serviceAttributes are the attributes that belong to the registered service object.
     * @throws CoreException thrown on failure
     */
    public void registerServiceWithCustomAttributeType(Object serviceObject, Map<String, ?> serviceAttributes)
            throws CoreException;

    /**
     * Find a service, given the name and its associated attributes, since only com.ge interfaces will be exported, this method
     * can only be used to find services starting with com.ge
     * 
     * @param interfaceName name of the service interface
     * @param serviceAttributes attributes belong to this service object
     * @return service object
     * @exception ServiceNotFoundException if service is not found under the combination of service name and attributes
     */
    public Object findRegisteredService(String interfaceName, Map<String, String> serviceAttributes)
            throws ServiceNotFoundException;

    /**
     * Create a service object using the given service attributes, and register it under the required service attributes.
     * This service can be used later. The bundle that creates this service factory is the owner of the created service.
     * 
     * @param serviceFactoryInfo information about the service factory.
     * @param serviceAttributes service attributes required
     * @return service object
     * @throws CoreException if failed
     */
    public Object createAndRegisterService(ServiceInfo serviceFactoryInfo, Map<String, String> serviceAttributes)
            throws CoreException;

    /**
     * Create a SOAP web service using the given object. The object will have to have some standard jax-ws annotations, such as
     * having @WebService at the class level, etc. Any sample WS code from CXF should work.
     * 
     * @param serviceObject service object
     * @param address address for this service, this will be appended to the fully qualified address space DSP is maintaining.
     * @throws CoreException if failed to register this as a web service using SOAP
     */
    public void createSoapWebService(Object serviceObject, String address)
            throws CoreException;

    /**
     * Create a SOAP web service using the given object. The object will have to have some standard jax-ws annotations, such as
     * having @WebService at the class level, etc. Any sample WS code from CXF should work.
     * 
     * @param serviceObject service object
     * @param serviceAttributeMap service attribute map
     * @param address address for this service, this will be appended to the fully qualified address space DSP is maintaining.
     * @throws CoreException if failed to register this as a web service using SOAP
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
     * @param serviceObject service object, this object will have to implement some interfaces that has RestfulServiceAddress annotation
     * @throws CoreException if failed
     */
    public void createRestWebService(Object serviceObject)
            throws CoreException;

    /**
     * Create a restful service. JSR-311 specification needs to be followed(annotation, etc).
     * 
     * @param serviceObject service object, this object will have to implement some interfaces that has RestfulServiceAddress annotation
     * @param serviceAttributeMap service attribute map
     * @throws CoreException if failed
     */
    public void createRestWebService(Object serviceObject, Map<String, String> serviceAttributeMap)
            throws CoreException;
            
                
    /**
     * Create a restful service and register the service with custom attribute type.
     * JSR-311 specification needs to be followed(annotation, etc).
     * @param serviceObject service object, this object will have to implement some interfaces that has RestfulServiceAddress annotation
     * @param serviceAttributeMap service attribute map
     * @throws CoreException if failed
     */
    public void createRestWebServiceWithCustomAttributeType(Object serviceObject, Map<String, ?> serviceAttributeMap)
    		throws CoreException;

}
