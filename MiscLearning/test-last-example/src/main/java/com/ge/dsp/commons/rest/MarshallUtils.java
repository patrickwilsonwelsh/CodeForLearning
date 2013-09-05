/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.commons.rest;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains methods to help with converting object to XML and JSON
 * or returning object from XML and JSON.
 *
 * @author ccollins
 */
public class MarshallUtils
{
    private static Logger _log = LoggerFactory.getLogger(MarshallUtils.class);

    /**
     * Convert the object into an XML String representation.
     *
     * @param theObject item to convert into XML.
     * @return XML string representation or null on error
     */
    public static String toXmlString(Object theObject)
    {
        StringWriter stringWriter = new StringWriter();
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(theObject.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(theObject, stringWriter);
        }
        catch (JAXBException ee)
        {
            _log.error("", ee); //$NON-NLS-1$
            return null;
        }

        return stringWriter.toString();
    }

    /**
     * From an XML string build a theClass object.
     *
     * @param theClass the class to bind the XML into.
     * @param xmlContent the XML representation of the PropertiesRestWrapper.
     * @return Instance of theClass or null if the content could not be parsed.
     */
    public static Object fromXml(Class<?> theClass, String xmlContent)
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(theClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlContent);

            return unmarshaller.unmarshal(reader);
        }
        catch (JAXBException ee)
        {
            _log.error("", ee); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Return the object as a JSON string.
     *
     * @param theObject the object to serialize into a JSON string.
     * @return JSON string or null if serialization fails.
     */
    public static String toJsonString(Object theObject)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(theObject);
        }
        catch (Exception ee)
        {
            _log.error("", ee); //$NON-NLS-1$
            return null;
        }
    }

    /**
     * From an JSON string build a theClass object.
     *
     * @param typeRef the TypeReference to bind with the JSON. Build this like: <code>
     *  org.codehaus.jackson.type.TypeReference<PropertiesRestWrapper> typeRef = new org.codehaus.jackson.type.TypeReference<PropertiesRestWrapper>()
     *  {
     *      // nothing
     *  };
     *  </code>
     * @param jsonContent the XML representation of the PropertiesRestWrapper.
     * @return Instance of theClass or null if the content could not be parsed.
     */
    public static Object fromJson(org.codehaus.jackson.type.TypeReference<?> typeRef, String jsonContent)
    {
        try
        {
            return new ObjectMapper().readValue(jsonContent, typeRef);
        }
        catch (Exception ee)
        {
            _log.error("", ee); //$NON-NLS-1$
            return null;
        }
    }
}
