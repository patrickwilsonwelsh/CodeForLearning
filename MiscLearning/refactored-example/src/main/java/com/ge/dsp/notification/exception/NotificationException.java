/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
 
package com.ge.dsp.notification.exception;

/**
 * 
 * @author 212307967
 */
public class NotificationException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message. The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     * 
     * @since 1.5
     */
    public NotificationException()
    {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     * 
     * @param message the detail message. The detail message is saved for
     *            later retrieval by the {@link #getMessage()} method.
     *
     * @since 1.5
     */
    public NotificationException(String message)
    {
        super(message);
    }
    
    
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     * @param errorCode the error code for the message
     * 
     * @param message the detail message. The detail message is saved for
     *            later retrieval by the {@link #getMessage()} method.
     *
     * @since 1.5
     */
    public NotificationException(String errorCode, String message)
    {
        super(message);
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
     * @since 1.5
     */
    public NotificationException(String message, Throwable cause)
    {
        super(message, cause);
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
     * @since 1.5
     */
    public NotificationException(Throwable cause)
    {
        super(cause);
    }
}
