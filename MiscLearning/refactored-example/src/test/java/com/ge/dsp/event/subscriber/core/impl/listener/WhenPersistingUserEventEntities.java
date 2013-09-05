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
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Calendar;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.BeanUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ge.dsp.event.subscriber.core.fakes.DBConfigurationException;
import com.ge.dsp.event.subscriber.core.fakes.DBUtil;
import com.ge.dsp.event.subscriber.core.fakes.EntityManager;
import com.ge.dsp.event.subscriber.core.fakes.EntityManagerFactory;
import com.ge.dsp.event.subscriber.core.fakes.EntityTransaction;
import com.ge.dsp.event.subscriber.core.fakes.MessageEventEntity;
import com.ge.dsp.event.subscriber.core.fakes.UserEventEntity;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.util.SubConstants;

@PrepareForTest({DBUtil.class, SubscriberHelper.class})
@SuppressWarnings({ "javadoc", "nls" })
public class WhenPersistingUserEventEntities<fakeEventCallBack> extends SubscriberListenerTestBase  {

	private MessageEventEntity evententity;

	@BeforeTest
	public void setUp() {
		fakeMessageEvent = createMessageEvent();
		
		EntityManagerFactory emf = mock(EntityManagerFactory.class);
		EntityManager em = mock(EntityManager.class);
		EntityTransaction tx = mock(EntityTransaction.class);
		when(em.getTransaction()).thenReturn(tx);
		
		mockStatic(DBUtil.class);
		when(DBUtil.getJpaEntityManagerFactory(UserEventEntity.class, SubConstants.EVENT_PERSISTENCE_UNIT)).thenReturn(emf);
		when(emf.createEntityManager()).thenReturn(em);
		
		evententity = new MessageEventEntity();
		evententity.setTimestamp(Calendar.getInstance().getTime());
		BeanUtils.copyProperties(fakeMessageEvent, evententity);
	}

	@Test //Ugh. Bust this into multiple tests
	public void testInternalPersistence() throws DBConfigurationException {
		String preferenceIndex = "";
		if (fakeMessageEvent.getEventName() == null)
			preferenceIndex = preferenceIndex + "*";
		else
			preferenceIndex = preferenceIndex + fakeMessageEvent.getEventName();

		if (fakeMessageEvent.getEventType() == null)
			preferenceIndex = preferenceIndex + ".*";
		else
			preferenceIndex = preferenceIndex + "."
					+ fakeMessageEvent.getEventType();

		preferenceIndex = preferenceIndex + "." + fakeMessageEvent.getContext();

		// persist messageEvent and userEvent entities
		SubscriberHelper.internalPersistence(createUserEventEntity(evententity, preferenceIndex), SubConstants.EVENT_PERSISTENCE_UNIT);
		
		//No Assertions?
	}

	private UserEventEntity createUserEventEntity(
			MessageEventEntity evententity, String preferenceIndex) {
		// create user entity object to persist into db
		UserEventEntity userevent = new UserEventEntity(
				"http://User.A@test.dsp.ge.com",
				FILTERED_BY_EVENT_TYPE_AND_CONTEXT, preferenceIndex);
		userevent.setEvent(evententity);
		userevent.setTimestamp(Calendar.getInstance().getTime());
		return userevent;
	}
}

