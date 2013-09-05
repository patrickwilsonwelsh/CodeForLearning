/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.core.fakes;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.BeanUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ge.dsp.core.util.fakes.BundleUtil;
import com.ge.dsp.core.util.fakes.CoreUtil;
import com.ge.dsp.core.util.fakes.MonitorUtil;
import com.ge.dsp.dsi.dups.api.IDups;
import com.ge.dsp.event.publisher.beans.MessageEvent;
import com.ge.dsp.event.subscriber.api.IEventCallback;
import com.ge.dsp.event.subscriber.beans.EventDestination;
import com.ge.dsp.event.subscriber.beans.EventDestination.NotificationType;
import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.impl.SubscriberListener;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;
import com.ge.dsp.event.subscriber.core.util.SubConstants;
import com.ge.dsp.notification.api.INotificationBean;
import com.ge.dsp.notification.api.INotificationService;
import com.ge.dsp.notification.exception.NotificationException;

/**
 * 
 * @author 212304931
 */
@PrepareForTest(
{
        BundleUtil.class, InternalConfiguration.class, CoreUtil.class, DBUtil.class, SubscriberHelper.class,
        Calendar.class, MonitorUtil.class
})
@SuppressWarnings(
{
        "javadoc", "nls"
})
public class OriginalSubscriberListenerTest extends PowerMockTestCase
{
	org.slf4j.Logger _logger;

    InternalConfiguration        internalConfig              = InternalConfiguration.getInstance();
    
    @DataProvider(name = "debugFlag")
    public Object[][] creatDebugFlagData()
    {
        return new Object[][]
        {
                {
                    Boolean.FALSE
                },

                {
                    Boolean.TRUE
                },
        };
    }

    @Test(dataProvider = "debugFlag")
    public void testEmailNotify(boolean debugFlag)
    {
    	mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        this._logger = Mockito.mock(org.slf4j.Logger.class);
        Whitebox.setInternalState(DigestCleanupJob.class, this._logger);
        when(this._logger.isDebugEnabled()).thenReturn(debugFlag);
        
        MessageEvent event = createMessageEvent();
        
        MyCallbackEventCallback eventCallback = new MyCallbackEventCallback();
        SubscriberListener listener = new SubscriberListener(eventCallback);
        listener.setEventCallback(eventCallback);
        
        assertNotNull(listener.getEventCallback());
        
        listener.setEventCallback(null);
        mockStatic(SubscriberHelper.class);
        //List<UserPreferenceEntity> userPreferences = SubscriberHelper.getUsersSubscriptionPreferences(msgEvent.getContext(), msgEvent.generateRoutingKey()); 
        List<UserPreferenceEntity> userPreferences = new ArrayList<UserPreferenceEntity>();
        UserPreferenceEntity userPreferenceEntity = new UserPreferenceEntity();
        userPreferenceEntity.setDigestEnabled(true);
        userPreferenceEntity.setDigestHour(8);
        userPreferenceEntity.setDupsContext("IVHM");
       
        EventDestination destination = new EventDestination();
        destination.setType(NotificationType.EMAIL);
        destination.addValue("chen@ge.com");
        
        userPreferenceEntity.setDestination(destination);
        userPreferenceEntity.setFrequence("hourly");
        userPreferenceEntity.setNotificationEnabled(Boolean.TRUE);
        userPreferenceEntity.setPreferenceIndex("fleet/500/ALERT");
        userPreferenceEntity.setUuid("http://User.A@test.dsp.ge.com");
        userPreferences.add(userPreferenceEntity);
        
        when(SubscriberHelper.getUsersSubscriptionPreferences(event.getEventName(), event.getEventType(), event.getContext())).thenReturn(userPreferences);
        INotificationService notification = new MyNotification();
        this.internalConfig.setNotificationService(notification);
        
        listener.handleMessage(event);
        
        assertTrue(((MyNotification)notification).wasCalled);
    }
        

