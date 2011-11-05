package com.qait.gold.testframework.fixtures.powersearch;

import com.qait.gold.testframework.fixtures.SystemEnvironmentFixture;

/**
 * This is the product (PowerSearch) specific SetUp fixture that sets the system
 * environment settings such as product Url, user/password etc.
 * @author QAIT
 */
public class PowerSearchSystemEnvironmentFixture extends SystemEnvironmentFixture {
    
    private static String productUrl = "/gps/start.do?prodId=IPS";
    private static String userGroupName = "gold17_deploy";
    private static String password = "qainfotech";

    /**
     * @return the productUrl
     */
    public static String getProductUrl() {
        return productUrl;
    }

    /**
     * @param aProductUrl the productUrl to set
     */
    public static void theProductUrlIs(String aProductUrl) {
        productUrl = aProductUrl;
    }

    /**
     * @return the userGroupName
     */
    public static String getUserGroupName() {
        return userGroupName;
    }

    /**
     * @param aUserGroupName the userGroupName to set
     */
    public static void theUserGroupNameIs(String aUserGroupName) {
        userGroupName = aUserGroupName;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void thePasswordIs(String aPassword) {
        password = aPassword;
    }

    
}//end class PowerSearchSystemEnvironmentFixture
