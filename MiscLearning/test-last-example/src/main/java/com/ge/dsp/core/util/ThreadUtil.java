/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ge.dsp.core.localization.CoreResources;

/**
 * A utility class providing thread pools for asynchronous execution.
 */
public class ThreadUtil
{
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.class);
    // todo: make it configurable
    private static ExecutorService threadPool = Executors.newFixedThreadPool(20, Executors.defaultThreadFactory());

    /**
     * Shutdown the service gracefully.
     */
    public void destroy()
    {
        if ( logger.isDebugEnabled() )
        {
            logger.debug("Shutting down DSP kernel thread pool."); //$NON-NLS-1$
        }

        if ( threadPool != null )
        {
            try
            {
                threadPool.shutdown();
                threadPool.awaitTermination(5, TimeUnit.SECONDS);
                threadPool.shutdownNow();
            }
            catch (Exception e)
            {
                logger.error(CoreResources.getInstance().getString("THREAD_POOL_SHUTDOWN_FAIL"), e); //$NON-NLS-1$
            }
        }
    }

    /**
     * @param runnable - submitTask takes thread as a parameter which used to submit the task to threadPool.
     */
    public static void submitTask(Runnable runnable)
    {
        threadPool.submit(runnable);
    }

}
