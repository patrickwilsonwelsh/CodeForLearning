/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.dsi.config;

import java.io.Serializable;

/**
 * This bean wraps everything we need to know for each node. When received, this bean will be introspected and
 * be taken care of.
 */
public class ConfigBeanCommand
        implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ManagedNodeConfiguration nodeConfiguration     = null;
    private String                   targetKernelUuid;

    /**
     * command for this node to get all the configuration
     */
    public static final String       CONFIG_COMMAND_GET    = "command.get"; //$NON-NLS-1$

    /**
     * command for this node to update a particular property for a given bundle
     */
    public static final String       CONFIG_COMMAND_UPDATE = "command.update"; //$NON-NLS-1$

    /**
     * command for this node to delete a property, in the given bundle.
     */
    public static final String       CONFIG_COMMAND_DELETE = "command.delete"; //$NON-NLS-1$

    /**
     * message for whoever is interested in the properties set for this node
     */
    public static final String       CONFIG_MESSAGE_STATUS = "message.status"; //$NON-NLS-1$

    /**
     * message for whoever is interested in the error code for this node, in this case statusCode and errorDetails are important 
     */
    public static final String       CONFIG_MESSAGE_ERROR  = "message.error"; //$NON-NLS-1$

    private String                   statusCode;
    private String                   errorDetails;

    // node message matching one of the above
    private String                   nodeMessage;

    /**
     * @return returns specific node configuration
     */
    public ManagedNodeConfiguration getNodeConfiguration()
    {
        return this.nodeConfiguration;
    }

    /**
     * @param nodeConfiguration sets node configuration
     */
    public void setNodeConfiguration(ManagedNodeConfiguration nodeConfiguration)
    {
        this.nodeConfiguration = nodeConfiguration;
    }

    /**
     * @return gets the target Kernel UUID
     */
    public String getTargetKernelUuid()
    {
        return this.targetKernelUuid;
    }

    /**
     * @param targetKernelUuid sets the target Kernel UUID
     */
    public void setTargetKernelUuid(String targetKernelUuid)
    {
        this.targetKernelUuid = targetKernelUuid;
    }

    /**
     * @return Status code is returned 
     */
    public String getStatusCode()
    {
        return this.statusCode;
    }

    /**
     * @param statusCode Status code is set
     */
    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    /**
     * @return gets the error details
     */
    public String getErrorDetails()
    {
        return this.errorDetails;
    }

    /**
     * @param errorDetails sets the error details
     */
    public void setErrorDetails(String errorDetails)
    {
        this.errorDetails = errorDetails;
    }

    /**
     * @return gets the code message
     */
    public String getNodeMessage()
    {
        return this.nodeMessage;
    }

    /**
     * @param nodeMessage sets the node message
     */
    public void setNodeMessage(String nodeMessage)
    {
        this.nodeMessage = nodeMessage;
    }

    @Override
    public String toString()
    {
        return "ConfigBeanCommand{" + "nodeConfiguration=[" + this.nodeConfiguration + "], targetKernelUuid=[" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + this.targetKernelUuid + "], statusCode=[" + this.statusCode + "], errorDetails=[" + this.errorDetails  //$NON-NLS-1$//$NON-NLS-2$
                + "], nodeMessage=[" + this.nodeMessage + "]}"; //$NON-NLS-1$ //$NON-NLS-2$
    }
}
