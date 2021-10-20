package hn.sanservices.rassautomation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

            System.out.println(gson.toJson(getFullLoad("BBO", "USA", "2022/12/21","2022/12/28",conn)));


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
                    String[] values = rs.getString(1).split("\\|");
                    RatesVO rates = new RatesVO();
                    hydrateRates(rates, values);
                    list.add(rates);
                }
            }
        }

        return list;
    }




    static private void hydrateAvailability(AvailabilityVO availability, String[] values) {


        availability.setResort(values[1].substring("resort=".length()));
        availability.setRoomCategory(values[2].substring("room_category=".length()));
        availability.setReservationDate(values[3].substring("reservation_date=".length()));
        availability.setAvailableRooms(values[4].substring("available_rooms=".length()));
    }



   static  private void hydrateRates(RatesVO rates, String[] values) {

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
    }




}
