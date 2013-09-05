package com.ge.dsp.event.subscriber.core.impl.listener;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.powermock.modules.testng.PowerMockTestCase;

import com.ge.dsp.event.publisher.beans.MessageEvent;
import com.ge.dsp.event.subscriber.api.IEventCallback;
import com.ge.dsp.event.subscriber.beans.EventDestination;
import com.ge.dsp.event.subscriber.beans.EventDestination.NotificationType;
import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;
import com.ge.dsp.event.subscriber.core.fakes.IPreference;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.impl.SubscriberListener;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;
import com.ge.dsp.notification.api.INotificationBean;
import com.ge.dsp.notification.api.INotificationService;
import com.ge.dsp.notification.exception.NotificationException;

public class SubscriberListenerTestBase extends PowerMockTestCase {
	private static final String EMPTY_STRING = "";
	private static final String JSON = "{\"eventFilter\":{\"eventType\":\"ALERT\",\"eventName\":null,\"context\":\"testSendMessageFilterByEventTypeAndContext\"},\"notificationDestination\":{\"type\":\"EMAIL\",\"value\":[\"dsp.encore@ge.com\"]},\"notificationEnable\":true,\"digestPreference\":{\"digestFrequency\":\"daily\",\"digestTime\":18,\"dayOfMonth\":0,\"dayOfWeek\":null,\"digestEnable\":true},\"userIdentityID\":\"http://User.A@test.dsp.ge.com\"}";

	protected static final String FILTERED_BY_EVENT_TYPE_AND_CONTEXT = "testSendMessageFilterByEventTypeAndContext";
	protected InternalConfiguration internalConfig;
	protected MessageEvent fakeMessageEvent;
	protected FakeEventCallback fakeEventCallBack;
	protected SubscriberListener listener;
	protected INotificationService fakeNotification;
	
	protected List<IPreference> preferenceListWith(
			IPreference preference) {
		List<IPreference> preferenceList = new ArrayList<IPreference>();
		preferenceList.add(preference);
		return preferenceList;
	}
	
	protected IPreference createPopulatedPreference() {
		IPreference preference = createEmptyUserPreference();
		preference.setDupsContext(FILTERED_BY_EVENT_TYPE_AND_CONTEXT);
		preference.setKey("A747/800/ALERT");
		preference.setUuid("http://User.A@test.dsp.ge.com");
		preference.setValue("{\"eventFilter\":{\"eventType\":\"ALERT\",\"eventName\":null,\"context\":\"testSendMessageFilterByEventTypeAndContext\"}");
		return preference;
	}

	protected List<IPreference> preferenceListWithNullJasonFlag() {
		IPreference preference = createEmptyUserPreference();
		preference = setPreference(preference, true);

		List<IPreference> preferenceList = preferenceListWith(preference);
		return preferenceList;
	}

	protected INotificationBean getActualBean() {
		return (INotificationBean) ((FakeNotification) fakeNotification).instantiatedBean;
	}

	protected INotificationService setNotificationService() {
		fakeNotification = new FakeNotification();
		internalConfig.setNotificationService(fakeNotification);
		return fakeNotification;
	}

	protected IPreference setPreference(IPreference preference, boolean isJson) {
		preference.setDupsContext(FILTERED_BY_EVENT_TYPE_AND_CONTEXT);
		preference.setKey("A747/800/ALERT");
		preference.setUuid("http://User.A@test.dsp.ge.com");
		if (isJson)
			preference.setValue(JSON);
		else
			preference.setValue(EMPTY_STRING);
		
		return preference;
	}

	protected List<UserPreferenceEntity> getUserPreferenceEntities_FromSubscriberHelper() {
		return SubscriberHelper.getUsersSubscriptionPreferences(
				fakeMessageEvent.getEventName(),
				fakeMessageEvent.getEventType(),
				fakeMessageEvent.getContext());
	}


