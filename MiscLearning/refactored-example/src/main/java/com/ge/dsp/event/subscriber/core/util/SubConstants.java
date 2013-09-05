/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.event.subscriber.core.util;

/**
 * SubscriberConstant - This class provide common constants those can be shared
 * inside Notification service.
 */
@SuppressWarnings("nls")
public final class SubConstants
{

    /**
     * SQL Result set mapping for message event entity
     */
    public static final String SQL_MESSAGE_ENTITY_MAP      = "messageEventMap";
    
    /** Queue configuration constants **/
    public static final String DEFAULT_QUEUE_NAME          = "dsp.event.default.queue";

    /** Topic name  constants **/
    public static final String DEFAULT_TOPIC_NAME          = "dsp.default.topic";
    
    /** Dups user preference attribute name **/
    public static final String DUPS_USER_PREFERENCE        = "preference";

    /** Preference index concat symbol **/
    public static final String PREFERENCE_INDEX_CONCAT     = "/";

    /** The Scheduler service interface name **/
    public static final String SCHEDULER_INTERFACE_NAME    = "com.ge.dsp.dsi.scheduler.ISchedulerService";

    /** Dups service interface name **/
    public static final String DUPS_INTERFACE_NAME         = "com.ge.dsp.dsi.dups.api.IDups";

    /** Data persist unit name **/
    public static final String EVENT_PERSISTENCE_UNIT      = "EventPersistenceUnit";

    /**
     * JAVA JDO Query class
     */
    public static final String JAVAX_JDO_QUERY_SQL         = "javax.jdo.query.SQL";

    /** Digest schedule interval of 60 mins **/
    public static int          DIGEST_SCHEDULE_INTERVAL    = 60;

    /** SQL to select user digest events **/
    public static final String SELECT_USER_DIGEST_EVENTS   = "select * from messageevententity, userevententity where userevententity.eventseqid " + //$NON-NLS-1$
                                                                   "= messageevententity.eventseqid and userevententity.uuid = ? and userevententity.preferenceindex = ?"; //$NON-NLS-1$
    /** SQL to delete digested user events **/
    // TODO: add context
    public static final String DELETE_FROM_USEREVENTENTITY = "delete from userevententity where uuid = ? and preferenceindex = ?";

    /** SQL to delete digested message events **/
    public static final String DELETE_FROM_MESSAGEEVENTENTITY = "DELETE FROM messageevententity WHERE eventseqid not in (select eventseqid from userevententity)";


    /** The sql cleans up all messageevents have no reference to the userevents table **/
//    public static final String DIGEST_CLEANUP_SQL          = "delete from messageevententity me " + //$NON-NLS-1$
//                                                                   "where me.eventseqid not in " + //$NON-NLS-1$
//                                                                   "(select distinct ue.eventseqid from userevententity ue where ue.eventseqid != null)";                                       //$NON-NLS-1$

    /** The sql cleans up userevents table **/
    public static final String DIGEST_CLEANUP_USEREVENT        = "delete  from userevententity ue where ue.eventseqid is null";  
    
    /** Digest mail subject **/
    public static final String DIGEST_MAIL_SUBJECT             = "Event Notification Digest Service";
    
    // 
    
    /**
     * UTF-8 encoding
     */
    public static final String UTF8_ENCODING               = "UTF-8";

}
