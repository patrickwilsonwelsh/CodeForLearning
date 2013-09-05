/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.dsi.scheduler;

/**
 * Cron Expression Util class
 * quartz cron expression reference
 * http://www.quartz-scheduler.org/documentation/quartz-1.x/tutorials/crontrigger
 * User: 212304931
 * Date: 10/25/12
 * Time: 5:51 PM
 */
public final class CronExpressionUtil
{

    /**
     * This method builds cronExpression for daily schedule
     *
     * @param hour range: 0-23
     * @param minute range: 0-59
     * @return cron expression
     *         0 45 12 * * ? equals 12:45PM every day
     * @throws CronExpressionException exception handling
     */
    public static String buildDailyExpression(int hour, int minute)
            throws CronExpressionException
    {
        validate(hour, minute);
        return String.format("0 %d %d ? * *", minute, hour); //$NON-NLS-1$
    }

    /**
     * This method builds cronExpression for weekly schedule
     *
     * @param hour range: 0-23
     * @param minute range: 0-59
     * @param dayOfWeek range: MON, TUE, WED, THU, FRI, SAT, SUN
     * @return cron expression
     * @throws CronExpressionException exception handling
     *             0 45 12 ? * MON * equals 12:45PM every Monday
     */
    public static String buildWeeklyExpression(DayOfWeek dayOfWeek, int hour, int minute)
            throws CronExpressionException
    {

        validate(hour, minute);
        return String.format("0 %d %d ? * %d", minute, hour, dayOfWeek.getValue()); //$NON-NLS-1$
    }

    /**
     * This method builds a cronExpression for monthly schedule
     * If the day does not exist for a specified month, scheduler will run the last of the month
     *
     * @param hour range: 0-12
     * @param minute range: 0-59
     * @param dayOfMonth range: 1-31
     * @return cron expression
     * @throws CronExpressionException exception handlin
     *             0 45 12 5 1/1 ? * equals 12:45PM 5th day every month
     */
    public static String buildMonthlyExpression(int dayOfMonth, int hour, int minute)
            throws CronExpressionException
    {

        validate(hour, minute);
        validateDayOfMonth(dayOfMonth);
        return String.format("0 %d %d %d * ?", minute, hour, dayOfMonth); //$NON-NLS-1$
    }


    /**
     * The Enum Day Of Week.
     */
    public static enum DayOfWeek
    {
        /** Sunday */
        SUN(1),
        /** Monday */
        MON(2),
        /** Tuesday */
        TUE(3),
        /** Wednesday */
        WED(4),
        /** Thursday */
        THU(5),
        /** Friday */
        FRI(6),
        /** Saturday */
        SAT(7);
        /** Week day (not supported) */
        // WEEK_DAYS = "MON-FRI"

        private int value;

        private DayOfWeek(int value){
           this.value = value;
        }

        /**
         * get the integer value
         * @return int
         */
        public int getValue(){
            return this.value;
        }
    }


    /** Minute out of range exception */
    public final static String MINUTE_OUT_OF_RANGE      = "Expected minute range: 0 - 59";             //$NON-NLS-1$

    /** Hour out of range exception */
    public final static String HOUR_OUT_OF_RANGE        = "Expected hour range: 0 - 23";               //$NON-NLS-1$

    /** DATE out of range exception */
    public final static String DATE_OUT_OF_RANGE        = "Expected day of month range: 1 - 31";       //$NON-NLS-1$

    /** Minutes must be positive */
    public final static String POSITIVE_MINUTE_REQUIRED = "Minute(s) must be positive integer number."; //$NON-NLS-1$

    /**
     * Checking if hour and minute are within the expected range
     *
     * @param hour
     * @param minute
     */
    private static void validate(int hour, int minute)
            throws CronExpressionException
    {

        if ( hour < 0 || hour > 23 )
        {
            throw new CronExpressionException(HOUR_OUT_OF_RANGE);
        }

        if ( minute < 0 || minute > 59 )
        {
            throw new CronExpressionException(MINUTE_OUT_OF_RANGE);
        }

    }

    /**
     * Check if month day is within expected range
     *
     * @param monthday
     */
    private static void validateDayOfMonth(int monthday)
            throws CronExpressionException
    {

        if ( monthday < 1 || monthday > 31 )
        {
            throw new CronExpressionException(DATE_OUT_OF_RANGE);
        }

    }
}
