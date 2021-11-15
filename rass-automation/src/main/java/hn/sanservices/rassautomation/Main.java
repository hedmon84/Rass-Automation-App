package hn.sanservices.rassautomation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hn.sanservices.rassautomation.integration.LogMessage;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleDriver;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Main {


    public static void main(String[] args) throws SQLException {

        DriverManager.registerDriver(new OracleDriver());
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@tordgolddb2.sandals.com:1522:DEVGOLD", "RASSJMS", "yQZ2Gu3A$2i$TmP")) {

//            if (conn != null) {
//                System.out.println("Connected to the database!");
//            } else {
//                System.out.println("Failed to make connection!");
           // dd
//            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
               // get availability
//            System.out.println(gson.toJson(getFullLoad("SMB", conn)));
            //rates
            System.out.println(gson.toJson(getFullLoad("BBO", "USA", "2023/07/1","2023/07/2",conn)));
//             promotions and Discounts
//            System.out.println(gson.toJson(getFullLoad(InfoType.PROMOTIONS,conn)));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    static protected List<AvailabilityVO> getFullLoad(String rstCode, Connection connection) throws SQLException {
        List<AvailabilityVO> list = new ArrayList<>();

        //preparar el statement
        try (CallableStatement cs = connection.prepareCall(InternalSQL.GET_AVAILABILITY)) {
            cs.setQueryTimeout(60);
            //preparar parametros que llevar el procedure
            cs.setString(1, rstCode);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                rs.setFetchDirection(ResultSet.FETCH_FORWARD);
                rs.setFetchSize(7000);

                while (rs.next()) {
                    String[] values = rs.getString(1).split("\\|");
                    AvailabilityVO availability = new AvailabilityVO();
                    hydrateAvailability(availability, values);
                    list.add(availability);
                }
            }
        }

        return list;
    }


    static protected List<RatesVO> getFullLoad(String rstCode, String rateStructure, String startDate, String endDate, Connection connection) throws SQLException {
        List<RatesVO> list = new ArrayList<>();


        try (CallableStatement cs = connection.prepareCall(
                InternalSQL.GET_RATES)) {
            cs.setQueryTimeout(60);
            cs.setString(1, rstCode);
            cs.setString(2, rateStructure);
            cs.setString(3, startDate);
            cs.setString(4, endDate);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(5)) {
                rs.setFetchDirection(ResultSet.FETCH_FORWARD);
                rs.setFetchSize(7000);

                while (rs.next()) {

                    String[]  values = rs.getString(1).split("\\|");
                    values[0] = "Rate_Excluding_Disc=";
                    RatesVO rates = new RatesVO();
                    hydrateRates(rates, values);

                    // select getStayLength
                    if(rates.getStayLength().equals("7")){
                        list.add(rates);
                   }


                }

            }
        }

        return list;
    }

    static String Calculate_Rate_Excluding_Disc(String AdultRate,String ChildRate, String Days  ){

        //calculate Rate_Excluding_Disc
        Float adults = Float.parseFloat(AdultRate);
        Float childrens =  Float.parseFloat(ChildRate);
        Float days =   Float.parseFloat(Days);

        return  Float.toString((adults + childrens)*days);




    }

    static protected List<Object> getFullLoad(InfoType infoType, Connection connection) throws SQLException {
        List<Object> list = new ArrayList<>();

        try (CallableStatement cs = connection.prepareCall(getProcedure(infoType))) {
            cs.setQueryTimeout(60);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                rs.setFetchDirection(ResultSet.FETCH_FORWARD);
                rs.setFetchSize(7000);

                while (rs.next()) {
                    String[] values = rs.getString(1).split("\\|");
                    hydrateSelector(list, values, infoType);
                }
            }
        }

        return list;
    }


    static  private String getProcedure(InfoType infoType) throws SQLException {
        switch (infoType) {
            case PROMOTIONS:
                return InternalSQL.GET_PROMOTIONS;
            case DISCOUNTS:
                return InternalSQL.GET_DISCOUNTS;
            default:
                throw new SQLException(
                        LogMessage.FLT1010.get());
        }
    }



    static private void hydrateAvailability(AvailabilityVO availability, String[] values) {


        availability.setResort(values[1].substring("resort=".length()));
        availability.setRoomCategory(values[2].substring("room_category=".length()));
        availability.setReservationDate(values[3].substring("reservation_date=".length()));
        availability.setAvailableRooms(values[4].substring("available_rooms=".length()));
    }



   static  private void hydrateRates(RatesVO rates, String[] values) {

//       calculate_Rate_Excluding_Disc(rates.getAdultRate(),rates.getChildRate(),rates.getStayLength());






        rates.setRateStructure(values[1].substring("rate_structure=".length()));
        rates.setResInsertSource(values[2].substring(("res_insert_source=").length()));
        rates.setResort(values[3].substring(("resort=").length()));
        rates.setRoomCategory(values[4].substring(("room_category=").length()));
        rates.setReserveBeginDate(values[5].substring(("reserve_begin_date=").length()));
        rates.setStayLength(values[6].substring(("length_of_stay=").length()));
        rates.setAdultRate(values[7].substring(("adult_rate=").length()));
        rates.setChildRate(values[8].substring(("child_rate=").length()));
        rates.setMaxAdult(values[9].substring(("max_adult=").length()));
        rates.setMaxChild(values[10].substring(("max_children=").length()));
        rates.setDepartureGateway(values[11].substring(("departure_gateway=").length()));
        rates.setZone(values[12].substring(("zone=").length()));
        rates.setState(values[13].substring(("state=").length()));
        rates.setCountry(values[14].substring(("country=").length()));
        rates.setSignetOnly(values[15].substring(("signet_only=").length()));
        rates.setApliedPromo(values[16].substring(("applied_promotions=").length()));
        rates.setAppliedDiscounts(values[17].substring(("applied_discounts=").length()));
        rates.setWholesalerOnly(values[18].substring(("wholesaler_only_yn=").length()));
        rates.setActive(values[19].substring(("active_yn=").length()));
       values[0] = "Rate_Excluding_Disc=" + Calculate_Rate_Excluding_Disc(rates.getAdultRate(),rates.getChildRate(),rates.getStayLength()) ;
       rates.setRate_Excluding_Disc(values[0].substring(("Rate_Excluding_Disc=").length()));

    }
    static private void hydrateSelector(List<Object> list, String[] values, InfoType infoType) throws SQLException {
        switch (infoType) {
            case PROMOTIONS:
                PromotionVO promotions = new PromotionVO();
                hydratePromotion(promotions, values);
                list.add(promotions);
                return;
            case DISCOUNTS:
                DiscountVO discounts = new DiscountVO();
                hydrateDiscount(discounts, values);
                list.add(discounts);
                return;
            default:
                throw new SQLException(
                        LogMessage.FLT1010.get());
        }
    }


    static private void hydratePromotion(PromotionVO promotion, String[] values) {


        promotion.setPromotionId(values[1].substring("promotion_id=".length()));
        promotion.setName(values[2].substring("promotion_name=".length()));
        promotion.setDescription(values[3].substring("description=".length()));
    }

    static private void hydrateDiscount(DiscountVO promotion, String[] values) {


        promotion.setDiscountCodeId(values[1].substring("discount_code_id=".length()));
        promotion.setDiscountCode(values[2].substring("discount_code=".length()));
        promotion.setDescription(values[3].substring("description=".length()));
    }


}
