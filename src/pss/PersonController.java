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
     * Adds new person to DB (if person does not already exist
     */
    public static boolean addPerson(int id, int type) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // Check for existing Person in DB
            String SQL = "SELECT PID, P_TYPE FROM person WHERE PID = "
                    + id + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            if (!rs.next()) {
                // If person does not exist in DB - add person, return success.
                
                SQL = "INSERT INTO person (PID, P_TYPE) "
                    + "VALUES (" + id + "," + type + ")";
                stmt = con.createStatement();
                stmt.executeUpdate(SQL);
                return true;
            } else {
                // If person already exists in DB - return error.
                return false;
            }

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
     * Adds new default floors for persons in DB (WIP)
     * TO CONFIRM: How is Priority incremented when new default floor is added?
     * And what happens with deleted floors?
     */
    public static boolean addDefaultFloors(ArrayList<DefaultFloor> defaultFloors) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // For each default floor to be added
            for (DefaultFloor df : defaultFloors) {
                int id = df.userid;
                int floor = df.floor;
                // Check for existing default floor for person in DB
                // Assume that check for person's existence was done elsewhere
                String SQL = "SELECT * FROM default_floor "
                        + "WHERE PID = " + id
                        + "AND default_floor_id = " + floor + "";

                stmt = con.createStatement();
                rs = stmt.executeQuery(SQL);

                if (!rs.next()) {
                    // If this default floor does not exist for said person - add into DB.

                    //Placeholder
                } else {
                    // If this default floor exists for said person - ???
                }
            }
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
     * Gets default floors for person in DB
     */
    public static ArrayList<DefaultFloor> getDefaultFloors(int id) {
        //placeholder
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<DefaultFloor> defaultFloors = new ArrayList<DefaultFloor>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // Check for existing Person in DB
            String SQL = "SELECT * "
                        + "FROM default_floor "
                        + "WHERE PID = " + id + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                int userid = rs.getInt("PID");
                int floor = rs.getInt("default_floor_ID");
                int priority = rs.getInt("priority");
                
                defaultFloors.add(new DefaultFloor(userid, floor, priority));
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
        return defaultFloors;
    }   
    
    /**
     * Adds new schedule for person in DB
     */
    public static boolean addSchedule(int id, int type, int scheduledFloor, int startTime, int endTime) {
        //placeholder
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // Check for existing Person in DB
            String SQL = "SELECT PID, P_TYPE FROM person WHERE PID = "
                    + id + " AND P_TYPE = "
                    + type + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            if (!rs.next()) {
                // If person does not exist in DB - error and return false
                return false;
            } else {
                // If person exists in DB - add schedule and return true
                
                SQL = "INSERT INTO schedule (floor, start_datetime, end_datetime, PID) VALUES ("
                        + scheduledFloor + "," + startTime + "," + endTime + "," + id + ")";
                stmt = con.createStatement();
                stmt.executeUpdate(SQL);
                return true;
            }

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
     * Gets schedules for person in DB
     */
    public static ArrayList<Schedule> getSchedules(int id) {
        //placeholder
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // Check for existing Person in DB
            String SQL = "SELECT * FROM schedule "
                        + "WHERE PID =" + id + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                int userid = rs.getInt("PID");
                int startTiming = rs.getInt("start_datetime");
                int endTiming = rs.getInt("end_datetime");
                int floor = rs.getInt("floor");
                
                schedules.add(new Schedule(userid, startTiming, endTiming, floor));
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
        return schedules;
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
            int scenario = p1.scenario;
            // Check for existing Person in DB
            String SQL = "SELECT PID, P_TYPE FROM person WHERE PID = "
                    + userid + " AND P_TYPE = "
                    + type + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            if (!rs.next()) {
                // If person does not exist in DB - error and return false
                return false;
            } else {
                // If person exists in DB - add activity and return true
                
                SQL = "INSERT INTO activity (arrive_datetime, start_floor, end_floor, PID, scenario) "
                        + "VALUES (" + timing + "," + arrivalFloor
                        + "," + destinationFloor + "," + userid
                        + scenario + ")";
                stmt = con.createStatement();
                stmt.executeUpdate(SQL);
                return true;
            }

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
     * Queries DB for activities to populate in lift queue based on scenario.
     */    
    public static ArrayList<Person> getActivities(int selectedScenario) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Person> activities = new ArrayList<Person>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // Check for existing Person in DB
            String SQL = "SELECT person.PID, person.P_TYPE, "
                        + "activity.arrive_datetime, activity.start_floor, "
                        + "activity.end_floor, activity.scenario "
                        + "FROM activity "
                        + "LEFT JOIN person "
                        + "ON person.PID=activity.PID "
                        + "WHERE activity.scenario = " + selectedScenario + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                int userid = rs.getInt("PID");
                int type = rs.getInt("P_TYPE");
                int timing = rs.getInt("arrive_datetime");
                int arrivalFloor = rs.getInt("start_floor");
                int destinationFloor = rs.getInt("end_floor");
                int scenario = rs.getInt("scenario");
                
                activities.add(new Person(userid, type, timing, arrivalFloor, destinationFloor, scenario));
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

