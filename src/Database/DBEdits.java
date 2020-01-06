package Database;

import Objects.Appointment;
import Objects.ApptFee;
import Objects.SubTodo;
import Objects.Todo;
import Objects.TodoFee;
import Objects.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBEdits {
    
    private static final String connectionURL = "jdbc:sqlite:test.db";
    
    public static void editAppointment(Appointment userAppointment)
    {
            String updateAppointment = "UPDATE APPOINTMENTS " +
                                       "SET TITLE = '" + userAppointment.getAptTitle() + "', " +
                                       "CLIENTID = " + userAppointment.getClientID() + ", " +
                                       "STARTDATE = '" + userAppointment.getStartDate() + "', " +
                                       "STARTTIME = '" + userAppointment.getStartTime() + "', " +
                                       "ENDDATE = '" + userAppointment.getEndDate() + "', " +
                                       "ENDTIME = '" + userAppointment.getEndTime() + "' " +
                                       "WHERE ID = " + userAppointment.getAppointmentID() + ";";
            executeEditStatement(updateAppointment);
    }
    
    public static void editTodo(Todo userTodo)
    {
            String updateTodo = "UPDATE TODO " +
                                "SET TITLE= '" + userTodo.getTitle() + "', " +
                                "CLIENTID =" + userTodo.getClientID() + ", " +
                                "DUE = '" + userTodo.getDueDate() + "', " +
                                "NOTES = '" + userTodo.getNotes() + "', " +
                                "COMPLETED = " + userTodo.getCompleted() + " " +
                                "WHERE ID = " + userTodo.getID() + ";";
            executeEditStatement(updateTodo);
    }
    
    public static void editSubTodo(SubTodo userSubTodo)
    {
            String updateSubTodo = "UPDATE SUBTODO " + 
                                   "SET TITLE = '" + userSubTodo.getSubTodoTitle() + "', " +
                                   "NOTES = '" + userSubTodo.getSubTodoNotes() + "', " +
                                   "COMPLETED = " + userSubTodo.getSubTodoCompleted() + " " +
                                   "WHERE ID = " + userSubTodo.getID() + ";";
            executeEditStatement(updateSubTodo);
    }
    
    public static void editAppointmentFee(ApptFee userApptFee)
    {
            String updateApptFee = "UPDATE APPTFEES " + 
                                   "SET TITLE = '" + userApptFee.getTitle() + "', " +
                                   "AMOUNT = '" + userApptFee.getAmount() + "' " +
                                   "WHERE ID = " + userApptFee.getId() + ";";
            executeEditStatement(updateApptFee);

    }
    
    public static void editTodoFee(TodoFee userFee)
    {
            String updateTodoFee = "UPDATE TODOFEES " +
                                   "SET TTTLE = '" + userFee.getTitle() + "', " +
                                   "AMOUNT = '" + userFee.getAmount() + "' " +
                                   "WHERE ID = " + userFee.getID() + ";";
            executeEditStatement(updateTodoFee);
    }
    
    public static void editUser(User userUser)
    {
            String updateUser = "UPDATE USERS " + 
                                "SET USERNAME = '" + userUser.getUserName() + "', " +
                                "PASSWORD = '" + userUser.getPasswordString() +"', " +
                                "EMAIL = '" + userUser.getEmailAddress() + "', " +
                                "FIRSTNAME = '" + userUser.getFirstName() + "', " +
                                "LASTNAME = '" + userUser.getLastName() + "' " +
                                "WHERE ID = " + userUser.getUserID() + ";";
            executeEditStatement(updateUser);
    }
    
    public static void executeEditStatement(String sqlStatement)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(connectionURL);
            stmt = c.createStatement();
            stmt.executeUpdate(sqlStatement);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
        }
    }
    
}
