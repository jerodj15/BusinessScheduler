package Scenes;

import Database.DBEdits;
import Database.DBInserts;
import Database.DBRequests;
import Objects.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class NewUserSceneController {

    @FXML
    private Label newUserLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField userNameTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Button showPasswordButton;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField emailTF;
    @FXML
    private Button saveButton;
    @FXML
    private GridPane passwordPane;
    
    private TextField passField = new TextField();

    private static int numberSetting;
    private int showClicked = 0;
    private User currentUser;
    
    public void setupNewUserScene(int userSetting)
    {
        numberSetting = userSetting;
        System.out.println("numbersetting = " + numberSetting);
        setupEditUser();
    }
    
    public void cancelButtonClicked()
    {
        if (numberSetting == 0)
        {
            SceneGenerator.anotherLoginScreen();
        }
        else
        {
            SceneGenerator.dashboardScreen(DashboardSceneController.myScheduler);
        }
    }
    
    public void saveButtonClicked()
    {
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String email = emailTF.getText();
        String userName = userNameTF.getText();
        String userPassword;
        if (showClicked == 0)
        {
            userPassword = passwordPF.getText();
        }
        else
        {
            userPassword = passField.getText();
        }
            currentUser.setFirstName(firstName);
            currentUser.setLastName(lastName);
            currentUser.setEmailAddress(email);
            currentUser.setPasswordString(userPassword);
            currentUser.setUserName(userName);
        boolean areFieldsFilled = UsefulMethods.CheckEmptyObject.checkUser(currentUser);
        if (numberSetting != 0)
        {
            currentUser.setUSerID(numberSetting);
            if (areFieldsFilled)
            {
                DBEdits.editUser(currentUser);
                SceneGenerator.dashboardScreen(DashboardSceneController.myScheduler);
            }
            else
            {
                Alert invalidAlert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields and try again", ButtonType.OK);
                invalidAlert.show();
            }
        }
        else
        {
            if (areFieldsFilled)
            {
                 DBInserts.createNewUser(currentUser);
                 DBRequests.closeConnection();
                 SceneGenerator.anotherLoginScreen();
            }
            else
            {
                Alert invalidAlert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields and try again", ButtonType.OK);
                invalidAlert.show();
            }
        }
        
        
    }
    
    public void showPasswordButtonClicked()
    {
        if (showClicked == 0)
        {
            String userPassword = passwordPF.getText();
            passField.setText(userPassword);
            passwordPane.getChildren().set(0, passField);
            showClicked = 1;
        }
        else 
        {
            String userPassword = passField.getText();
            passwordPF.setText(userPassword);
            passwordPane.getChildren().set(0, passwordPF);
            showClicked = 0;
        }
        
        
    }
    
    public void setupEditUser()
    {
        
        if (numberSetting == 0)
        {
            newUserLabel.setText("Create New User");
            currentUser = new User();
        }
        else
        {
            currentUser = DBRequests.getUserByID(numberSetting);
            newUserLabel.setText("Edit User");
            firstNameTF.setText(currentUser.getFirstName());
            lastNameTF.setText(currentUser.getLastName());
            emailTF.setText(currentUser.getEmailAddress());
            passwordPF.setText(currentUser.getPasswordString());
            userNameTF.setText(currentUser.getUserName());
        }
    }
    
}
