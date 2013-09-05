package com.ge.dsp.event.subscriber.core.impl;
/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */



import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ge.dsp.commons.rest.MarshallUtils;
import com.ge.dsp.core.util.fakes.MonitorUtil;
import com.ge.dsp.event.subscriber.api.IEventCallback;
import com.ge.dsp.event.subscriber.beans.EventDestination;
import com.ge.dsp.event.subscriber.beans.EventDestination.NotificationType;
import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;
import com.ge.dsp.event.subscriber.core.util.SubscriberResources;
import com.ge.dsp.notification.beans.EmailNotification;
import com.ge.dsp.notification.beans.HTTPNotification;
import com.ge.dsp.notification.exception.NotificationException;

/**
 * Message event subscriber listener class
 * User: 212304931
 * Date: 10/28/12
 * Time: 5:45 PM
 */
public class SubscriberListener
{

    // SLF4J LoggerFactory
    private static Logger              _logger            = LoggerFactory.getLogger(SubscriberListener.class);

    private static SubscriberResources _resource          = SubscriberResources.getInstance();

    private IEventCallback             eventCallback;

    /** Internal message counter for logging purpose **/
    private final AtomicLong           counter            = new AtomicLong(0);

    private static String              MONITOR_GROUP_NAME = "Subscriber-service";                             //$NON-NLS-1$

    private static final String        _classname         = SubscriberListener.class.getName();
    private static final String        EMAIL_ID           = "event.subscription@ge.com";                      //$NON-NLS-1$
    private static String              EMAIL_CONTENT_TYPE = "text/html";                                      //$NON-NLS-1$

    /**
     * Constructor to set the IEventCallback
     * 
     * @param eventCallback the callback reference
     */
    public SubscriberListener(IEventCallback eventCallback)
    {
        this.eventCallback = eventCallback;
    }

    /**
     * @return the eventCallback
     */
    public IEventCallback getEventCallback()
    {
        return this.eventCallback;
    }

    /**
     * @param eventCallback the eventCallback to set
     */
    public void setEventCallback(IEventCallback eventCallback)
    {
        this.eventCallback = eventCallback;
    }

    /**
     * This is the default listener
     * This is the POJO message driven adapter method for asynchronous message reception.
     * 
     * @param event the messageEvent reference
     */
    public void handleMessage(final Object event)
    {

        if ( MonitorUtil.monitorEnabled() )
        {
            MonitorUtil.start(MONITOR_GROUP_NAME, _classname, "handleMessage"); //$NON-NLS-1$
        }

        LoggerFactory.getLogger(this.getClass()).debug("message event received...#" + this.counter.incrementAndGet()); //$NON-NLS-1$

        try
        {
            // If the client did not provide any notification callback method, use dsp default delivery service
            if ( (this.eventCallback == null) && (event instanceof com.ge.dsp.event.publisher.beans.MessageEvent) )
            {
                com.ge.dsp.event.publisher.beans.MessageEvent msgEvent = (com.ge.dsp.event.publisher.beans.MessageEvent) event;

                // String eventFilter = msgEvent.generateRoutingKey();

                // If email notification recipient is provided in message event then notify the recipient immediately
                if ( (msgEvent.getMessageRecipient() != null) && (msgEvent.getMessageRecipient().trim().length() > 0) )
                {

                    byte[] attachment = msgEvent.getMessageAttachment();
                    // clear the messageAttachment, it's not needed for email recipient
                    msgEvent.setMessageAttachment(null);

                    String[] mailto = new String[]
                    {
                        msgEvent.getMessageRecipient()
                    };

                    EmailNotification notification = new EmailNotification(mailto, null, null, EMAIL_ID, null,
                            msgEvent.getEventName(), msgEvent.toString(), msgEvent.getAttachmentName(), attachment,
                            EMAIL_CONTENT_TYPE, msgEvent.getAttachmentContentType());
                    InternalConfiguration.getInstance().getNotificationService().notify(notification);
                   
                }

                // Warning: Dups performance issue
                List<UserPreferenceEntity> userPreferences = SubscriberHelper.getUsersSubscriptionPreferences(
                        msgEvent.getEventName(), msgEvent.getEventType(), msgEvent.getContext());

                for (int i = 0; i < userPreferences.size(); i++)
                {
                    UserPreferenceEntity entity = userPreferences.get(i);
                    sendNotification(event, msgEvent, entity);
                }

            }

            else if ( event instanceof com.ge.dsp.event.publisher.beans.MessageEvent )
            {
                this.eventCallback.processEvent((com.ge.dsp.event.publisher.beans.MessageEvent) event);
            }
            else
            {
                _logger.warn("Unsupported event type; event will be dropped; cannot process it: " + event.getClass().getName()); //$NON-NLS-1$
            }

            if ( MonitorUtil.monitorEnabled() )
            {
                MonitorUtil.stop(MONITOR_GROUP_NAME, _classname, "handleMessage", null); //$NON-NLS-1$ 
            }
        }
        catch (Exception e)
        {
            if ( MonitorUtil.monitorEnabled() )
            {
                MonitorUtil.fail(MONITOR_GROUP_NAME, _classname, "handleMessage", null); //$NON-NLS-1$
            }

            _logger.error(_resource.getString("SubscriberListener_process_error"), e); //$NON-NLS-1$

        }
    }

    /**
     * @param event
     * @param msgEvent
     * @param entity
     * @throws MessagingException
     * @throws NotificationException
     */
    private void sendNotification(final Object event, com.ge.dsp.event.publisher.beans.MessageEvent msgEvent,
            UserPreferenceEntity entity)
            throws MessagingException, NotificationException
    {
        EventDestination destination = entity.getDestination();
        List<String> value = destination.getValue();

        if ( (destination != null) && (destination.getType() == NotificationType.EMAIL) )
        {
            EmailNotification notification = null;
            if ( msgEvent.getMessageAttachment() != null )
            {
                byte[] attachment = msgEvent.getMessageAttachment();

                notification = new EmailNotification(value.toArray(new String[value.size()]), null, null,
                        EMAIL_ID, null, msgEvent.getEventName(), msgEvent.toString(),
                        msgEvent.getAttachmentName(), attachment, EMAIL_CONTENT_TYPE,
                        msgEvent.getAttachmentContentType());
            }
            else
            {
                notification = new EmailNotification(value.toArray(new String[value.size()]), EMAIL_ID,
                        msgEvent.getEventName(), msgEvent.toString(), EMAIL_CONTENT_TYPE);
            }

            InternalConfiguration.getInstance().getNotificationService().notify(notification);
        }
        if ( (destination != null) && (destination.getType() == NotificationType.HTTP) )
        {
            HTTPNotification httpBean = new HTTPNotification();
            httpBean.setUrls(value);
            httpBean.setBody(MarshallUtils.toJsonString(event));
            InternalConfiguration.getInstance().getNotificationService().notify(httpBean);
        }
    }
}
