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

            System.out.println(gson.toJson(getFullLoad("SMB", conn)));


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


    static private void hydrateAvailability(AvailabilityVO availability, String[] values) {


        availability.setResort(values[1].substring("resort=".length()));
        availability.setRoomCategory(values[2].substring("room_category=".length()));
        availability.setReservationDate(values[3].substring("reservation_date=".length()));
        availability.setAvailableRooms(values[4].substring("available_rooms=".length()));
    }




}