    @Test(dataProvider = "debugFlag")
    public void testHttpNotify(boolean debugFlag)
    {
        mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        MessageEvent event = createMessageEvent();

        
        MyCallbackEventCallback eventCallback = new MyCallbackEventCallback();
        SubscriberListener listener = new SubscriberListener(eventCallback);
        listener.setEventCallback(eventCallback);
        
        assertNotNull(listener.getEventCallback());
        assertNotNull(listener.getEventCallback());
        
        listener.setEventCallback(null);
        mockStatic(SubscriberHelper.class);
        //List<UserPreferenceEntity> userPreferences = SubscriberHelper.getUsersSubscriptionPreferences(msgEvent.getContext(), msgEvent.generateRoutingKey()); 
        List<UserPreferenceEntity> userPreferences = new ArrayList<UserPreferenceEntity>();
        UserPreferenceEntity userPreferenceEntity = new UserPreferenceEntity();
        userPreferenceEntity.setDigestEnabled(true);
        userPreferenceEntity.setDigestHour(8);
        userPreferenceEntity.setDupsContext("IVHM");
       
        EventDestination destination = new EventDestination();
        destination.setType(NotificationType.HTTP);
        destination.addValue("http://abc.com");
        
        userPreferenceEntity.setDestination(destination);
        userPreferenceEntity.setFrequence("hourly");
        userPreferenceEntity.setNotificationEnabled(Boolean.TRUE);
        userPreferenceEntity.setPreferenceIndex("fleet/500/ALERT");
        userPreferenceEntity.setUuid("http://User.A@test.dsp.ge.com");
        userPreferences.add(userPreferenceEntity);
        
        when(SubscriberHelper.getUsersSubscriptionPreferences(event.getEventName(), event.getEventType(), event.getContext())).thenReturn(userPreferences);
        INotificationService notification = new MyNotification();
        this.internalConfig.setNotificationService(notification);
        
        listener.handleMessage(event);
        
        assertTrue(((MyNotification)notification).wasCalled);

    }
    
    
    @Test(dataProvider = "debugFlag")
    public void testHandleNullMessageEvent(boolean debugFlag)
    {
        
        mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        MyCallbackEventCallback eventCallback = new MyCallbackEventCallback();
        SubscriberListener listener = new SubscriberListener(eventCallback);
        listener.handleMessage(null);
    }
    
    
    @Test(dataProvider = "debugFlag")
    public void testHandleEventCallback(boolean debugFlag)
    {
        
        mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        MyCallbackEventCallback eventCallback = new MyCallbackEventCallback();
        SubscriberListener listener = new SubscriberListener(eventCallback);
        listener.handleMessage(createMessageEvent());
        assertTrue(eventCallback.wasCalled);
        
    }
    
    @Test(dataProvider = "debugFlag")
    public void testMail(boolean debugFlag)
    {
    	mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        this._logger = Mockito.mock(org.slf4j.Logger.class);
        Whitebox.setInternalState(DigestCleanupJob.class, this._logger);

        when(this._logger.isDebugEnabled()).thenReturn(debugFlag);
        
        MessageEvent event = new MessageEvent();
        
        event.setContext("IVHM");
        event.setEventId("123");
        event.setEventName("DSP-K notification test");
        event.setEventTime("2012-08-14 09:00:02");
        event.setEventType("immediate");
        event.setMessageBody("Notification Subscriber service unit test.");
        event.setMessageType("Notification");
        event.setMessageRecipient("chen@ge.com");
        event.setMessageAttachment("abc".getBytes());
        event.setAttachmentContentType("application/pdf");
        event.setAttachmentName("report");
        // event.setEventProperties(eventProperties)
        event.setMessageSender("systemAdmin");
        event.setSystemId("100");
        event.setSystemType("fleet");

        
        MyCallbackEventCallback eventCallback = new MyCallbackEventCallback();
        SubscriberListener listener = new SubscriberListener(eventCallback);
        listener.setEventCallback(eventCallback);
        
        assertNotNull(listener.getEventCallback());
        
        listener.setEventCallback(null);
        mockStatic(SubscriberHelper.class);
        
       
        //destination.addValue("http://abc.com");
        
        List<UserPreferenceEntity> userPreferences = createUserPreferenceEntity();
        when(SubscriberHelper.getUsersSubscriptionPreferences(event.getEventName(), event.getEventType(), event.getContext())).thenReturn(userPreferences);
        INotificationService notification = new MyNotification();
        this.internalConfig.setNotificationService(notification);
        
        listener.handleMessage(event);
        
        assertTrue(((MyNotification)notification).wasCalled);
        
    }
    
    
    @Test(dataProvider = "debugFlag")
    public void testGetUsersSubscriptionPreferenceWithNullPreference(boolean debugFlag) throws DupsUserNotFoundException
    {
    	mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        this._logger = Mockito.mock(org.slf4j.Logger.class);
        Whitebox.setInternalState(DigestCleanupJob.class, this._logger);

        when(this._logger.isDebugEnabled()).thenReturn(debugFlag);
        
        MessageEvent event = createMessageEvent(); 
       
        //destination.addValue("http://abc.com");
        
        IDups dupsServiceMock = mock(IDups.class);
        this.internalConfig.setDupsService(dupsServiceMock);       
        when( dupsServiceMock.getUserPreferenceAll("testSendMessageFilterByEventTypeAndContext")).thenReturn(null);    
              
        List<UserPreferenceEntity> userPreferenceEntity = SubscriberHelper.getUsersSubscriptionPreferences(event.getEventName(), event.getEventType(), event.getContext());
        
		assertNull(userPreferenceEntity);      
    }
    
