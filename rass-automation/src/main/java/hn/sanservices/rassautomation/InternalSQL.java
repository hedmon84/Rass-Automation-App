package hn.sanservices.rassautomation;

public class InternalSQL {

    /**
     * Availability ( Full Load by Resort Code )
     */
    public static String GET_AVAILABILITY = "{call RASS.JMS_QUEUE_PKG.GET_AVAILABILITY(?, ?)}";

    /**
     * Rates ( Full Load by Resort Code )
     */
    public static String GET_RATES = "{call RASS.JMS_QUEUE_PKG.GET_RATES(?, ?, ?, ?, ?)}";

    /**
     * Promotions ( Full Load )
     */
    public static String GET_PROMOTIONS = "{call RASS.JMS_QUEUE_PKG.GET_PROMOTIONS(?)}";

    /**
     * Discounts ( Full Load )
     */
    public static String GET_DISCOUNTS = "{call RASS.JMS_QUEUE_PKG.GET_DISCOUNTS(?)}";

    /**
     * Commissions ( Full Load by Resort Code )
     */
    public static String GET_COMMISSIONS = "{call RASS.JMS_QUEUE_PKG.GET_COMMISSION(?, ?)}";
}
