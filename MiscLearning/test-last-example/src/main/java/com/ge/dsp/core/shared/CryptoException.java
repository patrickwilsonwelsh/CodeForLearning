/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.shared;

import com.ge.dsp.core.util.LoggingUtil;

/**
 * An exception that captures errors found in DSP kernel.
 */
public class CryptoException extends RuntimeException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message. The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CryptoException()
    {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     * 
     * @param message the detail message. The detail message is saved for
     *            later retrieval by the {@link #getMessage()} method.
     */
    public CryptoException(String message)
    {
        super(LoggingUtil.getLocalHostName() + ":" + message); //$NON-NLS-1$
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.
     * <p>
     * Note that the detail message associated with {@code cause} is <i>not</i> automatically incorporated in this runtime exception's detail message.
     * 
     * @param message the detail message (which is saved for later retrieval
     *            by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt> value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     * 
     */
    public CryptoException(String message, Throwable cause)
    {
        super(LoggingUtil.getLocalHostName() + ":" + message, cause); //$NON-NLS-1$
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt> (which typically contains the class and detail message of <tt>cause</tt>). This
     * constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     * 
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt> value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     * 
     */
    public CryptoException(Throwable cause)
    {
        super(cause);
    }
}
