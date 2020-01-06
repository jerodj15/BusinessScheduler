package Database;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SQLiteJDBC {
    public static Connection c;
    public void connectToDB()
    {
        File file = new File("test.db");
        if (!(file.exists()))
        {
            Statement stmt = null;
  
        try {
            c = getActiveConnection();
            
            System.out.println("Opened database successfully");
            String[] sqlStatements = createTables();
            stmt = c.createStatement();
            String sql = null;
            // Loop through to create tables
            for (int i = 0; i < sqlStatements.length; i++)
            {
                sql = sqlStatements[i];
                stmt.executeUpdate(sql);
            }

            String createAdmin = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, CREATEDATE, FIRSTNAME, LASTNAME, THEMEID) VALUES " +
                                                    "('admin', 'admin', 'admin@admin.com', '" + LocalDateTime.now() + "', 'Admin', 'Admin'," + 0 + ");";
            stmt = c.createStatement();
            stmt.executeUpdate(createAdmin);
            stmt.close();
            c.commit();
            
        } catch (SQLException ex) {
            System.out.println("SQL exception in connectToDB: " + ex.getLocalizedMessage());
        }
        System.out.println("Table created successffully");
        }
        else
        {
            c = getActiveConnection();
        }
    }
    
    public String[] createTables()
    {
        String[] result = new String[7];
        String sqlUsers = "CREATE TABLE USERS " + "(ID INTEGER PRIMARY KEY NOT NULL, " +
                                                   "USERNAME TEXT NOT NULL, " +
                                                   "PASSWORD TEXT NOT NULL, " +
                                                   "EMAIL TEXT NOT NULL, " +
                                                   "CREATEDATE TEXT NOT NULL, " +
                                                   "FIRSTNAME TEXT NOT NULL, " +
                                                   "LASTNAME TEXT NOT NULL, " + 
                                                   "THEMEID INT NOT NULL DEFAULT 0)";
        String sqlClients = "CREATE TABLE CLIENTS " + "(ID INTEGER PRIMARY KEY NOT NULL, " +
                                                        "FIRSTNAME TEXT NOT NULL, " +
                                                        "LASTNAME TEXT NOT NULL, " +
                                                        "PHONE TEXT NOT NULL, " +
                                                        "EMAIL TEXT NOT NULL, " +
                                                        "ADDRESS TEXT, " +
                                                        "NOTES TEXT, " + 
                                                        "DOB TEXT)";
        String sqlAppointments = "CREATE TABLE APPOINTMENTS " + "(ID INTEGER PRIMARY KEY NOT NULL, " +
                                                                "TITLE TEXT NOT NULL, " +
                                                                "USERID INT NOT NULL, " +
                                                                "CLIENTID INT NOT NULL, " +
                                                                "STARTDATE TEXT NOT NULL, " +
                                                                "STARTTIME TEXT NOT NULL, " +
                                                                "ENDDATE TEXT NOT NULL, " + 
                                                                "ENDTIME TEXT NOT NULL)";
        String sqlAppointmentFees = "CREATE TABLE APPTFEES " + "(ID INTEGER PRIMARY KEY NOT NULL, " +
                                                                "APPTID INT NOT NULL, " +
                                                                "TITLE TEXT NOT NULL, " +
                                                                "AMOUNT TEXT NOT NULL)";
        String sqlTodo = "CREATE TABLE TODO " + "(ID INTEGER PRIMARY KEY NOT NULL, " +
                                                "USERID INT NOT NULL, " +
                                                "CLIENTID INT, " +
                                                "TITLE TEXT NOT NULL, " +
                                                "DUE TEXT, " +
                                                "CREATEDATE TEXT NOT NULL, " +
                                                "NOTES TEXT, " +
                                                "COMPLETED INT NOT NULL DEFAULT 0)";
        String sqlTodoFees = "CREATE TABLE TODOFEES " + "(ID INTEGER PRIMARY KEY NOT NULL, " +
                                                        "TODOID INT NOT NULL, " +
                                                        "TTTLE TEXT NOT NULL, " +
                                                        "AMOUNT TEXT NOT NULL)";
        String sqlSubTodo = "CREATE TABLE SUBTODO " + "(ID INTEGER PRIMARY KEY NOT NULL, " +
                                                      "TODOID INT NOT NULL, " +
                                                      "TITLE STRING NOT NULL, " +
                                                      "NOTES TEXT, " +
                                                      "COMPLETED INT NOT NULL)";
        result[0] = sqlUsers;
        result[1] = sqlClients;
        result[2] = sqlAppointments;
        result[3] = sqlAppointmentFees;
        result[4] = sqlTodo;
        result[5] = sqlTodoFees;
        result[6] = sqlSubTodo;
        return result;
    }
    
    public static Connection getActiveConnection()
    {
        Connection result = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            result = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (ClassNotFoundException ex) {
            System.out.println("Get Active Connection Error: " + ex.getLocalizedMessage());
        } catch (SQLException ex) {
            System.out.println("Get Active Connection Error: " + ex.getLocalizedMessage());
        }
        return result;
    }
}
