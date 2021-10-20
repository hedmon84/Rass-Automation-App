/*
 * San Services USA, 2020
 *
 * All rights reserved.
 * Copying of this software or parts of this software is a violation
 * of U.S. and international laws and will be prosecuted.
 *
 * Author(s): Edwin Villanueva ( QA Computer Engineer )
 *
 * Created on Wed October 20 2:37:14 PM CST 2021
 *
 * 10/20/2021 - Hedmon Lopez
 *      Initial Implementation
 */
package hn.sanservices.rassautomation;

/**
 *
 * @author Hedmon Lopez
 */
public class AvailabilityVO {

    private String resort;
    private String roomCategory;
    private String reservationDate;
    private String availableRooms;

    public String getResort() {
        return resort;
    }

    public void setResort(String resort) {
        this.resort = resort;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(String availableRooms) {
        this.availableRooms = availableRooms;
    }

}
