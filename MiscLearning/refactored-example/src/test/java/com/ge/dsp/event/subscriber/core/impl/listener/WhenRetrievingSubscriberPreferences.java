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

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ge.dsp.dsi.dups.api.IDups;
import com.ge.dsp.event.subscriber.beans.EventDestination.NotificationType;
import com.ge.dsp.event.subscriber.core.fakes.DupsUserNotFoundException;
import com.ge.dsp.event.subscriber.core.fakes.IPreference;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;

@PrepareForTest({InternalConfiguration.class,
		SubscriberHelper.class})
@SuppressWarnings({ "javadoc", "nls" })
public class WhenRetrievingSubscriberPreferences<fakeEventCallBack> extends SubscriberListenerTestBase  {
	private IDups dupsServiceMock;

	@BeforeTest
	public void setUp() {
		dupsServiceMock = mock(IDups.class);
		internalConfig = InternalConfiguration.getInstance();
		internalConfig.setDupsService(dupsServiceMock);
		fakeMessageEvent = createMessageEvent();
	}

	@Test
	public void nullPreference_ReturnsNullPreferenceEntities() throws DupsUserNotFoundException {
		when(dupsServiceMock.getUserPreferenceAll(FILTERED_BY_EVENT_TYPE_AND_CONTEXT))
				.thenReturn(null);

		assertNull(getUserPreferenceEntities_FromSubscriberHelper());
	}

	@Test
	public void nullSubscriptionReturns_EmptyPreferenceEntities() throws DupsUserNotFoundException {
		when(dupsServiceMock.getUserPreferenceAll(FILTERED_BY_EVENT_TYPE_AND_CONTEXT))
				.thenReturn(preferenceListWith(createPopulatedPreference()));

		assertTrue(getUserPreferenceEntities_FromSubscriberHelper().isEmpty());
	}


	//Break this into multiple simpler test cases to localize failures better
	// What exactly is being tested here? What behaviors?
	@Test
	public void testGetUsersSubscriptionPreferenceWithContext() throws DupsUserNotFoundException {
		String preferenceIndex = "";
		
		if (fakeMessageEvent.getEventName() == null)
			preferenceIndex = preferenceIndex + "*";
		
		else
			preferenceIndex = preferenceIndex + fakeMessageEvent.getEventName();

		if (fakeMessageEvent.getEventType() == null) preferenceIndex = preferenceIndex + ".*";
		
		else
			preferenceIndex = preferenceIndex + "." + fakeMessageEvent.getEventType();

		preferenceIndex = preferenceIndex + "." + fakeMessageEvent.getContext();

		List<IPreference> preferenceList = preferenceListWithNullJasonFlag();

		when(dupsServiceMock.getUserPreferenceAll(FILTERED_BY_EVENT_TYPE_AND_CONTEXT))
				.thenReturn(preferenceList);
		
		when(SubscriberHelper.getUsersSubscriptionPreferences(fakeMessageEvent.getContext(), preferenceIndex))
				.thenReturn(aUserPreferenceEntityListWith(NotificationType.EMAIL));
		
		//No Assertions?
	}

	@Test
	public void testGetUsersSubscriptionPreference_WIthNullJsonFlag() throws DupsUserNotFoundException {
		IPreference preferenceWithNullJsonFlag = createEmptyUserPreference();
		setPreference(preferenceWithNullJsonFlag, false);
		when(dupsServiceMock.getUserPreferenceAll(FILTERED_BY_EVENT_TYPE_AND_CONTEXT))
			.thenReturn(preferenceListWith(preferenceWithNullJsonFlag));
		
		assertTrue(getUserPreferenceEntities_FromSubscriberHelper().isEmpty()); 
	}

	@Test  
	public void whatInTheWorldDoesThisTestDo() throws DupsUserNotFoundException {
		when(dupsServiceMock.getUserPreferenceAll(FILTERED_BY_EVENT_TYPE_AND_CONTEXT))
		.thenReturn(preferenceListWithNullJasonFlag());
		
		when(getUserPreferenceEntities_FromSubscriberHelper())
		.thenReturn(aUserPreferenceEntityListWith(NotificationType.EMAIL));
		
		//No assertions?
	}

}

