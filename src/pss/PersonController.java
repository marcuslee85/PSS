/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nine_ball
 */
public class PersonController {

    static String connectionUrl = "jdbc:mysql://localhost:3306/lift?"
            + "zeroDateTimeBehavior=convertToNull"
            + "user=root&password=admin";

    /**
     * Adds new person to DB
     */
    public static boolean addPerson(int id, int type) {
        //placeholder
        return false;
    }
    
    /**
     * Adds new default floor for person in DB
     */
    public static boolean addDefaultFloor(int id, int type, int defaultFloor, int priority) {
        //placeholder
        return false;
    }
    
    /**
     * Adds new schedule for person in DB
     */
    public static boolean addSchedule(int id, int type, int scheduledFloor, int startTime, int endTime) {
        //placeholder
        return false;
    }
    
    /**
     * Adds new activity for person in DB
     */    
    public static boolean addActivity(Person p1) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            int userid = p1.userid;
            int type = p1.type;
            int timing = p1.timing;
            int arrivalFloor = p1.arrivalFloor;
            int destinationFloor = p1.destinationFloor;
            // Check for existing Person in DB
            String SQL = "SELECT PID, P_TYPE FROM person WHERE PID = '"
                    + userid + "' AND P_TYPE = '"
                    + type + "'";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            if (!rs.next()) {
                // If person does not exist in DB - Code to add Person here
            } else {
                // If person exists in DB - ???                
            }
            SQL = "INSERT INTO activity (arrive_datetime, start_floor, end_floor, PID) VALUES ('"
                    + timing + "','" + arrivalFloor + "'," + destinationFloor + "," + userid + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            //System.out.println("SQL Exception: " + e.toString());
            return false;
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                stmt = null;
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                con = null;
            }

        }
        return true;

    }

    /**
     * Queries DB for activities to populate in lift queue.
     */    
    public static ArrayList<Person> getActivities() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Person> activities = new ArrayList<Person>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // Check for existing Person in DB
            String SQL = "SELECT person.PID, person.P_TYPE, activity.arrive_datetime, activity.start_floor, activity.end_floor "
                        + "FROM activity "
                        + "LEFT JOIN person "
                        + "ON person.PID=activity.PID;";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                int userid = rs.getInt("PID");
                int type = rs.getInt("P_TYPE");
                int timing = rs.getInt("arrive_datetime");
                int arrivalFloor = rs.getInt("start_floor");
                int destinationFloor = rs.getInt("end_floor");
                
                activities.add(new Person(userid, type, timing, arrivalFloor, destinationFloor));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            //System.out.println("SQL Exception: " + e.toString());
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                stmt = null;
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                con = null;
            }

        }
        return activities;

    }    
}
