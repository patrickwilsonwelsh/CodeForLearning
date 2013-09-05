/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.core.entities;

import java.util.ArrayList;
import java.util.List;

import com.ge.dsp.event.subscriber.beans.EventDestination;


/**
 * User Preference Object
 * 
 * @author 212304931
 * 
 */
public class UserPreferenceEntity
{

    private String       uuid;

    private String       dupsContext;

    private String       preferenceIndex;

    private boolean      notificationEnabled;

    private boolean      digestEnabled;

    private String       frequence;

    private int          digestHour;

    private int          weekdate;

    private int          monthday;
    
    private List<String> emailList = new ArrayList<String>();
    
    private EventDestination destination;
  


    /**
     * @return the destination
     */
    public EventDestination getDestination()
    {
        return this.destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(EventDestination destination)
    {
        this.destination = destination;
    }

    /**
     * @return the uuid
     */
    public String getUuid()
    {
        return this.uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    /**
     * @return the dupsContext
     */
    public String getDupsContext()
    {
        return this.dupsContext;
    }

    /**
     * @param dupsContext the dupsContext to set
     */
    public void setDupsContext(String dupsContext)
    {
        this.dupsContext = dupsContext;
    }

    /**
     * @return the preferenceIndex
     */
    public String getPreferenceIndex()
    {
        return this.preferenceIndex;
    }

    /**
     * @param preferenceIndex the preferenceIndex to set
     */
    public void setPreferenceIndex(String preferenceIndex)
    {
        this.preferenceIndex = preferenceIndex;
    }

    /**
     * @return the notificationEnabled
     */
    public boolean isNotificationEnabled()
    {
        return this.notificationEnabled;
    }

    /**
     * @param notificationEnabled the notificationEnabled to set
     */
    public void setNotificationEnabled(boolean notificationEnabled)
    {
        this.notificationEnabled = notificationEnabled;
    }

    /**
     * @return the digestEnabled
     */
    public boolean isDigestEnabled()
    {
        return this.digestEnabled;
    }

    /**
     * @param digestEnabled the digestEnabled to set
     */
    public void setDigestEnabled(boolean digestEnabled)
    {
        this.digestEnabled = digestEnabled;
    }

    /**
     * @return the frequence
     */
    public String getFrequence()
    {
        return this.frequence;
    }

    /**
     * @param frequence the frequence to set
     */
    public void setFrequence(String frequence)
    {
        this.frequence = frequence;
    }

    /**
     * @return the digestHour
     */
    public int getDigestHour()
    {
        return this.digestHour;
    }

    /**
     * @param digestHour the digestHour to set
     */
    public void setDigestHour(int digestHour)
    {
        this.digestHour = digestHour;
    }

    /**
     * @return the weekdate
     */
    public int getWeekdate()
    {
        return this.weekdate;
    }

    /**
     * @param weekdate the weekdate to set
     */
    public void setWeekdate(int weekdate)
    {
        this.weekdate = weekdate;
    }

    /**
     * @return the monthday
     */
    public int getMonthday()
    {
        return this.monthday;
    }

    /**
     * @param monthday the monthday to set
     */
    public void setMonthday(int monthday)
    {
        this.monthday = monthday;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings(
    {
        "nls"
    })
    @Override
    public String toString()
    {
        return "UserPreferenceEntity [" + (getUuid() != null ? "uuid=" + getUuid() + ", " : "")
                + (getDupsContext() != null ? "dupsContext=" + getDupsContext() + ", " : "")
                + (getPreferenceIndex() != null ? "preferenceIndex=" + getPreferenceIndex() + ", " : "")
                + "notificationEnabled=" + isNotificationEnabled() + ", digestEnabled=" + isDigestEnabled() + ", "
                + (getFrequence() != null ? "frequence=" + getFrequence() + ", " : "") + "digestHour="
                + getDigestHour() + ", weekdate=" + getWeekdate() + ", monthday=" + getMonthday() + "]";
    }

    /**
     * @return the list of emails
     */
    public List<String> getEmailList()
    {
        return this.emailList;
    }

    /**
     * @param emailList set email list
     */
    public void setEmailList(List<String> emailList)
    {
        this.emailList = emailList;
    }

}
