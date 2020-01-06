package Scenes;

import Database.DBRequests;
import Database.SQLiteJDBC;
import Main.BusinessScheduler;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LoginSceneController {
    static BusinessScheduler myScheduler;
    @FXML
    private TextField userNameTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button newUserButton;

    
    @FXML
    private Label titleLabel;
    
    static Stage dialog = new Stage();
    private SQLiteJDBC myDBC = new SQLiteJDBC();
    
    public void setupLoginScene(BusinessScheduler scheduler)
    {
        this.myScheduler = scheduler;
        myDBC.connectToDB();
        passwordPF.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER)
                {
                    event.consume();
                    loginClicked();
                }
            }
        });
    }
 
    
    @FXML
    public void loginClicked()
    {
        StringBuffer sbUname = new StringBuffer(userNameTF.getText());
        StringBuffer sbPass = new StringBuffer(passwordPF.getText());
        System.out.println("pass entered = " + sbPass.toString());
        System.out.println("user = " + sbUname.toString());
        int checked = DBRequests.checkLogin(sbUname.toString(), sbPass.toString());
        
        if (checked == 0)
        {
            Alert wrongStuff = new Alert(Alert.AlertType.ERROR, "Username and / or password is incorrect, "
                    + "check your spelling and try again", ButtonType.OK);
            wrongStuff.show(); 
        }
        else 
        {
//            SceneGenerator.dashboardScreen(myScheduler);
            myScheduler.dashboardScreen(checked);
        }
        
    }
    
    public void newUserClicked()
    {
        startCreateUserLoginScreen();
    }
    
    public static void startCreateUserLoginScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/createNewUserScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            CreateNewUserSceneController createNewUserSceneController = loader.getController();
            createNewUserSceneController.setupCreateUserLogin(myScheduler);
            Scene createLoginScene = new Scene(mainPane);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(BusinessScheduler.primaryStage);
            dialog.setScene(createLoginScene);
            dialog.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        
    }
    
}
