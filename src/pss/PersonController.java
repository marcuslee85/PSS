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
            + "zeroDateTimeBehavior=convertToNull&"
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
            System.out.println("SQL Exception: " + e.toString());
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
     * Adds new default floors by persons in DB (WIP)
     * CANCELS OLD DEFAULT FLOORS AND IMPLEMENTS NEW ONES
     */
    public static boolean addDefaultFloors(Person p1) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // For each default floor to be added
            int userid = p1.userid;
            int type = p1.type;
            int exitFloor = p1.arrivalFloor;
            int officeFloor = p1.destinationFloor;
            
            String SQL = "SELECT PID, P_TYPE FROM person WHERE PID = "
                    + userid + " AND P_TYPE = "
                    + type + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            if (!rs.next()) {
                // If person does not exist in DB, create new Person
                SQL = "INSERT INTO person (PID, P_TYPE) "
                    + "VALUES (" + userid + "," + type + ")";
                stmt = con.createStatement();
                stmt.executeUpdate(SQL);
            } 
            //Deletes old entries
            SQL = "DELETE d.* FROM default_floor d "
                    + "WHERE PID = " + userid + "";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            
            //Creates new entries
            SQL = "INSERT INTO default_floor (default_floor_ID, priority, PID) "
               + "VALUES (" + officeFloor + ",1," + userid + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            
            
            SQL = "INSERT INTO default_floor (default_floor_ID, priority, PID) "
               + "VALUES (" + exitFloor + ",2," + userid + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
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
            System.out.println("SQL Exception: " + e.toString());
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
    public static boolean addSchedule(Person p1, int endTime) {
        //placeholder
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            int userid = p1.userid;
            int type = p1.type;
            int startTime = p1.timing;
            int floor = p1.destinationFloor;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            // Check for existing Person in DB
            String SQL = "SELECT PID, P_TYPE FROM person WHERE PID = "
                    + userid + " AND P_TYPE = "
                    + type + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            if (!rs.next()) {
                // If person does not exist in DB, create new Person
                SQL = "INSERT INTO person (PID, P_TYPE) "
                    + "VALUES (" + userid + "," + type + ")";
                stmt = con.createStatement();
                stmt.executeUpdate(SQL);
            }

            SQL = "INSERT INTO schedule (floor, start_datetime, end_datetime, PID) VALUES ("
                    + floor + "," + startTime + "," + endTime + "," + userid + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
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
            System.out.println("SQL Exception: " + e.toString());
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
     * Adds new activities for person in DB
     */    
    public static boolean addActivity(Person p1, int scenario) {
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
            String SQL = "SELECT PID, P_TYPE FROM person WHERE PID = "
                    + userid + " AND P_TYPE = "
                    + type + "";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            
            if (!rs.next()) {
                // If person does not exist in DB, create new Person
                SQL = "INSERT INTO person (PID, P_TYPE) "
                    + "VALUES (" + userid + "," + type + ")";
                stmt = con.createStatement();
                stmt.executeUpdate(SQL);
            } 
            SQL = "INSERT INTO activity (arrive_datetime, start_floor, end_floor, PID, scenario) "
                    + "VALUES (" + timing + "," + arrivalFloor
                    + "," + destinationFloor + "," + userid
                    + "," + scenario + ")";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
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
                
                activities.add(new Person(userid, type, timing, arrivalFloor, destinationFloor));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
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

