/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ge.dsp.commons.rest.MarshallUtils;
import com.ge.dsp.dsi.dups.api.IDups;
import com.ge.dsp.event.subscriber.beans.DigestPreference;
import com.ge.dsp.event.subscriber.beans.EventFilter;
import com.ge.dsp.event.subscriber.beans.SubscriptionPreference;
import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;
import com.ge.dsp.event.subscriber.core.fakes.DBConfigurationException;
import com.ge.dsp.event.subscriber.core.fakes.DBUtil;
import com.ge.dsp.event.subscriber.core.fakes.DupsUserNotFoundException;
import com.ge.dsp.event.subscriber.core.fakes.EntityManager;
import com.ge.dsp.event.subscriber.core.fakes.EntityManagerFactory;
import com.ge.dsp.event.subscriber.core.fakes.EntityTransaction;
import com.ge.dsp.event.subscriber.core.fakes.IPreference;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;
import com.ge.dsp.event.subscriber.core.util.SubscriberResources;

/**
 * This is the Subscriber helper class.
 * See methods comments for each method usages.
 * 
 * @author 212304931
 * 
 */
public class SubscriberHelper
{

    // SLF4J LoggerFactory
    private static Logger              _logger   = LoggerFactory.getLogger(SubscriberHelper.class);

    private static SubscriberResources _resource = SubscriberResources.getInstance();

