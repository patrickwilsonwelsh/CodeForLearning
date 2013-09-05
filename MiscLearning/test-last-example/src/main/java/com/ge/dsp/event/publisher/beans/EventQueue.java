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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Shared queue instance used for in-memory persistence.
 * This provides Notification services out-of-box demo mode capability with any 3rd party dependency.
 * 
 * @author 212304931
 */
public class EventQueue
{

    /**
     * Java BlockingQueue provides thread safe when perform PUT and TAKE action.
     * This queue will be used by Publishser service to send any message event, then
     * Subscriber service consumes the message event immediately.
     * **/
    private BlockingQueue<MessageEvent> memoryQueue;

    private static EventQueue           eventQueue;

    /**
     * private constructor for singleton class
     */
    private EventQueue()
    {
        this.memoryQueue = new LinkedBlockingDeque<MessageEvent>();
    }

    /**
     * @return EventQueue singleton object
     */
    public static synchronized EventQueue getInstance()
    {
        if ( eventQueue == null )
        {
            eventQueue = new EventQueue();
        }

        return eventQueue;
    }

    /**
     * @return the memoryQueue
     */
    public BlockingQueue<MessageEvent> getMemoryQueue()
    {
        return this.memoryQueue;
    }

}
