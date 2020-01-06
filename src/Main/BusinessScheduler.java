package Main;

import Scenes.DashboardSceneController;
import Scenes.LoginSceneController;
import Scenes.SceneGenerator;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BusinessScheduler extends Application {
    
    public static Stage primaryStage;
    public static Stage secondStage = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupSecondary();
        SceneGenerator.loginScreen(this);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    // Setup secondary stage stuff
    public void setupSecondary()
    {
        this.secondStage.initStyle(StageStyle.UNDECORATED);
        this.secondStage.initModality(Modality.APPLICATION_MODAL);
        this.secondStage.setX(primaryStage.getX() + primaryStage.getWidth() - 205);
        this.secondStage.setY(primaryStage.getY());
        this.secondStage.setResizable(false);
        
    }
    
    public static void reloadLoginScreen(BusinessScheduler scheduler)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/loginScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            LoginSceneController loginSceneController = loader.getController();
            loginSceneController.setupLoginScene(scheduler);
           Scene loginScene = new Scene(mainPane);
           primaryStage.setTitle("Welcome, Please Login");
           primaryStage.setScene(loginScene);
           primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public void loginScreen() 
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/loginScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            LoginSceneController loginSceneController = loader.getController();
            loginSceneController.setupLoginScene(this);
           Scene loginScene = new Scene(mainPane);
           primaryStage.setTitle("Welcome, Please Login");
           primaryStage.setScene(loginScene);
           primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public void dashboardScreen(int userID)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/dashboardScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            DashboardSceneController dashboardSceneController = loader.getController();
            dashboardSceneController.setupDash(this, userID);
           Scene loginScene = new Scene(mainPane);
           primaryStage.setTitle("Welcome, User");
           primaryStage.setScene(loginScene);
           primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
}
