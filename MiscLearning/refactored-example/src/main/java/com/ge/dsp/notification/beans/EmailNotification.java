/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.notification.beans;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ge.dsp.notification.api.INotificationBean;

/**
 * EMail implementation of Notification API
 * 
 * @author 212307967
 */
public class EmailNotification extends MimeMailMessage
        implements INotificationBean
{
    /**
     * Creates an new EmailNotification object based on the given mimeMessageHelper
     * 
     * @param mimeMessageHelper - the mimeMessageHelper instance
     * 
     * @since 1.5
     * 
     */
    public EmailNotification(MimeMessageHelper mimeMessageHelper)
    {
        super(mimeMessageHelper);
    }

    /**
     * Creates an new EmailNotification object based on the given mimeMessage
     * 
     * @param mimeMessage - the mimeMessage instance
     * 
     * @since 1.5
     */
    public EmailNotification(MimeMessage mimeMessage)
    {
        super(mimeMessage);
    }

    /**
     * Creates an EmailNotification object based on a default mimeMessageHelper object created internally.
     * 
     * @throws MessagingException - thrown if there is an exception while creating the EmailNotification object
     * 
     * @since 1.5
     */
    public EmailNotification() throws MessagingException
    {
        super(createMimeMessageHelper(null, null, null, null,
                null, null , null , null , null, null ,
                null ));
    }

    /**
     * 
     * Creates an EmailNotification object based on a default mimeMessageHelper object created internally
     * 
     * @param to - an array of email addresses to whom  email must be sent
     * @param from - the email address from which the  email must be sent
     * @param subject - subject of the email
     * @param body - body of the email
     * @param contentType - content-type of the mail
     * 
     * @throws MessagingException - thrown if there is an exception while creating the EmailNotification object
     * 
     * @since 1.5
     */
    public EmailNotification(String[] to, String from, String subject, String body, String contentType)
            throws MessagingException
    {
        super(createMimeMessageHelper(to, null, null, from,
                null, subject, body, null, null, contentType,
                null ));
    }

    /**
     * @param to - an array of email addresses to whom email must be sent
     * @param cc - an array of email addresses to whom email is cced
     * @param bcc - an array of email addresses to whom  email is bcced
     * @param from - the email address from which the  email must be sent
     * @param subject - subject of the email
     * @param body - body of the email
     * @param contentType - content-type of the mail
     * @throws MessagingException - thrown if there is an exception while creating the EmailNotification object
     * 
     * @since 1.5
     * 
     */
    public EmailNotification(String[] to, String[] cc, String[] bcc, String from, String subject, String body,
            String contentType) throws MessagingException
    {
        super(createMimeMessageHelper(to, cc, bcc, from, null, subject, body, null, null, contentType, null));
    }

    /**
     * @param to - an array of email addresses to whom  email must be sent
     * @param cc - an array of email addresses to whom email is cced
     * @param bcc - an array of email addresses to whom email is bcced
     * @param from - the email address from which the  email must be sent
     * @param sentDate - the date on which the email is sent
     * @param subject - subject of the email
     * @param body - body of the email
     * @param attachmentName - name of the file that will be sent as an attachment
     * @param attachment - the attachment as a byte array
     * @param contentType - content-type of the mail
     * @param attachmentContentType - the attachment content type
     * @throws MessagingException - thrown if there is an exception while creating the EmailNotification object
     * 
     * @since 1.5
     * 
     */
    public EmailNotification(String[] to, String[] cc, String[] bcc, String from, Date sentDate, String subject,
            String body, String attachmentName, byte[] attachment, String contentType, String attachmentContentType)
            throws MessagingException
    {
        super(createMimeMessageHelper(to, cc, bcc, from, sentDate, subject, body, attachmentName, attachment,
                contentType, attachmentContentType));
    }

    private static MimeMessageHelper createMimeMessageHelper(String[] to, String[] cc, String[] bcc, String from,
            Date sentDate, String subject, String body, String attachmentName, byte[] attachment, String contentType,
            String attachmentContentType)
            throws MessagingException
    {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");//$NON-NLS-1$

        if ( isValueNotNullorEmpty(to) )
        {
            mimeMessageHelper.setTo(to);
        }
        if ( isValueNotNullorEmpty(cc) )
        {
            mimeMessageHelper.setCc(cc);
        }
        if ( isValueNotNullorEmpty(bcc) )
        {
            mimeMessageHelper.setBcc(bcc);
        }
        if ( isValueNotNullorEmpty(from) )
        {
            mimeMessageHelper.setFrom(from);
        }
        if ( sentDate!= null )
        {
            mimeMessageHelper.setSentDate(sentDate);
        }
        if ( isValueNotNullorEmpty(subject) )
        {
            mimeMessageHelper.setSubject(subject);
        }
        if ( isValueNotNullorEmpty(body) && isValueNotNullorEmpty(contentType) )
        {
            mimeMessageHelper.setText(body, contentType);
        }
        if ( isValueNotNullorEmpty(body) )
        {
            mimeMessageHelper.setText(body);
        }

        if ( (attachment != null) && (attachment.length > 0) )
        {
            String fileName = attachmentName == null ? "noFileName" //$NON-NLS-1$
                    : attachmentName;
            final InputStreamSource iSource = new ByteArrayResource(attachment);
            mimeMessageHelper.addAttachment(fileName, iSource, attachmentContentType);
        }

        return mimeMessageHelper;
    }

    private static boolean isValueNotNullorEmpty(String value)
    {
        if ( value != null && !value.isEmpty() )
        {
            return true;
        }
        return false;

    }

    private static boolean isValueNotNullorEmpty(String[] value)
    {
        if ( value != null && value.length > 0 && !value[0].isEmpty() )
        {
            return true;
        }
        return false;

    }

}