    @Test(dataProvider = "debugFlag")
    public void testGetUsersSubscriptionPreferenceWithNullSubscription(boolean debugFlag) throws DupsUserNotFoundException
    {
    	mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        this._logger = Mockito.mock(org.slf4j.Logger.class);
        Whitebox.setInternalState(DigestCleanupJob.class, this._logger);

        when(this._logger.isDebugEnabled()).thenReturn(debugFlag);
        
        MessageEvent event = createMessageEvent(); 
       
        //destination.addValue("http://abc.com");
        
        IDups dupsServiceMock = mock(IDups.class);
        this.internalConfig.setDupsService(dupsServiceMock);    
        
        IPreference preference = createUserPreference();
        setPreferenceWithWrongJson(preference);
        List<IPreference> preferenceList = new ArrayList<IPreference>();
        preferenceList.add(preference);
        when( dupsServiceMock.getUserPreferenceAll("testSendMessageFilterByEventTypeAndContext")).thenReturn(preferenceList);    
          
              
        List<UserPreferenceEntity> userPreferenceEntity = SubscriberHelper.getUsersSubscriptionPreferences(event.getEventName(), event.getEventType(), event.getContext());
        
		assertTrue(userPreferenceEntity.isEmpty());      
    }
    
    
    
    @Test(dataProvider = "debugFlag")
    public void testGetUsersSubscriptionPreference(boolean debugFlag) throws DupsUserNotFoundException
    {
    	mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        this._logger = Mockito.mock(org.slf4j.Logger.class);
		Whitebox.setInternalState(SubscriberHelper.class, this._logger);
		when(this._logger.isDebugEnabled()).thenReturn(debugFlag);
        
        MessageEvent event = createMessageEvent();
        IDups dupsServiceMock = mock(IDups.class);
        
        IPreference preference = createUserPreference();
        setPreference(preference, true);
        List<IPreference> preferenceList = new ArrayList<IPreference>();
        preferenceList.add(preference);
        when( dupsServiceMock.getUserPreferenceAll("testSendMessageFilterByEventTypeAndContext")).thenReturn(preferenceList);    

        List<UserPreferenceEntity> userPreferences = createUserPreferenceEntity();
        
        this.internalConfig.setDupsService(dupsServiceMock);
        when(SubscriberHelper.getUsersSubscriptionPreferences(event.getEventName(), event.getEventType(), event.getContext())).thenReturn(userPreferences);
    }
    
    @Test(dataProvider = "debugFlag")
    public void testGetUsersSubscriptionPreferenceWithContext(boolean debugFlag) throws DupsUserNotFoundException
    {
    	mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
        this._logger = Mockito.mock(org.slf4j.Logger.class);
		Whitebox.setInternalState(SubscriberHelper.class, this._logger);
		when(this._logger.isDebugEnabled()).thenReturn(debugFlag);
        
        MessageEvent event = createMessageEvent();
        IDups dupsServiceMock = mock(IDups.class);
        String preferenceIndex = "";
        if(event.getEventName() == null)
           preferenceIndex = preferenceIndex + "*";
        else
        	preferenceIndex = preferenceIndex + event.getEventName();
        
        if(event.getEventType() == null)
            preferenceIndex = preferenceIndex + ".*";
         else
         	preferenceIndex = preferenceIndex + "."+event.getEventType();
        
        preferenceIndex = preferenceIndex + "."+event.getContext();
        
        
        IPreference preference = createUserPreference();
        setPreference(preference, true);
        List<IPreference> preferenceList = new ArrayList<IPreference>();
        preferenceList.add(preference);
        when( dupsServiceMock.getUserPreferenceAll("testSendMessageFilterByEventTypeAndContext")).thenReturn(preferenceList);    

        List<UserPreferenceEntity> userPreferences = createUserPreferenceEntity();
        
        this.internalConfig.setDupsService(dupsServiceMock);
        when(SubscriberHelper.getUsersSubscriptionPreferences(event.getContext(), preferenceIndex)).thenReturn(userPreferences);
    }


