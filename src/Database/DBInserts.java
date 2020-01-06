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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;


public class DBInserts {
    
    private static final String connectionURL = "jdbc:sqlite:test.db";
    private static Connection c = SQLiteJDBC.c;
    private static Statement stmt;
    
    public static void createNewClient(Client userClient)
    {
            String newClientString = "INSERT INTO CLIENTS " + "(FIRSTNAME, LASTNAME,"
                    + " PHONE, EMAIL, ADDRESS, NOTES, DOB) "
                    + "VALUES ('" + userClient.getFirstName() + "', '"
                                  + userClient.getLastName() + "', '" 
                                  + userClient.getPhoneNumber() + "', '"
                                  + userClient.getEmailAddress() + "', '"
                                  + userClient.getAddress() + "', '" 
                                  + userClient.getClientNotes() + "', '"
                                  + userClient.getBirthDate() + "');";
            executeInsertStatement(newClientString);
    }
    
    public static void createNewAppointment(Appointment userApt)
    {
            String newAptString = "INSERT INTO APPOINTMENTS " + "(TITLE, USERID, CLIENTID, STARTDATE, STARTTIME, "
                    + "ENDDATE, ENDTIME) VALUES ('" + userApt.getAptTitle() + "', "
                                                   + userApt.getUserID() + ", "
                                                   + userApt.getClientID() + ", '"
                                                   + userApt.getStartDate() + "', '"
                                                   + userApt.getStartTime() + "', '"
                                                   + userApt.getEndDate() + "', '"
                                                   + userApt.getEndTime() + "');";
            executeInsertStatement(newAptString);
    }
    
    public static void createNewTodo(Todo userTodo)
    {
            String newTodoString = "INSERT INTO TODO " + "(USERID, CLIENTID, TITLE, DUE, CREATEDATE, NOTES, COMPLETED) VALUES " +
                                                          "(" + userTodo.getUserID() + ", " +
                                                                userTodo.getClientID() + ", '" +
                                                                 userTodo.getTitle() + "', '" +
                                                                 userTodo.getDueDate() + "', '" + 
                                                                 userTodo.getCreateDate() + "', '" +
                                                                 userTodo.getNotes() + "', " +
                                                                 userTodo.getCompleted() +");";
            executeInsertStatement(newTodoString);
    }
    
    public static void createNewSubTodo(SubTodo userSubTodo)
    {
            String newSubTodoString = "INSERT INTO SUBTODO " + "(TODOID, TITLE, NOTES, COMPLETED) VALUES " +
                                                                "(" + userSubTodo.getTodoID() + ", '" +
                                                                      userSubTodo.getSubTodoTitle() + "', '" +
                                                                      userSubTodo.getSubTodoNotes() + "', " +
                                                                      userSubTodo.getSubTodoCompleted() + ");";
            executeInsertStatement(newSubTodoString);
    }
    
    public static void appendNewSubTodo(SubTodo userSubTodo)
    {
            String newSubTodoString = "INSERT INTO SUBTODO " + "(TODOID, TITLE, NOTES, COMPLETED) VALUES " +
                                                                "(" + userSubTodo.getTodoID() + ", '" +
                                                                      userSubTodo.getSubTodoTitle() + "', '" +
                                                                      userSubTodo.getSubTodoNotes() + "', " +
                                                                      userSubTodo.getSubTodoCompleted() + ");";
            executeInsertStatement(newSubTodoString);
    }
    
    public static void createNewUser(User userUser)
    {
            String newUser = "INSERT INTO USERS " + 
                             "(USERNAME, PASSWORD, EMAIL, FIRSTNAME, LASTNAME, CREATEDATE) VALUES " +
                             "('" + userUser.getUserName() + "', '" +
                                    userUser.getPasswordString() + "', '" +
                                    userUser.getEmailAddress() + "', '" +
                                    userUser.getFirstName() + "', '" +
                                    userUser.getLastName() + "', '" +
                                    LocalDateTime.now() + "');";
            c = SQLiteJDBC.getActiveConnection();
            try {
            Class.forName("org.sqlite.JDBC");
            stmt = c.createStatement();
            stmt.execute(newUser);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Execute Insert Statment Error: " + e.getLocalizedMessage());
        }
//            executeInsertStatement(newUser);
    }
    
    public static void createMultipleSubTodos(List<SubTodo> userSubTodos)
    {
            for (int i = 0; i < userSubTodos.size(); i++)
            {
                String newSubTodoString = "INSERT INTO SUBTODO " + "(TODOID, TITLE, NOTES, COMPLETED) VALUES " +
                                                                "(" + userSubTodos.get(i).getTodoID() + ", '" +
                                                                      userSubTodos.get(i).getSubTodoTitle() + "', '" +
                                                                      userSubTodos.get(i).getSubTodoNotes() + "', " +
                                                                      userSubTodos.get(i).getSubTodoCompleted() + ");";
                executeInsertStatement(newSubTodoString);
            }
    }
    
    public static void createMultipleAppointmentFees(List<ApptFee> userApptFees)
    {
            for (int i = 0; i < userApptFees.size(); i++)
            {
                String newApptFeeString = "INSERT INTO APPTFEES " + "(APPTID, TITLE, AMOUNT) VALUES " +
                                           "(" + userApptFees.get(i).getApptID() + ", '" +
                                                 userApptFees.get(i).getTitle() + "', '" + 
                                                 userApptFees.get(i).getAmount() + "');";
                executeInsertStatement(newApptFeeString);
            }
    }
    
    public static void createAppointmentFee(ApptFee userApptFee)
    {
                String newApptFeeString = "INSERT INTO APPTFEES " + "(APPTID, TITLE, AMOUNT) VALUES " +
                                           "(" + userApptFee.getApptID() + ", '" +
                                                 userApptFee.getTitle() + "', '" + 
                                                 userApptFee.getAmount() + "');";
                executeInsertStatement(newApptFeeString);
    }
    
    public static void createTodoFee(TodoFee userTodoFee)
    {
            String newTodoFeeString = "INSERT INTO TODOFEES " + "(TODOID, TTTLE, AMOUNT) VALUES " + 
                                        "(" + userTodoFee.getTodoID() + ", '" +
                                              userTodoFee.getTitle() + "', '" + 
                                              userTodoFee.getAmount() + "');";
            executeInsertStatement(newTodoFeeString);
    }
    
    public static void executeInsertStatement(String sqlStatement)
    {
        c = SQLiteJDBC.getActiveConnection();
        System.out.println("hereherehere");
        try {
            Class.forName("org.sqlite.JDBC");
            stmt = c.createStatement();
            stmt.execute(sqlStatement);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Execute Insert Statment Error: " + e.getLocalizedMessage());
        }
    }
    
}
