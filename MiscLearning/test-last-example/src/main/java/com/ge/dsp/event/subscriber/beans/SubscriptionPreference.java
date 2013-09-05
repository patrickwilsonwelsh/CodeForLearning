package com.ge.dsp.event.subscriber.beans;

import java.io.Serializable;



/**
 * The SubscrptionPreference entity
 * @author 502188493
 * @Since 1.5
 */
public class SubscriptionPreference implements Serializable
{

    private static final long serialVersionUID = 1L;

    private EventFilter eventFilter;

    private EventDestination notificationDestination;

    private boolean notificationEnable;

    private DigestPreference digestPreference;
    
    private String userIdentityID;

	/**
     * @return the userIdentityID
     */
    public String getUserIdentityID()
    {
        return this.userIdentityID;
    }

    /**
     * @param userIdentityID the userIdentityID to set
     */
    public void setUserIdentityID(String userIdentityID)
    {
        this.userIdentityID = userIdentityID;
    }

    /**
	 * @return eventFilter
	 */
	public EventFilter getEventFilter() {
		return this.eventFilter;
	}

	/**
	 * @param eventFilter sets eventFilter
	 */ 
	public void setEventFilter(EventFilter eventFilter) {
		this.eventFilter = eventFilter;
	}

	/**
	 * @return notificationDestination
	 */
	public EventDestination getNotificationDestination() {
		return this.notificationDestination;
	}

	/**
	 * @param notificationDestination sets it
	 */
	public void setNotificationDestination(EventDestination notificationDestination) {
		this.notificationDestination = notificationDestination;
	}

	/**
	 * @return gets Digest Preference
	 */
	public DigestPreference getDigestPreference() {
		return this.digestPreference;
	}

	/**
	 * @param digestPreference sets Digest preference
	 */
	public void setDigestPreference(DigestPreference digestPreference) {
		this.digestPreference = digestPreference;
	}

	/**
	 * @return checks id notification is enabled
	 */
	
    public boolean isNotificationEnable() {
		return this.notificationEnable;
	}

	/**
	 * @param notificationEnable sets it
	 */
	public void setNotificationEnable(boolean notificationEnable) {
		this.notificationEnable = notificationEnable;
	}
 

}
