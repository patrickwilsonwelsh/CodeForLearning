/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 212304931
 * @since 1.5
 */
@XmlRootElement
public enum SubscriptionStatus
{
    /**
     * subscription not found
     */
    NOTFOUND,
    /**
     * call success
     */
    SUCCESS,
    /**
     * call failed
     */
    FAILED;

    /**
     * @return lower case name
     */
    String getStringName()
    {
        return name().toLowerCase();
    }
}
