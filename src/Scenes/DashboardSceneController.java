package Scenes;

import Database.DBDeletes;
import Database.DBRequests;
import Main.BusinessScheduler;
import Objects.Appointment;
import Objects.Todo;
import Objects.User;
import UsefulMethods.DateConversion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashboardSceneController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Button monthButton;
    @FXML
    private Button weekButton;
    @FXML
    private Button todayButton;
    @FXML
    private TableView<Appointment> glanceTV;
    @FXML
    private Button menuButton;
    @FXML
    private Label timeframeLabel;
    @FXML
    private TableColumn<Appointment, String> glanceTVTitle;
    @FXML
    private TableColumn<Appointment, String> glanceTVStartDate;
    @FXML
    private TableColumn<Appointment, String> glanceTVStartTime;
    @FXML
    private TableColumn<Appointment, String> glanceTVEndDate;
    @FXML
    private TableColumn<Appointment, String> glanceTVEndTime;
    @FXML
    private TableView<Todo> todoGV;
    @FXML
    private TableColumn completedColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private AnchorPane bigPane;
    @FXML
    private Button logOutButton;

    
    final ContextMenu contextMenu = new ContextMenu();
    
    private static List<Appointment> usersAppointments;
    private static List<Todo> userTodos;
    int userId;
    private static int loggedUserID;
    public static BusinessScheduler myScheduler;
    private User currentUser;
    private static int userTheme = 0;
   
    
    public void setupDash(BusinessScheduler scheduler, int userID)
    {
        Themes.ThemeSetter.setMainPane(bigPane, userTheme);
//        Themes.DarkBlueTheme.setDarkBlueTheme(dashPane);
        userId = userID;
        currentUser = DBRequests.getUserByID(userID);
        welcomeLabel.setText("Welcome " + currentUser.getLastName());
        setUserID(userID);
        this.myScheduler = scheduler;
        setupGlanceAppointmentTable();
        setupGlanceTodoTable();
        dayButtonClicked();
    }
    
    public void logoutButtonClicked()
    {
       BusinessScheduler.reloadLoginScreen(myScheduler);
       DBRequests.closeConnection();
    }
    
    public void dayButtonClicked()
    {
        LocalDate currentDate = LocalDate.now();
        List<Appointment> dayAppointments = DateConversion.getTodaysAppointments(usersAppointments);
        timeframeLabel.setText(currentDate.toString());
        glanceTV.getItems().setAll(dayAppointments);
    }
    
    public void weekButtonClicked()
    {
        String beginWeek = DateConversion.getBegginingWeekDate().toString();
        String endWeek = DateConversion.getEndingWeekDate().toString();
        timeframeLabel.setText("From the dates of " + beginWeek + " through " + endWeek);
        List<Appointment> weeksAppointments = DateConversion.getCurrentWeeksAppointments(usersAppointments);
        glanceTV.getItems().setAll(weeksAppointments);
    }
    
    public void monthButtonClicked()
    {
        String currentMonth = LocalDate.now().getMonth().toString();
        timeframeLabel.setText("For the month of " + currentMonth.toString());
        List<Appointment> monthsAppointments = DateConversion.getCurrentMonthsAppointments(usersAppointments);
        glanceTV.getItems().setAll(monthsAppointments);
    }
    
    public void sideMenuClicked()
    {
        SceneGenerator.sideMenuShown();
    }
    
    public static int getThemeID()
    {
        return userTheme;
    }
    
    public static void setThemeID(int userThemeID)
    {
        userTheme = userThemeID;
    }
    
    public static void setUserID(int usID)
    {
        loggedUserID = usID;
    }
    
    public static int getUserID()
    {
        return loggedUserID;
    }

    public void rightClickDeleteAppointment(int userIn)
    {
        MenuItem deleteItem = new MenuItem("Delete");
        MenuItem clientItem = new MenuItem("Client Details");
        MenuItem apptDetails = new MenuItem("Appointment Details");
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(deleteItem);
        menuItems.add(clientItem);
        menuItems.add(apptDetails);
        contextMenu.getItems().setAll(menuItems);
                    glanceTV.setContextMenu(contextMenu);
                    
                    deleteItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            
                            DBDeletes.deleteAppointmentByID(userIn);
                            SceneGenerator.dashboardScreen(myScheduler);
                        }
                    });
                    clientItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selClient = DBRequests.getClientIdByAppointmentId(userIn);
                SceneGenerator.clientDetailsScreen(selClient);
            }
        });
                    apptDetails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int apptID = glanceTV.getSelectionModel().getSelectedItem().getAppointmentID();
                    SceneGenerator.showAppointmentDetails(apptID);
            }
        });
        
    }
    
    public void rightClickDeleteTodo(int userIn)
    {
        MenuItem deleteItem = new MenuItem("Delete");
        MenuItem detailsItem = new MenuItem("Todo Details");
        List<MenuItem> todoItems = new ArrayList<>();
        todoItems.add(deleteItem);
        todoItems.add(detailsItem);
                    contextMenu.getItems().setAll(todoItems);
                    todoGV.setContextMenu(contextMenu);
                    
                    deleteItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            DBDeletes.deleteTodoByTodoID(userIn);
                            SceneGenerator.dashboardScreen(myScheduler);
                        }
                    });
                    detailsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = todoGV.getSelectionModel().getSelectedItem().getID();
                    SceneGenerator.showTodoDetails(index);
            }
        });
    }
    
    public void setupGlanceAppointmentTable()
    {
        // Setup the appointments table view
        usersAppointments = DBRequests.getAppointmentsByUserID(userId);
        glanceTVTitle.setCellValueFactory(new PropertyValueFactory<>("aptTitle"));
        glanceTVStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        glanceTVStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        glanceTVEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        glanceTVEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        glanceTV.getItems().setAll(usersAppointments);
        glanceTV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                if (event.getClickCount() > 1)
                {
                    int apptID = glanceTV.getSelectionModel().getSelectedItem().getAppointmentID();
                    SceneGenerator.showAppointmentDetails(apptID);
                }
                
            }
        });
        glanceTV.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isSecondaryButtonDown())
                {
                    int apptID = glanceTV.getSelectionModel().getSelectedItem().getAppointmentID();
                    contextMenu.show(glanceTV, event.getSceneX(), event.getSceneY());
                    rightClickDeleteAppointment(apptID);
                }
            }
        });
    }
    
    public void setupGlanceTodoTable()
    {
        // Setup the todo table view
        userTodos = DBRequests.getTodosByUserID(userId);
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        todoGV.getItems().setAll(userTodos);
        todoGV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 1)
                {
                    int index = todoGV.getSelectionModel().getSelectedItem().getID();
                    SceneGenerator.showTodoDetails(index);
                }
            }
        });
        
        todoGV.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isSecondaryButtonDown())
                {
                    int todoID = todoGV.getSelectionModel().getSelectedItem().getID();
                    contextMenu.show(todoGV, event.getSceneX(), event.getSceneY());
                    rightClickDeleteTodo(todoID);
                }
            }
        });
    }

}
