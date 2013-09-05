/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.dsi.dups.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ge.dsp.commons.rest.PropertiesWrapper;
import com.ge.dsp.dsi.notification.exception.DupsException;
import com.ge.dsp.event.subscriber.core.fakes.DupsUserNotFoundException;
import com.ge.dsp.event.subscriber.core.fakes.IPreference;

/**
 * Interface for DSP User Preference Service
 *
 * DSP User Preference Service (DUPS) stores, manages and provides the
 * information about users Preference in the scope of users accessing DSP
 * sub-system. This service maintains its own light weight data store to store
 * the preferences information about each user.
 *
 * The information stored and managed could be as simple as some flags such as
 * parameters enable/disable details or information such as list of attributes
 * for any specific preference associated with the user. The preferences are
 * bound with unique user id which is unique system wide.
 */
@Path("dups")
public interface IDups
{
    /**
     * Creates a specific User Preference in the DUPS data store.
     *
     * @param uuid unique uid of the user as maintained by the identity service
     * @param dupsContext an application context which is unique for a given application
     * @param key A String field which can be used by the caller to index the
     *            properties object.
     *            The combination of uuid, dupsContext and key becomes
     *            unique for DUPS.
     *            This field can be null. If null, the key will be set to <code>DUPS_INTERNAL_INDEX</code>
     * @param value A String value for the key. This could be a serialized JSON/XML but the
     *            conversion is the callers responsibility.
     * @restUrl POST Method<br>
     *          <pre>http://<hostname>:9090/service/dupsCore/dups/createUserPreference</pre>
     *          <pre>post arguments: uuid=<>&dupsContext=<>&key=<>&value=<></pre>
     * @throws DupsUserNotFoundException returned if the uuid is not found in the identity service.
     * @throws DupsException return for general unexpected errors (like persistence problems).
     * @return boolean (true=success, false=failure)
     * Note: for failure on a REST call, the content returned will be empty. On success will be a string "true".
     */
    @POST
    @Path("createUserPreference")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean createUserPreference(@FormParam("uuid") String uuid,
                                        @FormParam("dupsContext") String dupsContext,
                                        @FormParam("key") String key,
                                        @FormParam("value") String value)
            throws DupsUserNotFoundException, DupsException;

    /**
     * Creates a specific User Preference in the DUPS data store.
     *
     * @param uuid unique uid of the user as maintained by the identity service
     * @param dupsContext an application context which is unique for a given application
     * @param props Key/Value properties to store as preferences. For example:
     * XML:  <pre>{@code
     * <PropertyWrapper>
     *     <Properties>
     *         <Property key="color" value="blue"/>
     *         <Property key="team" value="Cal"/>
     *         <Property key="phone" value="555-1212"/>
     *     </Properties>
     * </PropertyWrapper>
     * } </pre>
     *
     * @restUrl POST Method<br>
     *          <pre>http://<hostname>:9090/service/dupsCore/dups/createUserPreferences</pre>
     *          <pre>post arguments: uuid=<>&dupsContext=<>&key=<>&props=<></pre>
     *          <pre>"props" is a XML representation of the prop key/values</pre>
     * @throws DupsUserNotFoundException returned if the uuid is not found in the identity service.
     * @throws DupsException return for general unexpected errors (like persistence problems).
     * @return boolean (true=success, false=failure)
     * Note: for failure on a REST call, the content returned will be empty. On success will be a string "true".
     */
    @POST
    @Path("createUserPreferences")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean createUserPreferences(@FormParam("uuid") String uuid,
                                         @FormParam("dupsContext") String dupsContext,
                                         @FormParam("props") PropertiesWrapper props)
            throws DupsUserNotFoundException, DupsException;

