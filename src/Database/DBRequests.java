package Database;

import Objects.Appointment;
import Objects.ApptFee;
import Objects.Client;
import Objects.SubTodo;
import Objects.Todo;
import Objects.TodoFee;
import Objects.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBRequests extends SQLiteJDBC{
    private static final String connectionURL = "jdbc:sqlite:test.db";
    private static Connection c;
    private static Statement stmt;
    private static ResultSet rs;
    
    // Check the login credentials entered at login
    public static int checkLogin(String uName, String passWord)
    {
        int result = 0;
        String checkCreds = "SELECT * FROM USERS WHERE USERNAME = '" + uName + "';";
        String passFetch = "";
        String userFetch = "";
        ResultSet diffRs = retreiveData(checkCreds);
        try {
            while (rs.next())
            {
                userFetch = rs.getString("USERNAME");
                System.out.println("userFetch = " + userFetch);
                passFetch = rs.getString("PASSWORD");
                System.out.println("passFetch = " + passFetch);
                result = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println("Check Login Error: " + ex.getLocalizedMessage());
        }
            if ((userFetch.equals(uName)) && (passFetch.equals(passWord)))
            {
                System.out.println("Access Granted");
            }
            else 
            {
                System.out.println("Access Denied");
                result = 0;
            }
        return result;
    }
    
    // Check admin login for creating new users
    public static boolean isUsingAdminCreds(String adminName, String adminPass)
    {
        boolean result = false;
        String checkAdminCreds = "SELECT * FROM USERS WHERE ID = 1";
        String adUser = "";
        String adPass = "";
            rs = retreiveData(checkAdminCreds);
        try {
            adUser = rs.getString("USERNAME");
            adPass = rs.getString("PASSWORD");
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
            if (adUser.equals(adminName) && adPass.equals(adminPass))
            {
                result = true;
            }
            else
            {
                result = false;
            }
        return result;
    }
    
    // Get a list of call clients
    public static List<Client> getAllClients()
    {
        List<Client> result = new ArrayList<>();
        String getClients = "SELECT * FROM CLIENTS";
        ResultSet rs = retreiveData(getClients); 
        try {
            while(rs.next())
            {
                Client newClient = new Client();
                newClient.setClientID(rs.getInt("ID"));
                newClient.setFirstName(rs.getString("FIRSTNAME"));
                newClient.setLastName(rs.getString("LASTNAME"));
                newClient.setPhoneNumber(rs.getString("PHONE"));
                newClient.setEmailAddress(rs.getString("EMAIL"));
                newClient.setAddress(rs.getString("ADDRESS"));
                newClient.setClientNotes(rs.getString("NOTES"));
                newClient.setBirthDate(rs.getString("DOB"));
                result.add(newClient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // Get a single client by their client ID
    public static Client getClientByID(int clientID)
    {
        Client result = new Client();
        String getClient = "SELECT * FROM CLIENTS WHERE ID = " + clientID;
        ResultSet rs = retreiveData(getClient);
        try {
            while(rs.next())
            {
                result.setClientID(rs.getInt("ID"));
                result.setFirstName(rs.getString("FIRSTNAME"));
                result.setLastName(rs.getString("LASTNAME"));
                result.setPhoneNumber(rs.getString("PHONE"));
                result.setEmailAddress(rs.getString("EMAIL"));
                result.setAddress(rs.getString("ADDRESS"));
                result.setClientNotes(rs.getString("NOTES"));
                result.setBirthDate(rs.getString("DOB"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static int getClientIdByAppointmentId(int apptID)
    {
        int result = 0;
        String getClientId = "SELECT CLIENTID FROM APPOINTMENTS WHERE ID = " + apptID;
        ResultSet rs = retreiveData(getClientId);   
        try {
            while(rs.next())
            {
                result = rs.getInt("CLIENTID");
                System.out.println("db client id " + result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    // Get user informatoin by their id
    public static User getUserByID(int userId)
    {
        User result = new User();
        String getUser = "SELECT * FROM USERS WHERE ID = " + userId;
        ResultSet rs = retreiveData(getUser);
        try {
            while(rs.next())
            {
                result.setUserName(rs.getString("USERNAME"));
                result.setPasswordString(rs.getString("PASSWORD"));
                result.setEmailAddress(rs.getString("EMAIL"));
                result.setCreateDate(rs.getString("CREATEDATE"));
                result.setFirstName(rs.getString("FIRSTNAME"));
                result.setLastName(rs.getString("LASTNAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    // Get a list of all apointments by userID
    public static List<Appointment> getAppointmentsByUserID(int userID)
    {
        List<Appointment> result = new ArrayList<>();
        String getAppointments = "SELECT * FROM APPOINTMENTS WHERE USERID = " + userID;
        ResultSet rs = retreiveData(getAppointments);
        try {
            while(rs.next())
            {
                Appointment addMe = new Appointment();
                addMe.setAppointmentID(rs.getInt("ID"));
                addMe.setAptTitle(rs.getString("TITLE"));
                addMe.setUserID(rs.getInt("USERID"));
                addMe.setClientID(rs.getInt("CLIENTID"));
                addMe.setStartDate(rs.getString("STARTDATE"));
                addMe.setStartTime(rs.getString("STARTTIME"));
                addMe.setEndDate(rs.getString("ENDDATE"));
                addMe.setEndTime(rs.getString("ENDTIME"));
                result.add(addMe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    // Get a list of all appointments by userId and start and end date
    public static List<Appointment> getAppointmentsByUserIDInBetween(int userID, String startDate, String endDate)
    {
        List<Appointment> result = new ArrayList<>();
        List<Appointment> userAppointments = getAppointmentsByUserID(userID);
        return result;
    }
    // Get details of a specific appointment by appointment ID
    public static Appointment getAppointmentByID(int aptID)
    {
        Appointment result = new Appointment();
        String getAppointment = "SELECT * FROM APPOINTMENTS WHERE ID = " + aptID;
        ResultSet rs = retreiveData(getAppointment);
        try {
            while(rs.next())
            {
                result.setAppointmentID(rs.getInt("ID"));
                result.setAptTitle(rs.getString("TITLE"));
                result.setUserID(rs.getInt("USERID"));
                result.setClientID(rs.getInt("CLIENTID"));
                result.setStartDate(rs.getString("STARTDATE"));
                result.setStartTime(rs.getString("STARTTIME"));
                result.setEndDate(rs.getString("ENDDATE"));
                result.setEndTime(rs.getString("ENDTIME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    // Get a list of all todos by user ID
    public static List<Todo> getTodosByUserID(int userID)
    {
        List<Todo> result = new ArrayList<>();
        String getTodos = "SELECT * FROM TODO WHERE USERID = " + userID;
        ResultSet rs = retreiveData(getTodos);    
        try {
            while(rs.next())
            {
                Todo addMe = new Todo();
                addMe.setID(rs.getInt("ID"));
                addMe.setUserID(rs.getInt("USERID"));
                addMe.setClientID(rs.getInt("CLIENTID"));
                addMe.setTitle(rs.getString("TITLE"));
                addMe.setDueDate(rs.getString("DUE"));
                addMe.setCreateDate(rs.getString("CREATEDATE"));
                addMe.setNotes(rs.getString("NOTES"));
                addMe.setCompleted(rs.getInt("COMPLETED"));
                result.add(addMe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static Todo getTodoByTitleAndUserID(int userID, String title)
    {
        Todo result = new Todo();
        String getTodo = "SELECT * FROM TODO WHERE USERID = " + userID + " AND TITLE = '" + title + "';";
        ResultSet rs = retreiveData(getTodo);
        try {
            while (rs.next())
            {
                result.setID(rs.getInt("ID"));
                result.setUserID(rs.getInt("USERID"));
                result.setClientID(rs.getInt("CLIENTID"));
                result.setTitle(rs.getString("TITLE"));
                result.setDueDate(rs.getString("DUE"));
                result.setCreateDate(rs.getString("CREATEDATE"));
                result.setNotes(rs.getString("NOTES"));
                result.setCompleted(rs.getInt("COMPLETED"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static Todo getTodoByTodoID(int todID)
    {
        Todo result = new Todo();
        String getTodo = "SELECT * FROM TODO WHERE ID = " + todID;
        ResultSet rs = retreiveData(getTodo);
        try {
            while (rs.next())
            {
                result.setID(rs.getInt("ID"));
                result.setUserID(rs.getInt("USERID"));
                result.setClientID(rs.getInt("CLIENTID"));
                result.setTitle(rs.getString("TITLE"));
                result.setDueDate(rs.getString("DUE"));
                result.setCreateDate(rs.getString("CREATEDATE"));
                result.setNotes(rs.getString("NOTES"));
                result.setCompleted(rs.getInt("COMPLETED"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static List<SubTodo> getSubTodosByTodoID(int todoId)
    {
        List<SubTodo> result = new ArrayList<>();
        String getSubTodos = "SELECT * FROM SUBTODO WHERE TODOID = " + todoId;
        ResultSet rs = retreiveData(getSubTodos);
        try {
            while (rs.next())
            {
                SubTodo addMe = new SubTodo();
                addMe.setID(rs.getInt("ID"));
                addMe.setTodoID(rs.getInt("TODOID"));
                addMe.setSubTodoTitle(rs.getString("TITLE"));
                addMe.setSubTodoNotes(rs.getString("NOTES"));
                addMe.setSubTodoCompleted(rs.getInt("COMPLETED"));
                result.add(addMe);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static List<ApptFee> getApptFeesByApptID(int apptID)
    {
        List<ApptFee> result = new ArrayList<>();
        String getSubTodos = "SELECT * FROM APPTFEES WHERE APPTID = " + apptID;
        ResultSet rs = retreiveData(getSubTodos);
        try {
            while (rs.next())
            {
                ApptFee addMe = new ApptFee();
                addMe.setId(rs.getInt("ID"));
                addMe.setApptID(rs.getInt("APPTID"));
                addMe.setTitle(rs.getString("TITLE"));
                addMe.setAmount(rs.getString("AMOUNT"));
                result.add(addMe);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static List<TodoFee> getTodoFeesByTodoID(int todoID)
    {
        List<TodoFee> result = new ArrayList<>();
        String getTodoFees = "SELECT * FROM TODOFEES WHERE TODOID = " + todoID;
        ResultSet rs = retreiveData(getTodoFees);
        try {
            while(rs.next())
            {
                TodoFee addMe = new TodoFee();
                addMe.setID(rs.getInt("ID"));
                addMe.setTodoID(rs.getInt("TODOID"));
                addMe.setTitle(rs.getString("TTTLE"));
                addMe.setAmount(rs.getString("AMOUNT"));
                result.add(addMe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static List<ApptFee> getServicesAndFeesByDate(int userID, String startDate, String endDate)
    {
        int i = 0;
        List<ApptFee> result = new ArrayList<>();
        List<Appointment> userAppointments = getAppointmentsByUserID(userID);
        List<ApptFee> userApptFees = new ArrayList<>();
        for (Appointment appointment : userAppointments)
        {
            List<ApptFee> newApptFees = DBRequests.getApptFeesByApptID(appointment.getAppointmentID());
            for (int j = 0; j < newApptFees.size(); j++)
            {
                userApptFees.add(newApptFees.get(j));
            }
            i++;
        }
        result = userApptFees;
        return result;
    }
    
    public static ResultSet retreiveData(String sqlStatement)
    {
        ResultSet result = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(connectionURL);
            stmt = c.createStatement();
            result = stmt.executeQuery(sqlStatement);
            rs = stmt.executeQuery(sqlStatement);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Retreive Data Error: " + e.getLocalizedMessage());
        }
        return result;
    }
    
    public static void closeConnection()
    {
        try {
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Close Connection Error: " + ex.getLocalizedMessage());
        }
    }
}
