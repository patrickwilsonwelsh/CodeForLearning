/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.notification.api;

import com.ge.dsp.notification.exception.NotificationException;

/**
 * DSP-K Notification Service Interface.
 * 
 * The Notification Service API provides the user the ability to notify any message via email or http post
 * 
 * @author 212307967
 */
public interface INotificationService
{

    /**
     * Notifies the user via email or http message based on the given INotification object
     * 
     * @param notify - the notification object
     * @throws NotificationException thrown if there an exception or the notification failed.
     */
    public void notify(INotificationBean notify) throws NotificationException;
}
