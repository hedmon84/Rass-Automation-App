/*
 * San Services USA, 2021
 *
 * All rights reserved.
 * Copying of this software or parts of this software is a violation
 * of U.S. and international laws and will be prosecuted.
 *
 * Author(s): Hedmon Lopez ( Qa Computer Engineer )
 *
 * Created on Wed Oct 20 2:37:14 PM CST 2021
 *
 * 10/20/2021 - Hedmon Lopez
 *      Initial Implementation
 */
package hn.sanservices.rassautomation;

/**
 *
 * @author Hedmon Lopez
 */
public class PromotionVO {

    private String promotionId;
    private String name;
    private String description;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