    /**
     * Retrieves a specific preference value from the DUPS store.
     *
     * @param uuid unique uid of the user as maintained by the identity service
     *            Pass DupsConstants.DUPS_QUERY_IGNORE for wild card matching.
     * @param dupsContext an application context which is unique for a given application
     *            Pass DupsConstants.DUPS_QUERY_IGNORE for wild card matching.
     * @param key A String field which can be used by the caller to index the
     *            properties object.
     *            The combination of uuid, dupsContext and key becomes
     *            unique for DUPS.
     *            This field can be null. If null, the key will be set to <code>DUPS_INTERNAL_INDEX</code>
     *            Pass DupsConstants.DUPS_QUERY_IGNORE for wild card matching.
     * @restUrl GET Method
     *            <pre>http://<hostname>:9090/service/dupsCore/dups/getUserPreference
     *            ?uuid=<>&dupsContext=<>&key=<></pre>
     * @throws DupsUserNotFoundException returned if the uuid is not found in the identity service.
     * @return A String value for the key. This could be a serialized JSON/XML but the
     *            conversion is the callers responsibility.
     *
     *
     *         uuid dupsContext key     possible/not-possible combination
     *         ----------------------------------------------------------------------
     *         0        0       0       No
     *         0        0       1       Yes
     *         0        1       0       Yes
     *         0        1       1       Yes
     *         1        0       0       Yes
     *         1        0       1       Yes
     *         1        1       0       Yes
     *         1        1       1       Yes
     *
     *         1 = field passed in the query
     *         0 = field not-passed in the query (DupsConstants.DUPS_QUERY_IGNORE)
     *
     *         example:
     *         001 = query using key only, DUPS should return List of records that have this key for all users
     *         and any context.
     * <p>
     * Examnple JSON: <pre>
     * [{"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT1"}]
     * </pre>
     * Note: for failure on a REST call, the content returned will be empty. On success will be a JSON string with at least the empty "[]".
     */
    @GET
    @Path("getUserPreference")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<IPreference> getUserPreference(@QueryParam("uuid") String uuid,
                                               @QueryParam("dupsContext") String dupsContext,
                                               @QueryParam("key") String key)
            throws DupsUserNotFoundException;

    /**
     * Retrieves all the Preference parameters for a given context from the dups
     * store.
     *
     * @param dupsContext
     *            an application context which is unique for a given application
     * @restUrl GET Method
     *          <pre>http://<hostname>:9090/service/dupsCore/dups/getUserPreferenceAll
     *          ?dupsContext<></pre>
     * @throws DupsUserNotFoundException returned if the dupsContext is bad.
     * @return JSON String
     * <p>
     * Examnple JSON: <pre>
     *  [{"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"false\", \"yetAnotherNotificationEnabled\" : \"true\" }","key":"fleet$100$ALERT0"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT1"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT4"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT8"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT5"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT6"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT9"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT2"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT7"},
     *  {"uuid":"http://User.A@test.dsp.ge.com","dupsContext":"DupsConsumer",
     *      "value":"{ \"notificationEnabled\" : \"true\", \"yetAnotherNotificationEnabled\" : \"false\" }","key":"fleet$100$ALERT3"}]
     * </pre>
     * Note: for failure on a REST call, the content returned will be empty. On success will be a JSON string with at least the empty "[]".
     */
    @GET
    @Path("getUserPreferenceAll")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<IPreference> getUserPreferenceAll(@QueryParam("dupsContext") String dupsContext)
            throws DupsUserNotFoundException;

    /**
     * Retrieves all the Preference parameters for a given context from the dups
     * store.
     *
     * @param uuid unique uid of the user as maintained by the identity service
     * @param dupsContext an application context which is unique for a given application
     * @restUrl GET Method
     *          <pre>http://<hostname>:9090/service/dupsCore/dups/getUserPreferences
     *          ?uuid=<>&dupsContext=<></pre>
     * @throws DupsUserNotFoundException returned if the uuid is not found in the identity service.
     * @return A key-value properties object for all the preferences under this context. The values of
     *         each could be a serialized JSON/XML but the conversion is the callers responsibility.
     * Exmaple XML:  <pre>{@code
     * <PropertyWrapper>
     *     <Properties>
     *         <Property key="color" value="blue"/>
     *         <Property key="team" value="Cal"/>
     *         <Property key="phone" value="555-1212"/>
     *     </Properties>
     * </PropertyWrapper>
     * } </pre>
     * JSON: <pre>
     *  {"properties":{"color":"blue","team":"Cal","phone":"555-1212"}}
     * </pre>
     * Note: for failure on a REST call, the content returned will be empty. On success will be a JSON string with at least the empty "[]" or
     * an XML with no elements (<pre><pre>{@code<PropertyWrapper><Properties/></PropertyWrapper>}</pre>).
     */
    @GET
    @Path("getUserPreferences")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public PropertiesWrapper getUserPreferences(@QueryParam("uuid") String uuid,
                                                @QueryParam("dupsContext") String dupsContext)
            throws DupsUserNotFoundException;

