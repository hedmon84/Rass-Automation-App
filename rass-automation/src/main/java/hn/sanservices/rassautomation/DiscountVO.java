/*
 * San Services USA, 2021
 *
 * All rights reserved.
 * Copying of this software or parts of this software is a violation
 * of U.S. and international laws and will be prosecuted.
 *
 * Author(s): Edwin Villanueva ( Computer Engineer )
 *
 * Created on Wed Jan 20 3:03:21 PM CST 2021
 *
 * 01/20/2021 - Edwin Villanueva
 *      Initial Implementation
 */
package hn.sanservices.rassautomation;

/**
 *
 * @author Edwin Villanueva
 */
public class DiscountVO {

    private String discountCodeId;
    private String discountCode;
    private String description;

    public String getDiscountCodeId() {
        return discountCodeId;
    }

    public void setDiscountCodeId(String discountCodeId) {
        this.discountCodeId = discountCodeId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