	protected IPreference createEmptyUserPreference() {
		IPreference preference = new IPreference() {
			private String uuid;
			private String value;
			private String key;
			private String dupsContext;

			@Override
			public String getUuid() {

				return this.uuid;
			}

			@Override
			public void setUuid(String uuid) {
				this.uuid = uuid;
			}

			@Override
			public String getValue() {

				return this.value;
			}

			@Override
			public void setValue(String value) {
				this.value = value;
			}

			@Override
			public String getKey() {
				return this.key;
			}

			@Override
			public void setKey(String key) {
				this.key = key;
			}

			@Override
			public String getDupsContext() {
				return this.dupsContext;
			}

			@Override
			public void setDupsContext(String dupsContext) {
				this.dupsContext = dupsContext;
			}

		};
		return preference;
	}

	protected List<UserPreferenceEntity> aUserPreferenceEntityListWith(
			NotificationType notificationType) {
		List<UserPreferenceEntity> userPreferences = new ArrayList<UserPreferenceEntity>();
		UserPreferenceEntity userPreferenceEntity = new UserPreferenceEntity();
		userPreferenceEntity.setDigestEnabled(true);
		userPreferenceEntity.setDigestHour(8);
		userPreferenceEntity.setDupsContext("IVHM");

		EventDestination destination = new EventDestination();
		destination.setType(notificationType);
		List<String> emails = new ArrayList<String>();
		emails.add("dsp.encore@ge.com");

		destination.setValue(emails);
		userPreferenceEntity.setDestination(destination);
		userPreferenceEntity.setFrequence("hourly");
		userPreferenceEntity.setNotificationEnabled(Boolean.TRUE);
		userPreferenceEntity.setPreferenceIndex("fleet/500/ALERT");
		userPreferenceEntity.setUuid("http://User.A@test.dsp.ge.com");
		userPreferences.add(userPreferenceEntity);

		return userPreferences;

	}

	protected MessageEvent createMessageEvent() {
		fakeMessageEvent = new MessageEvent();
		fakeMessageEvent
				.setContext(FILTERED_BY_EVENT_TYPE_AND_CONTEXT);
		fakeMessageEvent.setEventId("123");
		// event.setEventName("DSP-K notification test");
		fakeMessageEvent.setEventTime("2012-08-14 09:00:02");
		fakeMessageEvent.setEventType("ALERT");
		fakeMessageEvent
				.setMessageBody("Notification Subscriber service unit test.");
		fakeMessageEvent.setMessageType("Notification");
		// event.setMessageRecipient("chen@ge.com");
		fakeMessageEvent.setMessageAttachment("abc".getBytes());
		fakeMessageEvent.setAttachmentContentType("application/pdf");
		fakeMessageEvent.setAttachmentName("report");
		// event.setEventProperties(eventProperties)
		fakeMessageEvent.setMessageSender("systemAdmin");
		fakeMessageEvent.setSystemId("100");
		fakeMessageEvent.setSystemType("fleet");
		fakeMessageEvent.setMessageRecipient("kaparaboyna@ge.com");
		return fakeMessageEvent;
	}

	protected void assignFakeCallBackToListener(IEventCallback fakeEventCallBack) {
		listener = new SubscriberListener(fakeEventCallBack);
		listener.setEventCallback(fakeEventCallBack);
		assertNotNull(listener.getEventCallback());
	}

	protected class FakeNotification implements INotificationService {
		public boolean wasCalled = false;
		public INotificationBean instantiatedBean;

		@Override
		public void notify(INotificationBean actualNotificationBean)
				throws NotificationException {
			this.wasCalled = true;
			this.instantiatedBean = actualNotificationBean;
		}

	}

	protected class FakeEventCallback implements IEventCallback {
		public boolean processEventWasCalled = false;

		@Override
		public void processEvent(MessageEvent event) {
			this.processEventWasCalled = true;

		}
	}

}
