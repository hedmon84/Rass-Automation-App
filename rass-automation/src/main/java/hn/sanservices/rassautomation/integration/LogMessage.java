/*
 * San Services USA, 2020
 *
 * All rights reserved.
 * Copying of this software or parts of this software is a violation
 * of U.S. and international laws and will be prosecuted.
 *
 * Author(s): Edwin Villanueva ( Computer Engineer )
 *
 * Created on Tue Dec 08 10:16:27 AM CST 2020
 *
 * 12/08/2020 - Edwin Villanueva
 *      Initial Implementation
 */
package hn.sanservices.rassautomation.integration;

/**
 *
 * @author Edwin Villanueva
 */
public enum LogMessage {

    /**
     * Generic Messages: Unexpected Error.
     */
    FLT1000("code=\"%s\" type=\"Unexpected Error\""),
    /**
     * Invalid Job Type.
     */
    FLT1010("Invalid Job Type"),
    /**
     * Subsystem.
     */
    FLT1020("code=\"%s\" type=\"Subsystem\" job=\"%s\" ms=\"%s\" rstCode=\"%s\" load=\"%s\"");

    private final String message;

    private LogMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