	/**
	 * @param preference
	 */
	private void setPreference(IPreference preference, boolean isJson) {
		preference.setDupsContext("testSendMessageFilterByEventTypeAndContext");
        preference.setKey("A747/800/ALERT");
        preference.setUuid("http://User.A@test.dsp.ge.com");
        if (isJson)
           preference.setValue("{\"eventFilter\":{\"eventType\":\"ALERT\",\"eventName\":null,\"context\":\"testSendMessageFilterByEventTypeAndContext\"},\"notificationDestination\":{\"type\":\"EMAIL\",\"value\":[\"dsp.encore@ge.com\"]},\"notificationEnable\":true,\"digestPreference\":{\"digestFrequency\":\"daily\",\"digestTime\":18,\"dayOfMonth\":0,\"dayOfWeek\":null,\"digestEnable\":true},\"userIdentityID\":\"http://User.A@test.dsp.ge.com\"}");
        else
        	preference.setValue("");
	}
	
	private void setPreferenceWithWrongJson(IPreference preference) {
		preference.setDupsContext("testSendMessageFilterByEventTypeAndContext");
        preference.setKey("A747/800/ALERT");
        preference.setUuid("http://User.A@test.dsp.ge.com");
       
        preference.setValue("{\"eventFilter\":{\"eventType\":\"ALERT\",\"eventName\":null,\"context\":\"testSendMessageFilterByEventTypeAndContext\"}");
	}


	/**
	 * @return
	 */

    private IPreference createUserPreference() {
		IPreference preference = new IPreference()
        {
            private String uuid;
            private String value;
            private String key;
            private String dupsContext;

            @Override
            public String getUuid()
            {
                
                return this.uuid;
            }

            @Override
            public void setUuid(String uuid)
            {
                this.uuid = uuid;
            }

            @Override
            public String getValue()
            {
                
                return this.value;
            }

            @Override
            public void setValue(String value)
            {
                this.value = value;
            }

            @Override
            public String getKey()
            {
                return this.key;
            }

            @Override
            public void setKey(String key)
            {
                this.key = key;
            }

            @Override
            public String getDupsContext()
            {
                return this.dupsContext;
            }

            @Override
            public void setDupsContext(String dupsContext)
            {
                this.dupsContext = dupsContext;
            }

        };
		return preference;
	}


	/**
	 * 
	 */
	private List<UserPreferenceEntity> createUserPreferenceEntity() {
		List<UserPreferenceEntity> userPreferences = new ArrayList<UserPreferenceEntity>();
        UserPreferenceEntity userPreferenceEntity = new UserPreferenceEntity();
        userPreferenceEntity.setDigestEnabled(true);
        userPreferenceEntity.setDigestHour(8);
        userPreferenceEntity.setDupsContext("IVHM");
       
        EventDestination destination = new EventDestination();
        destination.setType(NotificationType.EMAIL);
        List<String> emails = new ArrayList<String>();
        emails.add("dsp.encore@ge.com"); //$NON-NLS-1$

        //destination.setType(NotificationType.EMAIL);
        destination.setValue(emails);
        userPreferenceEntity.setDestination(destination);
        userPreferenceEntity.setFrequence("hourly");
        userPreferenceEntity.setNotificationEnabled(Boolean.TRUE);
        userPreferenceEntity.setPreferenceIndex("fleet/500/ALERT");
        userPreferenceEntity.setUuid("http://User.A@test.dsp.ge.com");
        userPreferences.add(userPreferenceEntity);
        
        return userPreferences;
        
	}


	/**
	 * @return
	 */
	private MessageEvent createMessageEvent() {
		MessageEvent event = new MessageEvent();
        
        event.setContext("testSendMessageFilterByEventTypeAndContext");
        event.setEventId("123");
        //event.setEventName("DSP-K notification test");
        event.setEventTime("2012-08-14 09:00:02");
        event.setEventType("ALERT");
        event.setMessageBody("Notification Subscriber service unit test.");
        event.setMessageType("Notification");
       // event.setMessageRecipient("chen@ge.com");
        event.setMessageAttachment("abc".getBytes());
        event.setAttachmentContentType("application/pdf");
        event.setAttachmentName("report");
        // event.setEventProperties(eventProperties)
        event.setMessageSender("systemAdmin");
        event.setSystemId("100");
        event.setSystemType("fleet");
        event.setMessageRecipient("kaparaboyna@ge.com");
		return event;
	}
    
