/*
 * San Services USA, 2020
 *
 * All rights reserved.
 * Copying of this software or parts of this software is a violation
 * of U.S. and international laws and will be prosecuted.
 *
 * Author(s): Edwin Villanueva ( Computer Engineer )
 *
 * Created on Wed Dec 09 2:37:14 PM CST 2020
 *
 * 12/09/2020 - Edwin Villanueva
 *      Initial Implementation
 */
package hn.sanservices.rassautomation;

/**
 *
 * @author Edwin Villanueva
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
