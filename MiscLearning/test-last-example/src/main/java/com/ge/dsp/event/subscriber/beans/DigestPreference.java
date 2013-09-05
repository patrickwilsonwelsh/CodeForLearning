/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.ge.dsp.dsi.scheduler.CronExpressionUtil;

/**
 * Notification digest preference class
 * @author 212304931
 *
 */
@XmlRootElement
public class DigestPreference implements Serializable
{

    private static final long serialVersionUID = 1L;
    private Frequency digestFrequency;
    private int digestTime;
    private int dayOfMonth;
    private CronExpressionUtil.DayOfWeek dayOfWeek;
    private boolean digestEnable;


    /**
     * Digest frequency parameters
     * @author 212304931
     *
     */
    public enum Frequency
    {
        /**
         * daily digest frequency
         */
        daily,

        /**
         * weekly digest frequency
         */
        weekly,

        /**
         * monthly digest frequency
         */
        monthly;


       /**
         * @return digest frequency in lowerCase
         */
        public String getStringName()
        {
            return name().toLowerCase();
        }
    }


    /**
     * @return the digestFrequency
     */
    public Frequency getDigestFrequency()
    {
        return this.digestFrequency;
    }

    /**
     * @param digestFrequency the digestFrequency to set, values are "daily", "weekly" or "monthly"
     */
    public void setDigestFrequency(Frequency digestFrequency)
    {
        this.digestFrequency = digestFrequency;
    }

    /**
     * @return the digestTime the time the notification to be digested, values range [0..23]
     */
    public int getDigestTime()
    {
        return this.digestTime;
    }

    /**
     * @param digestTime the digestTime to set for notification to be digested, values range [0..23]
     */
    public void setDigestTime(int digestTime)
    {
        this.digestTime = digestTime;
    }

    /**
     * @return the dayOfMonth the notification to be digested, values range [1..31]
     */
    public int getDayOfMonth()
    {
        return this.dayOfMonth;
    }

    /**
     * @param dayOfMonth the dayOfMonth for notification to be digested, values range [1..31].  The digest time
     * is needed if this method is set.  Otherwise the default digest time will be set to 0.
     */
    public void setDayOfMonth(int dayOfMonth)
    {
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * @return the dayOfWeek for the notification to be digested, values are [0..6]
     */
    public CronExpressionUtil.DayOfWeek getDayOfWeek()
    {
        return this.dayOfWeek;
    }

    /**
     * @param dayOfWeek the dayOfWeek the notification to be digested, values are [0..6], which 0 is SUNDAY.
     * The digestTime is needed if this method is set.  Otherwise the default digest time will be set to 0.
     */
    public void setDayOfWeek(CronExpressionUtil.DayOfWeek dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * @return true if the digest is Enabled, false otherwise.
     */
    public boolean isDigestEnable()
    {
        return this.digestEnable;
    }

    /**
     * @param digestEnable - true to enable digest, false otherwise.  If true, other digest parameters are needed.
     * If other properties are not set, it will default to daily digest at 0 hour.
     */
    public void setDigestEnable(boolean digestEnable)
    {
        this.digestEnable = digestEnable;
    }

}
