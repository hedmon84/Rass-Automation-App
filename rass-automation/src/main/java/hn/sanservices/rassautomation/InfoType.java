/*
 * San Services USA, 2020
 *
 * All rights reserved.
 * Copying of this software or parts of this software is a violation
 * of U.S. and international laws and will be prosecuted.
 *
 * Author(s): Edwin Villanueva ( Computer Engineer )
 *
 * Created on Tue Dec 08 3:26:48 PM CST 2020
 *
 * 12/08/2020 - Edwin Villanueva
 *      Initial Implementation
 */
package hn.sanservices.rassautomation;

/**
 *
 * @author Edwin Villanueva
 */
public enum InfoType {

    /**
     * Availability Call.
     */
    AVAILABILITY("Availability"),
    /**
     * Rates Call.
     */
    RATES("Rates"),
    /**
     * Promotions Call.
     */
    PROMOTIONS("Promotions"),
    /**
     * Discounts Call.
     */
    DISCOUNTS("Discounts");

    private final String message;

    private InfoType(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
