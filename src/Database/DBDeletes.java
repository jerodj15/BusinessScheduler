package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDeletes {
    
    private static final String connectionURL = "jdbc:sqlite:test.db";
    static Connection c;
    static Statement stmt;
    
    public static void deleteAppointmentByID(int appointmentID)
    {
        String deleteAppointment = "DELETE FROM APPOINTMENTS WHERE ID = " + appointmentID;
        executeDeleteStatement(deleteAppointment);
    }
    
    public static void deleteTodoByTodoID(int todoID)
    {
        String deleteTodo = "DELETE FROM TODO WHERE ID = " + todoID;
        executeDeleteStatement(deleteTodo);
    }
    
    public static void deleteSubTodoByID(int subTodoID)
    {
        String deleteSubTodo = "DELETE FROM SUBTODO WHERE ID = " + subTodoID;
        executeDeleteStatement(deleteSubTodo);
    }
    
    public static void deleteApptFeeByID(int aptFeeID)
    {
        String deleteApptFee = "DELETE FROM APPTFEES WHERE ID = " + aptFeeID;
        executeDeleteStatement(deleteApptFee);
    }
    
    public static void deleteTodoFeeByID(int todoFeeID)
    {
        
        String deleteTodoFee = "DELETE FROM TODOFEES WHERE ID = " + todoFeeID;
        executeDeleteStatement(deleteTodoFee);
    }
    
    public static void executeDeleteStatement(String sqlStatement)
    {
        c = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(connectionURL);
            stmt = c.createStatement();
            stmt.executeUpdate(sqlStatement);
            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