    /**
     * This method will get a list of users preference from Dups
     * @param eventName the even name
     * @param evenType the event type
     * 
     * @param uuid the unique id stored in identity store
     * @param context the application context
     * @return List of users preferences
     */
    public static List<UserPreferenceEntity> getUsersSubscriptionPreferences(String eventName, String evenType, String context)
    {

        // Get dups service
        IDups dups = InternalConfiguration.getInstance().getDupsService();
        List<UserPreferenceEntity> users = new ArrayList<UserPreferenceEntity>();

       SubscriptionPreference subscriptionPreference = null;

        try
        {
            // retrieve list of users' preferences from dups
            List<IPreference> listPreference = dups.getUserPreferenceAll(context);

            if ( listPreference == null )
            {
                _logger.error(_resource.getString("null.parameter", "listPreference")); //$NON-NLS-1$ //$NON-NLS-2$
                return null;
            }

            // iterate through list of users preferences
            for (Object preference : listPreference)
            {
                String preferenceJson = ((IPreference) preference).getValue();
                
                //Check if the preference json string is the new form otherwise skip it
                //why we doing this? Because this is tide to Dups!!!
                if (preferenceJson == null || !preferenceJson.contains("eventFilter")) //$NON-NLS-1$
                {
                    continue;
                }
                
                subscriptionPreference = (SubscriptionPreference) MarshallUtils
                        .fromJson(new TypeReference<SubscriptionPreference>() {
                            /** nothing **/
                        }, preferenceJson);
        
                if ( subscriptionPreference == null || subscriptionPreference.getEventFilter() == null)
                {             
                    _logger.warn(_resource.getString("Undefined subscription preference: ") + preference); //$NON-NLS-1$                   
                    continue;
                }
                String userPreferenceIndex = ((IPreference) preference).getKey();
                
                /**
                 * need to check if userFilter matches the event based the following filter  permutation
                 * 
                 *  eventNmae.eventType.Context
                 *  *.eventType.Context
                 *  *.*.Context
                 *  eventNmae.*.Context
                 *  
                 */
                
                boolean filterMatches = matchFilter(subscriptionPreference.getEventFilter(), eventName, evenType, context);
                
				if (filterMatches) 
                {
                    UserPreferenceEntity entity = new UserPreferenceEntity();

                    entity.setUuid(subscriptionPreference.getUserIdentityID());
                    entity.setDupsContext(subscriptionPreference.getEventFilter().getContext());           

                    entity.setPreferenceIndex(userPreferenceIndex);
                    entity.setNotificationEnabled(subscriptionPreference.isNotificationEnable());
                    entity.setDestination(subscriptionPreference.getNotificationDestination());
                    //entity.setEmailList(subscriptionPreference.getNotificationDestination().getValue());
                    DigestPreference digestPreference = subscriptionPreference.getDigestPreference();

                    if ( digestPreference != null )
                    {
                        entity.setDigestEnabled(digestPreference.isDigestEnable());
                        entity.setDigestHour(digestPreference.getDigestTime());
                        if ( digestPreference.getDigestFrequency() != null )
                        {
                           entity.setFrequence(digestPreference.getDigestFrequency().toString());
                        }
                        entity.setMonthday(digestPreference.getDayOfMonth());
                        if ( digestPreference.getDayOfWeek() != null )
                        {
                            entity.setWeekdate(digestPreference.getDayOfWeek().getValue());
                        }
                   }
                   users.add(entity);
                }
            }
        }
        catch (DupsUserNotFoundException e)
        {
            _logger.error(" ", e);         //$NON-NLS-1$
        }

        return users;

    }
    
  
    /**
     * This method will get a list of users preference from Dups
     * 
     * @param uuid the unique id stored in identity store
     * @param dupsContext the application context
     * @param preferenceIndex preference index
     * @param wc where clause
     * @return List of users preferences
     */
    public static List<UserPreferenceEntity> getUsersSubscriptionPreferences(String dupsContext, String preferenceIndex)
    {

        // Get dups service
        IDups dups = InternalConfiguration.getInstance().getDupsService();
        List<UserPreferenceEntity> users = new ArrayList<UserPreferenceEntity>();

       SubscriptionPreference subscriptionPreference = null;

        try
        {
            // retrieve list of users' preferences from dups
            List<IPreference> listPreference = dups.getUserPreferenceAll(dupsContext);

            /*
             * @SuppressWarnings("unchecked")
             * // convert users's preferences json string into List of Hashamp
             * List<HashMap<String, String>> list = (List<HashMap<String, String>>) MarshallUtils.fromJson(
             * new TypeReference<List<HashMap<String, String>>>(){
             *//** nothing **/
            /*
             * },
             * userPreference.replaceAll("'\'", " ") //$NON-NLS-1$//$NON-NLS-2$
             * );
             */
            if ( listPreference == null )
            {
                _logger.error(_resource.getString("null.parameter", "listPreference")); //$NON-NLS-1$ //$NON-NLS-2$
                return null;
            }

            // iterate through list of users preferences
            for (Object preference : listPreference)
            {

            
                subscriptionPreference = (SubscriptionPreference) MarshallUtils
                        .fromJson(new TypeReference<SubscriptionPreference>() {
                            /** nothing **/
                        }, ((IPreference) preference).getValue());
        
                if ( subscriptionPreference == null || subscriptionPreference.getEventFilter() == null)
                {             
                    _logger.warn(_resource.getString("Undefined subscription preference: ") + preference); //$NON-NLS-1$                   
                    continue;
                }
                String userPreferenceIndex = null;
                
                
                userPreferenceIndex = subscriptionPreference.getEventFilter().generateRoutingKey();
                
                
                if (userPreferenceIndex.equals(preferenceIndex)) {
                    UserPreferenceEntity entity = new UserPreferenceEntity();

                    entity.setUuid(subscriptionPreference.getUserIdentityID());
                    entity.setDupsContext(subscriptionPreference.getEventFilter().getContext());           

                    entity.setPreferenceIndex(userPreferenceIndex);
                    entity.setNotificationEnabled(subscriptionPreference.isNotificationEnable());
                    entity.setDestination(subscriptionPreference.getNotificationDestination());
                    //entity.setEmailList(subscriptionPreference.getNotificationDestination().getValue());
                    DigestPreference digestPreference = subscriptionPreference.getDigestPreference();

                    if ( digestPreference != null )
                    {
                        entity.setDigestEnabled(digestPreference.isDigestEnable());
                        entity.setDigestHour(digestPreference.getDigestTime());
                        if ( digestPreference.getDigestFrequency() != null )
                        {
                           entity.setFrequence(digestPreference.getDigestFrequency().toString());
                        }
                        entity.setMonthday(digestPreference.getDayOfMonth());
                        if ( digestPreference.getDayOfWeek() != null )
                        {
                            entity.setWeekdate(digestPreference.getDayOfWeek().getValue());
                        }
                   }
                   users.add(entity);
                }
            }
        }
        catch (DupsUserNotFoundException e)
        {
            _logger.error(" ", e);         //$NON-NLS-1$
        }

        return users;

    }
    
    
    /**
     * check if userFilter matches the event based the following filter  permutation
     * 
     *  eventNmae.eventType.Context
     *  
     *  *.eventType.Context
     *  
     *  *.*.Context
     *  
     *  eventNmae.*.Context
     *  
     */
    private static boolean matchFilter(EventFilter userFilter, String eventName, String eventType, String context)
    {
        
        boolean contextmatch = false;
        boolean namematch = false;
        boolean typematch = false;
        
        String userContext = userFilter.getContext();
        if (userContext.equals(context))
        {
            contextmatch = true;
        }
        
        String userEventName = userFilter.getEventName();
        if(userEventName == null || userEventName.equals("null") || userEventName.isEmpty()) //$NON-NLS-1$
        {
            namematch = true;
        }
        else if (userEventName.equals(eventName) )
        {
            namematch = true;
           
        }
        
        String userEventType = userFilter.getEventType();
        if(userEventType == null || userEventType.equals("null") || userEventType.isEmpty()) //$NON-NLS-1$
        {
            typematch = true;
        }
        else if (userEventType.equals(eventType) )
        {
            typematch = true;
        }

        return (contextmatch && namematch && typematch);
        
    }

    /**
     * Persist digest event into database
     * 
     * @param entity
     *            The persist object
     * @param puName
     *            The persist unit name
     */
    public static void internalPersistence(Object entity, String puName)
    {

        EntityManagerFactory emf = null;
        EntityManager em = null;

//        try
//        {
//
//            emf = DBUtil.getJpaEntityManagerFactory(entity.getClass(), puName);
//            em = emf.createEntityManager();
//            EntityTransaction tx = em.getTransaction();
//            tx.begin();
//            em.persist(entity);
//            em.flush();
//            tx.commit();
//            em.close();
//
//        }
//        catch (DBConfigurationException e)
//        {
//            _logger.error(_resource.getString("dbutil_error") + e); //$NON-NLS-1$
//        }

    }
   
    
}
