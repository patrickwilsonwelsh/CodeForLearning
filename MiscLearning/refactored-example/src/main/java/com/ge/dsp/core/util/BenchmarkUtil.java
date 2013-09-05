/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
 
package com.ge.dsp.core.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.ge.dsp.core.localization.CoreResources;

/**
 * Static methods to add logging MDC context for determining benchmarks of methods.
 * The MDC is managed on a per thread basis. A child thread automatically inherits a copy of the mapped diagnostic context of its parent.
 * This class only pushes context when the DSP container is configured to measure "benchmarks". Methods can call
 * to push a time stamp for the start of a sequence and then pop the time on exit.
 * 
 * @author ccollins
 */
public class BenchmarkUtil
{
    private static final String                 STATUS_PASS = "PASS"; //$NON-NLS-1$
    private static final String                 STATUS_FAIL = "FAIL"; //$NON-NLS-1$
    private static final String                 DSP_BENCHMARK = "dsp.benchmark";   //$NON-NLS-1$
    private static boolean                      _benchmarkEnabled;
    private static Logger                       _log = LoggerFactory.getLogger(BenchmarkUtil.class);

    /**
     * Update the configuration flag based on configuration fields.
     * @param configMap properties from dsp.core.conf file.
     */
    public static void configUpdated(Map<String, String> configMap)
    {
        _benchmarkEnabled = "true".equals(configMap.get(DSP_BENCHMARK)); //$NON-NLS-1$
    }

    /**
     * Is benchmark measurement enabled?
     * @return true if benchmark is being measured, false otherwise.
     */
    public static boolean isBenchmark()
    {
        return _benchmarkEnabled;
    }
    
    /**
     * Push a new time stamp for this class name and method name.
     * @param className the class name of the method being tested (with package, i.e. <code>clazz.getName()</code>).
     * @param methodName the method to push a time stamp for. (only one sequence can be monitored per thread - i.e. don't call recursively)
     * What constitutes a "methodName" is up to the caller to determine. Method name could be a single method or a sequence of methods, basically
     * whatever you are trying to measure.
     */
    public static void start(String className, String methodName)
    {
        if (!isBenchmark()) return;
        if ( (className == null) || (methodName == null) ) return;
        
        long startTime = System.nanoTime(); //System.currentTimeMillis();
        
        pushMDC(className + methodName, "" + startTime); //$NON-NLS-1$
    }
    
