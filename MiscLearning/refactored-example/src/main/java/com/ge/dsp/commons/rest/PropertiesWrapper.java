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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Structures Properties into  XML or JSON for REST.<br>
 * JSON: <pre>
 *  {"properties":{"console":"true","file":"true","rootLogLevel":"TRACE","console.pattern":"%d|%p|%c|%m%n","file.pattern":"%d|%p|%c|%m%n","rootCategory":"com.ge.rest","applicationId":"rest"}}
 * </pre>
 * XML:  <pre>{@code
 * <PropertyWrapper>
 *     <Properties>
 *         <Property key="console" value="true"/>
 *         <Property key="file" value="true"/>
 *         <Property key="rootLogLevel" value="TRACE"/>
 *         <Property key="console.pattern" value="%d|%p|%c|%m%n"/>
 *         <Property key="file.pattern" value="%d|%p|%c|%m%n"/>
 *         <Property key="rootCategory" value="com.ge.rest"/>
 *         <Property key="applicationId" value="rest"/>
 *     </Properties>
 * </PropertyWrapper>
 * } </pre>
 *
 * @author ccollins
 */
@XmlRootElement(name="PropertyWrapper")
public class PropertiesWrapper
        implements Serializable
{
    private static final long       serialVersionUID = 1L;

    /**
     * Props argument.
     */
    private Properties props;

    /**
     * @return the properties
     */
    @XmlElement(name="Properties", required = true)
    @XmlJavaTypeAdapter(PropertiesAdaptor.class)
    public Properties getProperties()
    {
        return this.props;
    }

    /**
     * @param props the properties to set
     */
    public void setProperties(Properties props)
    {
        this.props = props;
    }

    /**
     * @return return the PropertiesWrapper as an XML String.
     * Example XML:  <pre>{@code
     * <PropertyWrapper>
     *     <Properties>
     *         <Property key="console" value="true"/>
     *         <Property key="file" value="true"/>
     *         <Property key="rootLogLevel" value="TRACE"/>
     *         <Property key="console.pattern" value="%d|%p|%c|%m%n"/>
     *         <Property key="file.pattern" value="%d|%p|%c|%m%n"/>
     *         <Property key="rootCategory" value="com.ge.rest"/>
     *         <Property key="applicationId" value="rest"/>
     *     </Properties>
     * </PropertyWrapper>
     * } </pre>
     */
    public String toXmlString()
    {
        return MarshallUtils.toXmlString(this);
    }

    /**
     * From an XML string build a PropertiesWrapper object.
     * @param xmlContent the XML representation of the PropertiesWrapper.
     * @return PropertiesWrapper or null if the content could not be parsed.
     */
    public static PropertiesWrapper fromXml(String xmlContent)
    {
        return (PropertiesWrapper)MarshallUtils.fromXml(PropertiesWrapper.class, xmlContent);
    }

    /**
     * JAXB auto creation on REST call with String parameters.
     * @param xmlContent the XML representation of the PropertiesWrapper.
     * @return PropertiesWrapper or null if the content could not be parsed.
     */
    public static PropertiesWrapper fromString(String xmlContent)
    {
        return fromXml(xmlContent);
    }

    /**
     * @return the JSON representation of this object.
     * <pre>
     *  {"properties":{"console":"true","file":"true","rootLogLevel":"TRACE","console.pattern":"%d|%p|%c|%m%n","file.pattern":"%d|%p|%c|%m%n","rootCategory":"com.ge.rest","applicationId":"rest"}}
     * </pre>
     */
    public String toJsonString()
    {
        return MarshallUtils.toJsonString(this);
    }

    /**
     * @param jsonContent the JSON content representing PropertiesWrapper.
     * @return PropertiesWrapper built from the JSON or null if parsing failed.
     */
    public static PropertiesWrapper fromJson(String jsonContent)
    {
         org.codehaus.jackson.type.TypeReference<PropertiesWrapper> typeRef = new org.codehaus.jackson.type.TypeReference<PropertiesWrapper>()
         {
             // nothing
         };
         return (PropertiesWrapper)MarshallUtils.fromJson(typeRef, jsonContent);
    }

    @Override
    public String toString()
    {
        return "" + this.props; //$NON-NLS-1$
    }
}

/**
 * Adapter class that knows how to serialize a "Properties" object.
 * @author ccollins
 */
class PropertiesAdaptor extends XmlAdapter<PropertiesMap, Properties>
{
    @Override
    public PropertiesMap marshal(Properties prop) throws Exception
    {
        PropertiesMap           myMap = new PropertiesMap();
        List<Property>          theList = myMap.getList();
        Enumeration<Object>     keys = prop.keys();
        while(keys.hasMoreElements())
        {
            String key = (String) keys.nextElement();
            theList.add(new Property(key, prop.getProperty(key)));
        }
        return myMap;
    }

    @Override
    public Properties unmarshal(PropertiesMap propMap) throws Exception
    {
        Properties props = new Properties();
        for (Property prop : propMap.getList())
        {
            props.put(prop.getKey(), prop.getValue());
        }
        return props;
    }
}

/**
 * Internal class used by the serializing adapter to convert a Properties object into XML.
 * @author ccollins
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PropertiesMap
{
    @XmlElement(name = "Property", required = true)
    private final List<Property> list = new ArrayList<Property>();

    /**
     * @return a list of <code>Property</code> objects.
     */
    public List<Property> getList()
    {
        return this.list;
    }
}

/**
 * Property object maps key and values into a serialize field.
 * @author ccollins
 */
class Property implements Serializable
{
    private static final long       serialVersionUID = 1L;

    private String key;
    private String value;

    /**
     * Empty constructor.
     */
    public Property()
    {
    }

    /**
     * Build a Property from key and value.
     * @param key property key
     * @param value property value.
     */
    public Property(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * @return the key assigned.
     */
    @XmlAttribute(name = "key", required = true)
    public String getKey()
    {
        return this.key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * @return the value assigned.
     */
    @XmlAttribute(name = "value", required = true)
    public String getValue()
    {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}