    /**
     * Update a specific User Preference in the DUPS data store. This will only "update" if
     * the value exists. It will NOT create the value.
     * @see createUserPreference
     *
     * @param uuid unique uid of the user as maintained by the identity service
     * @param dupsContext an application context which is unique for a given application
     * @param key A String field which can be used by the caller to index the
     *            properties object.
     *            The combination of uuid, dupsContext and key becomes
     *            unique for DUPS.
     *            This field can be null. If null, the value will not be included
     *            in the <code>getProperties()</code> method.
     * @param value A String value for the key. This could be a serialized JSON/XML but the
     *            conversion is the callers responsibility.
     * @restUrl POST Method<br>
     *          <pre>http://<hostname>:9090/service/dupsCore/dups/updateUserPreference</pre>
     *          <pre>post arguments: uuid=<>&dupsContext=<>&key=<>&value=<></pre>
     * @throws DupsUserNotFoundException returned if the uuid is not found in the identity service.
     * @throws DupsException return for general unexpected errors (like persistence problems).
     * @return boolean (true=success, false=failure)
     * Note: for failure on a REST call, the content returned will be empty. On success will be a string "true".
     */
    @POST
    @Path("updateUserPreference")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean updateUserPreference(@FormParam("uuid") String uuid,
                                        @FormParam("dupsContext") String dupsContext,
                                        @FormParam("key") String key,
                                        @FormParam("value") String value)
            throws DupsUserNotFoundException, DupsException;

    /**
      * Update a specific User Preference in the DUPS data store. This will only "update" if
     * the value exists. It will NOT create the value.
     * @see createUserPreference
     *
     * @param uuid unique uid of the user as maintained by the identity service
     * @param dupsContext an application context which is unique for a given application
     * @param props Key/Value properties to store as preferences. For example:
     * XML:  <pre>{@code
     * <PropertyWrapper>
     *     <Properties>
     *         <Property key="color" value="blue"/>
     *         <Property key="team" value="Cal"/>
     *         <Property key="phone" value="555-1212"/>
     *     </Properties>
     * </PropertyWrapper>
     * } </pre>
     *
     * @restUrl POST Method<br>
     *          <pre>http://<hostname>:9090/service/dupsCore/dups/updateUserPreference</pre>
     *          <pre>post arguments: uuid=<>&dupsContext=<>&key=<>&value=<></pre>
     *          <pre>"props" is a XML representation of the prop key/values</pre>
     * @throws DupsUserNotFoundException returned if the uuid is not found in the identity service.
     * @throws DupsException return for general unexpected errors (like persistence problems).
     * @return boolean (true=success, false=failure)
     * Note: for failure on a REST call, the content returned will be empty. On success will be a string "true".
     */
    @POST
    @Path("updateUserPreferences")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean updateUserPreferences(@FormParam("uuid") String uuid,
                                         @FormParam("dupsContext") String dupsContext,
                                         @FormParam("props") PropertiesWrapper props)
            throws DupsUserNotFoundException, DupsException;

    /**
     * Interacts with Identity Service and validates the user in Identity
     * Service Data store
     *
     * @param uuid
     *            unique uid of the user as maintained by the identity service
     * @restUrl GET Method
     *          http://<hostname>:9090/service/dupsCore/dups/validateUserInIdentityStore
     *          ?uuid=<>
     * @return true=valid user; false=invalid user
     */
    @GET
    @Path("validateUserInIdentityStore")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean validateUserInIdentityStore(@QueryParam("uuid") String uuid);
}