    /**
     * Get the total time for this class name and method name. Log the current time stamp for this method.
     * @param groupName This will write a group key into the log for grouping this metric with others in the same bundle or module.
     * @param className the class name of the method being tested (with package, i.e. <code>clazz.getName()</code>).
     * @param methodName the method to pop the time stamp for.
     * What constitutes a "methodName" is up to the caller to determine. Method name could be a method or a sequence of methods, basically
     * whatever you are trying to measure.
     * @param arguments list of key/value arguments to output into the log as part of the JSON.
     * @return the total time for the method. 0 will be returned if the start method was never called for this class and method name.
     */
    public static long stop(String groupName, String className, String methodName, Map<String, String> arguments)
    {
        if (!isBenchmark()) return 0;
        if ( (className == null) || (methodName == null) ) return 0;
        
        long timeInterval = 0;       
        long endTime = System.nanoTime(); //System.currentTimeMillis();
    
        String value = getMDC(className + methodName);
        if ( (value != null) && (value.length() > 0) )
        {
            try
            {
                long startTime = Long.valueOf(value);
                
                // if the time is zero, it is bogus and we don't want to log it.
                if (startTime > 0)
                {
                    // we still want to show things like milliseconds instead of nano so divide by 1 million.
                    timeInterval = (endTime - startTime) / 1000000;
                    String args = ""; //$NON-NLS-1$
                    if ( (arguments != null) && (arguments.size() > 0) )
                    {
                        StringBuilder sb = new StringBuilder();
                        for (String key : arguments.keySet())
                        {
                            if (key == null) continue;
                            // replace any double quotes because this will break the JSON.
                            sb.append(","); //$NON-NLS-1$
                            sb.append("\"" + key.replace('"', '_') + "\":"); //$NON-NLS-1$ //$NON-NLS-2$
                            sb.append("\"" + ((arguments.get(key) != null) ? arguments.get(key).replace('"', '_') : "") + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        }
                        args = sb.toString();
                    }
                    // BENCHMARK_TIME=\"group\":\"{0}\",\"class\":\"{1}\",\"method\":\"{2}\",\"status\":\"{3}\",\"delta\":\"{4}\"{5}
                    _log.info(CoreResources.getInstance().getString("BENCHMARK_TIME", groupName, className, methodName, STATUS_PASS, "" + timeInterval, args)); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
            catch (NumberFormatException ee)
            {
                if (_log.isDebugEnabled()) _log.debug(className + ":" + methodName + " start time was not a long time value (" + value + ")");   //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            }
        }
        popMDC(className + methodName);
        return timeInterval;
    }
    
    /**
     * Fail the benchmark. This will write a fail status and 0 delta.
     * @param groupName This will write a group key into the log for grouping this metric with others in the same bundle or module.
     * @param className the class name of the method being tested (with package, i.e. <code>clazz.getName()</code>).
     * @param methodName the method to pop the time stamp for.
     * What constitutes a "methodName" is up to the caller to determine. Method name could be a method or a sequence of methods, basically
     * whatever you are trying to measure.
     * @param arguments list of key/value arguments to output into the log as part of the JSON.
     */
    public static void fail(String groupName, String className, String methodName, Map<String, String> arguments)
    {
        if (!isBenchmark()) return;
        if ( (className == null) || (methodName == null) ) return;

        String args = ""; //$NON-NLS-1$
        if ( (arguments != null) && (arguments.size() > 0) )
        {
            StringBuilder sb = new StringBuilder();
            for (String key : arguments.keySet())
            {
                if (key == null) continue;
                // replace any double quotes because this will break the JSON.
                sb.append(","); //$NON-NLS-1$
                sb.append("\"" + key.replace('"', '_') + "\":"); //$NON-NLS-1$ //$NON-NLS-2$
                sb.append("\"" + ((arguments.get(key) != null) ? arguments.get(key).replace('"', '_') : "") + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            args = sb.toString();
        }
        // BENCHMARK_TIME=\"group\":\"{0}\",\"class\":\"{1}\",\"method\":\"{2}\",\"status\":\"{3}\",\"delta\":\"{4}\"{5}
        _log.info(CoreResources.getInstance().getString("BENCHMARK_TIME", groupName, className, methodName, STATUS_FAIL, 0, args)); //$NON-NLS-1$
        popMDC(className + methodName);
    }

    /**
     * Build an argument map from a argument list. The arguments must be like: (key, value, key2, value2).
     * @param args key/value arguments for the output into the benchmark log.
     * @return Map of the arguments list in as key/value array.
     */
    public static Map<String, String> benchmarkArguments(String... args)
    {
        HashMap<String, String> map = new HashMap<String, String>();
        for (int ii = 0; ii < args.length; ii+=2)
        {
            map.put(args[ii], args[ii+1]);
        }
        return map;
    }
    
    /**
     * Push the value as an MDC context <code>context + DSP_BENCHMARK</code>
     * @param context the context to push a context for. (only one sequence can be tested per thread - i.e. don't call recursively)
     * @param value the value to push for the MDC.
     */
    private static void pushMDC(String context, String value)
    {
        MDC.put(context + DSP_BENCHMARK,  value);
    }

    /**
     * Remove the MDC context <code>context + DSP_BENCHMARK</code>.
     * @param context the context to pop.
     */
    private static void popMDC(String context)
    {
        MDC.remove(context + DSP_BENCHMARK);
    }

    /**
     * Get the MDC context <code>context + DSP_BENCHMARK</code> value currently stored for this thread.
     * @param context the context to pop.
     * @return the current value stored for the context.
     */
    private static String getMDC(String context)
    {
        return MDC.get(context + DSP_BENCHMARK);
    }

}
