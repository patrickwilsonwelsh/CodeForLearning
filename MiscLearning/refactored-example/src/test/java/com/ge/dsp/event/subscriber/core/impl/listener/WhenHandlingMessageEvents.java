/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.core.impl.listener;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.testng.Assert.assertTrue;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ge.dsp.event.subscriber.beans.EventDestination.NotificationType;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.impl.SubscriberListener;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;
import com.ge.dsp.notification.beans.EmailNotification;
import com.ge.dsp.notification.beans.HTTPNotification;

@PrepareForTest({
		SubscriberHelper.class})
@SuppressWarnings({ "javadoc", "nls" })
public class WhenHandlingMessageEvents<fakeEventCallBack> extends SubscriberListenerTestBase  {
	@BeforeTest
	public void setUp() {
		internalConfig = InternalConfiguration.getInstance();
		fakeNotification = setNotificationService();
		fakeMessageEvent = createMessageEvent();
		fakeEventCallBack = new FakeEventCallback();
		assignFakeCallBackToListener(fakeEventCallBack);
	}

	@Test
	public void emailNotification_IsCreated_WithNullEventCallBack() {
		mockStatic(SubscriberHelper.class);
		when(getUserPreferenceEntities_FromSubscriberHelper())
			.thenReturn(aUserPreferenceEntityListWith(NotificationType.EMAIL));

		listener.setEventCallback(null);
		listener.handleMessage(fakeMessageEvent);

		assertTrue(((FakeNotification) fakeNotification).wasCalled);
		assertTrue(getActualBean() instanceof EmailNotification);

	}

	@Test
	public void HttpNotify_IsCreated_WithNullEventCallBack() {
		mockStatic(SubscriberHelper.class);
		when(getUserPreferenceEntities_FromSubscriberHelper()).thenReturn(aUserPreferenceEntityListWith(NotificationType.HTTP));

		listener.setEventCallback(null);
		listener.handleMessage(fakeMessageEvent);

		assertTrue(((FakeNotification) fakeNotification).wasCalled);
		assertTrue(getActualBean() instanceof HTTPNotification);
	}

	@Test
	public void canHandle_NullMessageEvent() {
		listener.handleMessage(null);
	}

	@Test
	public void canHandle_NonNull_EventCallback() {
		listener = new SubscriberListener(fakeEventCallBack);
		listener.handleMessage(fakeMessageEvent);

		assertTrue(fakeEventCallBack.processEventWasCalled);
	}
}
