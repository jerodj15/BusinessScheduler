package Scenes;

import Database.DBRequests;
import Main.BusinessScheduler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class CreateNewUserSceneController {

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    
    private BusinessScheduler myBusinessScheduler;
    
    
    public void setupCreateUserLogin(BusinessScheduler scheduler)
    {
        this.myBusinessScheduler = scheduler;
        passwordPF.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER)
                {
                    loginButtonClicked();
                }
            }
        });
    }
    
    public void cancelButtonClicked()
    {

        LoginSceneController.dialog.close();
    }
    
    public void loginButtonClicked()
    {
        String userName = usernameTF.getText();
        String userPass = passwordPF.getText();
        boolean accessGranted = DBRequests.isUsingAdminCreds(userName, userPass);
        if (accessGranted)
        {
            cancelButtonClicked();
            DBRequests.closeConnection();
            SceneGenerator.newUserScreen(0);
        }
        else
        {
            Alert invalidAlert = new Alert(Alert.AlertType.ERROR, "The credentials you have entered are incorrect, please try again.", ButtonType.OK);
            invalidAlert.show();
        }
    }

     
    
}
