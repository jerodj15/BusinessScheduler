package Scenes;

import Main.BusinessScheduler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SideMenuController  {

    @FXML
    private Label menuLabel;
    @FXML
    private Button newApptButton;
    @FXML
    private Button newClientButton;
    @FXML
    private Button newTodoButton;
    @FXML
    private Button dashButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button hideButton;
    @FXML
    private Button reportsButton;
    @FXML
    private AnchorPane smallPane;

    private BusinessScheduler myBusinessScheduler;
    Stage myStage = new Stage();
    
    public void setupMenu(BusinessScheduler scheduler)
    {
        this.myBusinessScheduler = scheduler;
        SceneGenerator.setupSideMenu();
        myStage = BusinessScheduler.secondStage;
    }
    
    public void newClientClicked()
    {
        SceneGenerator.hideClicked();
        newClientScreen();
    }
    
    public void newAppointmentClicked()
    {
        SceneGenerator.hideClicked();
        newAppointmentScreen();
    }
    
    public void newTodoClicked()
    {
        SceneGenerator.hideClicked();
        newTodoScreen();
    }
    
    public void clientListingClicked()
    {
        SceneGenerator.hideClicked();
        SceneGenerator.clientListingScreen();
    }
    
    public void hideClicked()
    {
        SceneGenerator.hideClicked();
        SceneGenerator.dashboardScreen(myBusinessScheduler);
    }
    
    public void reportClicked()
    {
        SceneGenerator.hideClicked();
        SceneGenerator.reportScreen();
    }
    
    public void dashboardClicked()
    {
        SceneGenerator.hideClicked();
        SceneGenerator.dashboardScreen(myBusinessScheduler);
    }
    
    public void settingsButtonClicked()
    {
        SceneGenerator.hideClicked();
        SceneGenerator.newUserScreen(DashboardSceneController.getUserID());
    }

    // Menu options 
    public void newClientScreen()
    { 
        SceneGenerator.newClientScreen();
    }
    
    public void newAppointmentScreen()
    {
        SceneGenerator.newAppointmentScreen();
    }
    
    public void newTodoScreen()
    {
       SceneGenerator.newTodoScreen();
    }
    
   

}
