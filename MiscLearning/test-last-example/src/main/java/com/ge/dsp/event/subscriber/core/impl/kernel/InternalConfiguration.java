/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.event.subscriber.core.impl.kernel;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.ge.dsp.core.spi.IServiceManagerService;
import com.ge.dsp.dsi.config.ConfigurationException;
import com.ge.dsp.dsi.config.IConfigurationAware;
import com.ge.dsp.dsi.config.IConfigurationService;
import com.ge.dsp.dsi.config.IConfigurationServiceFactory;
import com.ge.dsp.dsi.dups.api.IDups;
import com.ge.dsp.event.subscriber.core.fakes.EmailDeliveryHandler;
import com.ge.dsp.event.subscriber.core.fakes.ISchedulerService;
import com.ge.dsp.event.subscriber.core.util.SubscriberResources;
import com.ge.dsp.notification.api.INotificationService;

/**
 * Notification Subscriber configuration settings which loaded from DSP-K container config directory.
 * 
 * @author 212304931
 * 
 */
// @Component
public class InternalConfiguration
        implements InitializingBean, IConfigurationAware
{

    // SLF4J LoggerFactory
    private static Logger                _logger   = LoggerFactory.getLogger(InternalConfiguration.class);
    private static SubscriberResources   _resource = SubscriberResources.getInstance();

    private IConfigurationServiceFactory configurationServiceFactory;
    private IServiceManagerService       dspServiceManagerService;
    private Map<String, Object>          config;
    private IDups                        dupsService;
    private ISchedulerService            schedulerService;
    private EmailDeliveryHandler         emailDeliveryHandler;
    private INotificationService         notificationService;
    private Boolean isDefaultMode = true;
    private static InternalConfiguration instance;


    /**
	 * @return the isDefaultMode
	 */
	public Boolean getIsDefaultMode() {
		return this.isDefaultMode;
	}

	/**
	 * @param isDefaultMode the isDefaultMode to set
	 */
	public void setIsDefaultMode(Boolean isDefaultMode) {
		this.isDefaultMode = isDefaultMode;
	}

	

    /**
     * default private constructor
     */
    public InternalConfiguration()
    {
        instance = this;
    }

    /**
     * Get InternalConfiguration singleton instance
     * 
     * @return InternalConfiguration
     */
    public static synchronized InternalConfiguration getInstance()
    {
        if ( instance == null )
        {
            instance = new InternalConfiguration();
        }
        return instance;
    }

    /**
     * @return the notificationService
     */
    public INotificationService getNotificationService()
    {
        return this.notificationService;
    }

    /**
     * @param notificationService the notificationService to set
     */
    public void setNotificationService(INotificationService notificationService)
    {
        this.notificationService = notificationService;
    }

    
    
    /**
     * @return Dups service
     */
    public IDups getDupsService()
    {
        return this.dupsService;
    }

    /**
     * @param dupsService the dup service
     */
    public void setDupsService(IDups dupsService)
    {
        this.dupsService = dupsService;
    }

    /**
     * @return EmailDeliveryHandler the default email handler
     */
    public EmailDeliveryHandler getEmailDeliveryHandler()
    {
        return null;
    }

    /**
     * @return ISchedulerService service
     */
    public ISchedulerService getSchedulerService()
    {
        return null;
    }

    /**
     * @param schedulerService the scheduler service
     */
    public void setSchedulerService(ISchedulerService schedulerService)
    {
        this.schedulerService = schedulerService;
    }

    /**
     * inject IServiceManagerService
     * 
     * @param dspServiceManagerService dsp service manager
     */
    public void setDspServiceManagerService(IServiceManagerService dspServiceManagerService)
    {
        this.dspServiceManagerService = dspServiceManagerService;
    }

    /**
     * inject IConfigurationServiceFactory
     * 
     * @param configurationServiceFactory spring amqp connection factory
     */
    public void setConfigurationServiceFactory(IConfigurationServiceFactory configurationServiceFactory)
    {
        this.configurationServiceFactory = configurationServiceFactory;
        configurationServiceFactory.registerConfigurationListener(this);
    }

    @Override
    public void configUpdated(Map<String, String> configMap)
            throws ConfigurationException
    {

        for (String key : configMap.keySet())
        {

            _logger.debug("dsp-notification-subscriber-core: Got notification for attribute [" + key //$NON-NLS-1$
                    + "], new value is [" + configMap.get(key) + "]."); //$NON-NLS-1$ //$NON-NLS-2$

            if ( Constants.MESSAGE_CONNECTION_PORT.equals(key) )
            {
                configMap.put(Constants.MESSAGE_CONNECTION_PORT, key);
            }
            else if ( Constants.MESSAGE_CONNECTION_HOST.equals(key) )
            {
                configMap.put(Constants.MESSAGE_CONNECTION_HOST, key);
            }
            else if ( Constants.MESSAGE_CONNECTION_PASSWORD.equals(key) )
            {
                configMap.put(Constants.MESSAGE_CONNECTION_PASSWORD, key);
            }
        }
    }

    @Override
    public void afterPropertiesSet()
            throws Exception
    {

        /*
         * load configurations
         */
        this.config = loadConfiguration();

        IConfigurationService configurationService = this.configurationServiceFactory.getConfigurationService();

        StringBuilder sb = new StringBuilder();
        for (String key : this.config.keySet())
        {
            sb.append("\t" + key + ":\t" + String.valueOf(this.config.get(key)) + "\n");  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
        }

        // logger.info("Default dsp-notification-subscriber-core configuration:\n" + sb.toString());

        // If default config items are not the same number of items from config file, update the config file.
        if ( configurationService.getConfiguration().size() == 0 )
        {
            for (String key : this.config.keySet())
            {
                configurationService.saveConfiguration(key, String.valueOf(this.config.get(key)));
            }
        }

    }

    /**
     * get specified config value
     * 
     * @param key map key values
     * @return the config value object
     */
    public Object getConfig(String key)
    {
        //_logger.debug("config = " + this);
        //_logger.debug("config = " + this.config);
        return this.config.get(key);
    }

    /**
     * load default configuration and the load settings from config file
     * 
     * @return
     */
    private Map<String, Object> loadConfiguration()
    {
        Map<String, Object> configMap = Constants.getDefaultConfig();

        IConfigurationService configurationService = this.configurationServiceFactory.getConfigurationService();

        Map<String, String> configFromFile = configurationService.getConfiguration();

        for (String key : configFromFile.keySet())
        {
            configMap.put(key, configFromFile.get(key));
            if ( Constants.MESSAGE_CONNECTION_HOST.equals(key) )
            {
            	// check if event service is running in Default mode
                configMap.put(Constants.MESSAGE_CONNECTION_HOST, configFromFile.get(key));
                if ( configFromFile.get(key) != null && (!configFromFile.get(key).isEmpty()) ){
                   this.isDefaultMode = false;
                   _logger.info("DEFAULT MODE IS SET TO FALSE"); //$NON-NLS-1$
                }                
            }
        }
        
       

        return configMap;
    }

    /*
     * public LoggerService getLoggerService() {
     * return loggerService;
     * }
     * public void setLoggerService(LoggerService loggerService) {
     * this.loggerService = loggerService;
     * }
     */

    /**
     * Message subscriber internal constants
     */
    @SuppressWarnings("nls")
    public static class Constants
    {
        // Enable or disable digest
        // public static String NOTIFICATION_DIGEST_ENABLED = "dsp.notification.subscriber.digestEnable";

        // Digest period time constants
        /** how often the digest job should run **/
        public static String               NOTIFICATION_DIGEST_PERIOD         = "dsp.event.subscriber.digestPeriod";

        /** how often the digest cleanup job should run **/
        public static String               NOTIFICATION_DIGEST_CLEANUP_PERIOD = "dsp.event.subscriber.digestCleanupPeriod";

        // AMQP message broker constants
        /** AMQP message connection host **/
        public static String               MESSAGE_CONNECTION_HOST            = "dsp.event.subscriber.MessageConnectionHost";

        /** AMQP connection host username **/
        public static String               MESSAGE_CONNECTION_USERNAME        = "dsp.event.subscriber.MessageConnectionUserName";

        /** AMQP connection host password **/
        public static String               MESSAGE_CONNECTION_PASSWORD        = "dsp.event.subscriber.MessageConnectionPassword";

        /** AMQP connection host port **/
        public static String               MESSAGE_CONNECTION_PORT            = "dsp.event.subscriber.MessageConnectionPort";

        public static String DIGEST_MAIL_SUBJECT  = "dsp.event.subscriber.DigestMailSubject";

        // public static String APP_CONTEXT = "dsp.notification.subscriber.appContext";

        private static Map<String, Object> configMap                          = new HashMap<String, Object>();

        /**
         * @return Map
         */
        public static Map<String, Object> getDefaultConfig()
        {

            // configMap.put(NOTIFICATION_DIGEST_ENABLED, "true");

            // set default digest period as 60 minutes
            configMap.put(NOTIFICATION_DIGEST_PERIOD, 60);
            configMap.put(NOTIFICATION_DIGEST_CLEANUP_PERIOD, 1440);

            // set default connection to localhost
           // configMap.put(MESSAGE_CONNECTION_HOST, "localhost");
            configMap.put(MESSAGE_CONNECTION_USERNAME, "guest");
            configMap.put(MESSAGE_CONNECTION_PASSWORD, "guest");
            configMap.put(MESSAGE_CONNECTION_PORT, "5672");
            
            // configMap.put(DEFAULT_QUEUES, "dsp.notification.test");

//            configMap.put(MAIL_CONNECTION_HOST, "mail.ge.com");
//            configMap.put(MAIL_CONNECTION_PORT, "");
//            configMap.put(MAIL_CONNECTION_USERNAME, "");
//            configMap.put(MAIL_CONNECTION_PASSWORD, "");
//            configMap.put(MAIL_FROM_ADDRESS, "event.service@ge.com");
//            configMap.put(MAIL_SUBJECT, "Event Notification Service");
            configMap.put(DIGEST_MAIL_SUBJECT, "Event Notification Digest Service");
//            configMap.put(MAIL_REPLY_TO, "no-reply@ge.com");
//            configMap.put(MAIL_RETRY_COUNT, "0");
//            configMap.put(MAIL_RETRY_DELAY_INTERVAL, "0");

            // configMap.put(APP_CONTEXT, DupsConstants.DUPS_QUERY_IGNORE);

            return configMap;
        }
    }

    /**
     * This method is to ensure the MessageEventEntity and UserEventEntity is created in DB at first.
     */
    /*protected static void checkAndCreatePersistTables()
    {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try
        {
            emf = DBUtil.getJpaEntityManagerFactory(InternalConfiguration.class, SubConstants.EVENT_PERSISTENCE_UNIT);

            em = emf.createEntityManager();

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            MessageEventEntity messageEntity = new MessageEventEntity();
            em.persist(messageEntity);

            UserEventEntity userEntity = new UserEventEntity();
            em.persist(userEntity);

            em.flush();
            tx.commit();
            em.clear();
            em.close();
        }
        catch (DBConfigurationException e)
        {
            _logger.error(_resource.getString("dbutil_error") + e); //$NON-NLS-1$
        }
       
    }*/

    /**
     * Internal initialization
     */
    public void init()
    {

        
    }

    /**
     * Thread to consume message events immediately from EventQueue instance
     * 
     * @author 212304931
     */
    class InMemorySubscriberListener
            implements Runnable
    {

        private EmailDeliveryHandler handler = new EmailDeliveryHandler();
        private Logger               logger  = LoggerFactory.getLogger(InMemorySubscriberListener.class);

        /*
         * (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run()
        {



        }

    }

    /*
     * (non-Javadoc)
     * @see com.ge.dsp.dsi.config.IConfigurationAware#validateConfig(java.util.Map)
     */
    @Override
    public void validateConfig(Map<String, String> configMap)
            throws ConfigurationException
    {
        // TODO Auto-generated method stub

    }

}
