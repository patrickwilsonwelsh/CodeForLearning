/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.spi;

import java.io.Serializable;

/**
 * A plain bean encapsulates the execution result from the call back methods.
 */
public class ExecutionStatus
        implements Serializable
{
    private static final long serialVersionUID = -7760909479052055995L;

    /**
     * Status code, either success or failed
     */
    public static enum STATUSCODE
    {
        /**
         * Status code - success
         */
        SUCCESS,
        /**
         * Status code - failed
         */
        FAILED
    }

    /**
     * Instruction for DSP kernel if status code is FAILED. Returning CONTINUE will let DSP continue the normal
     * process as if the STATUS returned is SUCCESS. Returning ABORT will instruct the kernel to UNINSTALL the bundle.
     * This instruction will be ignored if STATUSCODE is SUCCESS.
     */
    public static enum KERNEL_PROCESS_INSTRUCTION
    {
        /**
         * Instruction for DSP kernel if status code is FAILED. Returning CONTINUE will let DSP continue the normal
         * process as if the STATUS returned is SUCCESS.
         * This instruction will be ignored if STATUSCODE is SUCCESS.
         */
        CONTINUE,
        /**
         * Instruction for DSP kernel if status code is FAILED. Returning ABORT will instruct the kernel to UNINSTALL the bundle.
         * This instruction will be ignored if STATUSCODE is SUCCESS.
         */
        ABORT
    }

    private ExecutionStatus.STATUSCODE                 statusCode;
    private ExecutionStatus.KERNEL_PROCESS_INSTRUCTION kernelProcessInstruction;

    // status text in more details
    private String                                     statusText;

    /**
     * @param statusCode - status code of execution
     * @param kernelProcessInstruction - instruction for kernel processing
     * @param statusText - status
     */
    public ExecutionStatus(STATUSCODE statusCode, KERNEL_PROCESS_INSTRUCTION kernelProcessInstruction, String statusText)
    {
        this.statusCode = statusCode;
        this.kernelProcessInstruction = kernelProcessInstruction;
        this.statusText = statusText;
    }

    /**
     * Convenience method for creating a regular success status.
     * 
     * @param statusText any text
     * @return execution status object, with status code as SUCCESS and process instruction as CONTINUE
     */
    public static ExecutionStatus createSuccessStatus(String statusText)
    {
        return new ExecutionStatus(STATUSCODE.SUCCESS, KERNEL_PROCESS_INSTRUCTION.CONTINUE, statusText);
    }

    /**
     * Convenience method for creating a regular failed status with abort instruction code.
     * 
     * @param statusText any text
     * @return execution status object, with status code as FAILED and process instruction as ABORT
     */
    public static ExecutionStatus createFailedStatus(String statusText)
    {
        return new ExecutionStatus(STATUSCODE.FAILED, KERNEL_PROCESS_INSTRUCTION.ABORT, statusText);
    }

    /**
     * @return statusText
     */
    public String getStatusText()
    {
        return this.statusText;
    }

    /**
     * @param statusText set statusText
     */
    public void setStatusText(String statusText)
    {
        this.statusText = statusText;
    }

    /**
     * @return statusCode
     */
    public STATUSCODE getStatusCode()
    {
        return this.statusCode;
    }

    /**
     * @param statusCode - set statusCode
     */
    public void setStatusCode(STATUSCODE statusCode)
    {
        this.statusCode = statusCode;
    }

    /**
     * @return kernelProcessInstruction
     */
    public KERNEL_PROCESS_INSTRUCTION getKernelProcessInstruction()
    {
        return this.kernelProcessInstruction;
    }

    /**
     * @param kernelProcessInstruction - set kernelProcessInstruction
     */
    public void setKernelProcessInstruction(KERNEL_PROCESS_INSTRUCTION kernelProcessInstruction)
    {
        this.kernelProcessInstruction = kernelProcessInstruction;
    }
}
