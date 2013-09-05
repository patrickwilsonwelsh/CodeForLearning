/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
 
package com.ge.dsp.event.subscriber.api;

import com.ge.dsp.event.publisher.beans.MessageEvent;

/**
 * Message event subscriber callback interface.
 * Defines the mechanism used to process an event.
 * Otherwise, it processes the event through an email
 * notification.
 * @author 212304931
 * @since 1.5
 */
public interface IEventCallback
{
    
    /**
     * Defines the mechanism used to process an event.
     * Otherwise, it processes the event through an email
     * notification.
     * 
     * @param event The MessageEvent object
     */
    public void processEvent(MessageEvent event);

}
