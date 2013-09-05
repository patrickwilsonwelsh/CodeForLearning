/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.publisher.beans;

import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Message Event POJO.
 * Define the an API for mapping MessageEvent XML elements to Java Objects.
 * The XML Document is defined by MessageEvent Schema
 * Defines the API mapping from XML document to Java objects
 * 
 * @author 212307967
 * 
 */

@XmlRootElement(name = "messageEvent")
public class MessageEvent
        implements Serializable
{

    private static final long serialVersionUID = 1L;

    // The unique ID of the event, This is an optional filed, if solutions does not set then event service set a unique value to it.
    private String            eventId;

    // The name of the event, subscribers can filter events on this filed.  This is an optional field.
    private String            eventName;
    
    // The event type, subscribers can filter events on this filed.  This is an optional field.
    private String            eventType;
    
    // the   context which solutions can set any value which they  would like subscriber to filter.  This is an required filed
    private String            context;

    // The event creation time, this is optional  field
    private String            eventTime;

    // The mime type of message. This is an optional field
    private String            messageType;

    // The content of the message body.  This is optional field
    private String            messageBody;

    // The system which sends the event..  This is an optional field.
    private String            systemId;

    // The system type which sends the event.  This is an optional field.
    private String            systemType;

    // list of user defined properties.  This is optional field. Client can set an custom key value pairs to it.
    private EventPropertyList eventProperties;

    // file attachment name.  if messageAttachment is not null then this is a required field else this is optional
    private String            attachmentName;

    // based64 attachment.  This is optional field
    private byte[]            messageAttachment;

    // attachment attachmentContentType.  if messageAttachment is not null then this is required field else it is optional
    private String            attachmentContentType;

    // the message sender.  This is optional field
    private String            messageSender;
    
    // message recipient
    private String messageRecipient;
    
    private final static String WILD_CARD = "*"; //$NON-NLS-1$

    /**
     * @return the messageSender
     */
    public String getMessageSender()
    {
        return this.messageSender;
    }

    /**
     * @param messageSender the messageSender to set
     */
    public void setMessageSender(String messageSender)
    {
        this.messageSender = messageSender;
    }


    /**
     * Get attachment content type
     * 
     * @return attachment content type
     */
    public String getAttachmentContentType()
    {
        return this.attachmentContentType;
    }

    /**
     * Set attachment content type
     * 
     * @param attachmentContentType content type
     */
    public void setAttachmentContentType(String attachmentContentType)
    {
        this.attachmentContentType = attachmentContentType;
    }

    /**
     * Get attachment name
     * 
     * @return attachment name
     */
    public String getAttachmentName()
    {
        return this.attachmentName;
    }

    /**
     * set attachment name
     * 
     * @param attachmentName attachment name
     */
    public void setAttachmentName(String attachmentName)
    {
        this.attachmentName = attachmentName;
    }

    /**
     * Get application context
     * 
     * @return context string
     */
    public String getContext()
    {
        return this.context;
    }

    /**
     * Set application context
     * 
     * @param context context string
     */
    public void setContext(String context)
    {
        this.context = context;
    }

    /**
     * @return the messageAttachment
     */
    public byte[] getMessageAttachment()
    {
        return this.messageAttachment;
    }

    /**
     * @param messageAttachment the messageAttachment to set
     */
    public void setMessageAttachment(byte[] messageAttachment)
    {
        if ( messageAttachment != null )
        {
            this.messageAttachment = Arrays.copyOf(messageAttachment, messageAttachment.length);

        }
    }

    /**
     * @return the systemType
     */
    public String getSystemType()
    {
        return this.systemType;
    }

    /**
     * @param systemType the systemType to set
     */
    public void setSystemType(String systemType)
    {
        this.systemType = systemType;
    }

    /**
     * @return the eventProperties
     */
    @XmlElement(name = "eventProperties")
    public EventPropertyList getEventProperties()
    {
        return this.eventProperties;
    }

    /**
     * @param eventProperties the eventProperties to set
     */
    public void setEventProperties(EventPropertyList eventProperties)
    {
        this.eventProperties = eventProperties;
    }

    /**
     * @return the eventName
     */
    public String getEventName()
    {
        return this.eventName;
    }

    /**
     * @param eventName the eventName to set
     */
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    /**
     * @return the eventId
     */
    public String getEventId()
    {
        return this.eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    /**
     * @return the eventTime
     */
    public String getEventTime()
    {
        return this.eventTime;
    }

    /**
     * @param eventTime the eventTime to set
     */
    public void setEventTime(String eventTime)
    {
        this.eventTime = eventTime;
    }

    /**
     * @return the eventType
     */
    public String getEventType()
    {
        return this.eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    /**
     * @return the messageType
     */
    public String getMessageType()
    {
        return this.messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType)
    {
        this.messageType = messageType;
    }

    /**
     * @return the messageBody
     */
    public String getMessageBody()
    {
        return this.messageBody;
    }

    /**
     * @param messageBody the messageBody to set
     */
    public void setMessageBody(String messageBody)
    {
        this.messageBody = messageBody;
    }

   

    /**
     * @return the systemId
     */
    public String getSystemId()
    {
        return this.systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    /**
     * Get message recipient address
     * 
     * @return message recipient address
     */
    public String getMessageRecipient()
    {
        return this.messageRecipient;
    }

    /**
     * @param messageRecipient message recipient
     */
    public void setMessageRecipient(String messageRecipient)
    {
        this.messageRecipient = messageRecipient;
    }

    @Override
    public int hashCode()
    {
        int result = this.context != null ? this.context.hashCode() : 0;
        result = (31 * result) + (this.eventName != null ? this.eventName.hashCode() : 0);
        result = (31 * result) + (this.eventId != null ? this.eventId.hashCode() : 0);
        result = (31 * result) + (this.eventTime != null ? this.eventTime.hashCode() : 0);
        result = (31 * result) + (this.eventType != null ? this.eventType.hashCode() : 0);
        result = (31 * result) + (this.messageType != null ? this.messageType.hashCode() : 0);
        result = (31 * result) + (this.messageBody != null ? this.messageBody.hashCode() : 0);
        result = (31 * result) + (this.systemId != null ? this.systemId.hashCode() : 0);
        result = (31 * result) + (this.systemType != null ? this.systemType.hashCode() : 0);
        result = (31 * result) + (this.eventProperties != null ? this.eventProperties.hashCode() : 0);
        result = (31 * result) + (this.attachmentName != null ? this.attachmentName.hashCode() : 0);
        result = (31 * result) + (this.messageAttachment != null ? Arrays.hashCode(this.messageAttachment) : 0);
        result = (31 * result) + (this.attachmentContentType != null ? this.attachmentContentType.hashCode() : 0);
        result = (31 * result) + (this.messageRecipient != null ? this.messageRecipient.hashCode() : 0);
        return result;
    }

    @SuppressWarnings(
    {
        "nls"
    })
    @Override
    public String toString()
    {
        return "MessageEvent{" + "\n context='" + this.context + '\'' + "\n eventName='" + this.eventName + '\''
                + "\n eventId='" + this.eventId + '\'' + "\n eventTime='" + this.eventTime + '\'' + "\n eventType='"
                + this.eventType + '\'' + "\n messageType='" + this.messageType + '\'' + "\n messageBody='"
                + this.messageBody + '\'' + "\n systemId='" + this.systemId + '\'' + "\n systemType='"
                + this.systemType + '\'' + "\n routingKey='" + this.attachmentName + '\''
                + "\n attachmentContentType='" + this.attachmentContentType + '\'' + "\n messageSender='"
                + this.messageSender + '\'' + "\n messageRecipient='" + this.messageRecipient + '\'' + "\n" + '}'
                + "\n";
    }

    /**
     * TODO: 1. Filter anything has '.'
     * Key = EventId.EventName.EventType
     * @return the event filter
     */
    public String generateRoutingKey()
    {
        

        String eventNameKey = (this.eventName == null || this.eventName.trim().isEmpty()) ? WILD_CARD : this.eventName;
        String eventTypeKey = (this.eventType == null || this.eventType.trim().isEmpty()) ? WILD_CARD : this.eventType;
        String contextKey = (this.context == null || this.context.trim().isEmpty()) ? WILD_CARD : this.context;
        
        return eventNameKey + "." + eventTypeKey + "." + contextKey; //$NON-NLS-1$ //$NON-NLS-2$
    }

}
