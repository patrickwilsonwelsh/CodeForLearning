package com.ge.dsp.event.subscriber.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author 502188493
 * @Since 1.5
 */
@XmlRootElement
public class EventFilter implements Serializable {
	
	private String eventType;
	private String eventName;
	private String context;
	private final static String WILD_CARD = "*"; //$NON-NLS-1$
	private static final long serialVersionUID = 1L;
		
	/**
	 * @return eventType
	 */
	public String getEventType() {
		return this.eventType;
	}
	/**
	 * @param eventType sets eventType 
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return eventName
	 */
	public String getEventName() {
		return this.eventName;
	}
	/**
	 * @param eventName sets it
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return context
	 */ 
	public String getContext() {
		return this.context;
	}
	/**
	 * @param context sets it
	 */
	public void setContext(String context) {
		this.context = context;
	}
	
	 /**
	 * @return routing key
	 */
	public String generateRoutingKey() {

	        String eventNameKey = (this.eventName == null || this.eventName.trim().isEmpty()) ? WILD_CARD : this.eventName;
	        String eventTypeKey = (this.eventType == null || this.eventType.trim().isEmpty()) ? WILD_CARD : this.eventType;
	        String contextKey = (this.context == null || this.context.trim().isEmpty()) ? WILD_CARD : this.context;
	        
	        return eventNameKey + "." + eventTypeKey + "." + contextKey; //$NON-NLS-1$ //$NON-NLS-2$
	 }
	
	/**
     * @return the wild card routing key combination
     */
    public static String generateMatchAllRoutingKey()
    {
        return WILD_CARD + "." + WILD_CARD + "." + WILD_CARD; //$NON-NLS-1$ //$NON-NLS-2$
    }	

}