    @Test(dataProvider = "debugFlag")
    public void testGetUsersSubscriptionPreferenceNullJson(boolean debugFlag) throws DupsUserNotFoundException
    {
    	mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
               
        this._logger = Mockito.mock(org.slf4j.Logger.class);
		Whitebox.setInternalState(SubscriberHelper.class, this._logger);
		when(this._logger.isDebugEnabled()).thenReturn(debugFlag);
        
        MessageEvent event = createMessageEvent();
        IDups dupsServiceMock = mock(IDups.class);
        
        IPreference preference = createUserPreference();
        setPreference(preference, false);
        List<IPreference> preferenceList = new ArrayList<IPreference>();
        
       // IPreference nullJson = null;
        preferenceList.add(preference);
        
        when( dupsServiceMock.getUserPreferenceAll("testSendMessageFilterByEventTypeAndContext")).thenReturn(preferenceList);    

       // List<UserPreferenceEntity> userPreferences = createUserPreferenceEntity();
        this.internalConfig.setDupsService(dupsServiceMock);
        List<UserPreferenceEntity> userPreferenceEntity = SubscriberHelper.getUsersSubscriptionPreferences(event.getEventName(), event.getEventType(), event.getContext());
        
		assertTrue(userPreferenceEntity.isEmpty());      
        
    }
    
    @Test(dataProvider = "debugFlag")
    public void testInternalPersistence(boolean debugFlag) throws DBConfigurationException
    {
    	mockStatic(MonitorUtil.class);
        when(MonitorUtil.monitorEnabled()).thenReturn(debugFlag);
        
     // create event entity object to persist into db
        MessageEventEntity evententity = new MessageEventEntity();
        
        MessageEvent event = createMessageEvent();
        
        EntityManagerFactory emf = mock(EntityManagerFactory.class);
        //EntityManager em = null;
        EntityManager em = mock(EntityManager.class);
        EntityTransaction tx = mock(EntityTransaction.class);
        when(em.getTransaction()).thenReturn(tx);
     
        mockStatic(DBUtil.class);
        when(DBUtil.getJpaEntityManagerFactory(UserEventEntity.class, SubConstants.EVENT_PERSISTENCE_UNIT)).thenReturn(
                emf);
        when(emf.createEntityManager()).thenReturn(em);
        
 
        // Copy all event properties to MesageEventEntity
        BeanUtils.copyProperties(event, evententity);
        evententity.setTimestamp(Calendar.getInstance().getTime());
        
        String preferenceIndex = "";
        if(event.getEventName() == null)
           preferenceIndex = preferenceIndex + "*";
        else
        	preferenceIndex = preferenceIndex + event.getEventName();
        
        if(event.getEventType() == null)
            preferenceIndex = preferenceIndex + ".*";
         else
         	preferenceIndex = preferenceIndex + "."+event.getEventType();
        
        preferenceIndex = preferenceIndex + "."+event.getContext();

        // create user entity object to persist into db
        UserEventEntity userevent = new UserEventEntity("http://User.A@test.dsp.ge.com", "testSendMessageFilterByEventTypeAndContext",
                preferenceIndex);
        userevent.setEvent(evententity);
        userevent.setTimestamp(Calendar.getInstance().getTime());

        
        // persist messageEvent and userEvent entities
        SubscriberHelper.internalPersistence(userevent, SubConstants.EVENT_PERSISTENCE_UNIT);
        
        
    }
    
    
    
    
    
    public class MyNotification implements INotificationService
    {

        public boolean wasCalled = false;
        
        /* (non-Javadoc)
         * @see com.ge.dsp.notification.api.INotificationService#notify(com.ge.dsp.notification.api.INotificationBean)
         */
        @Override
        public void notify(INotificationBean notify)
                throws NotificationException
        {
            this.wasCalled = true;
            
        }
        
    }


    // class for implement own callback
    public class MyCallbackEventCallback
            implements IEventCallback
    {

        public boolean wasCalled = false;

        /*
         * (non-Javadoc)
         * @see com.ge.dsp.notification.subscriber.api.ISubscriberCallback#processEvent(com.ge.dsp.event.beans.MessageEvent)
         */
        @Override
        public void processEvent(MessageEvent event)
        {
            // process event logic
            this.wasCalled = true;

        }
    }

}
