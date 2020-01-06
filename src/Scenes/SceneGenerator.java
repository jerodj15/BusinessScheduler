package Scenes;

import Main.BusinessScheduler;
import static Main.BusinessScheduler.primaryStage;
import static Main.BusinessScheduler.secondStage;
import Objects.ApptFee;
import Objects.SubTodo;
import Objects.Todo;
import static Scenes.AppointmentFeeSceneController.userSet;
import static Scenes.TodoSceneController.todoId;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class SceneGenerator {
    
    private static BusinessScheduler myBusinessScheduler;
    
    // Initial Screen Loaders
    public static void dashboardScreen(BusinessScheduler scheduler)
    {
        
        myBusinessScheduler = scheduler;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/dashboardScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            DashboardSceneController dashboardSceneController = loader.getController();
            dashboardSceneController.setupDash(SceneGenerator.myBusinessScheduler, DashboardSceneController.getUserID());
           Scene loginScene = new Scene(mainPane);
           primaryStage.setTitle("Welcome, User");
           primaryStage.setScene(loginScene);
           primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public static void loginScreen(BusinessScheduler scheduler) 
    {
        myBusinessScheduler = scheduler;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/loginScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            LoginSceneController loginSceneController = loader.getController();
            loginSceneController.setupLoginScene(SceneGenerator.myBusinessScheduler);
           Scene loginScene = new Scene(mainPane);
           primaryStage.setTitle("Welcome, Please Login");
           primaryStage.setScene(loginScene);
           primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public static void anotherLoginScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/loginScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            LoginSceneController loginSceneController = loader.getController();
            loginSceneController.setupLoginScene(SceneGenerator.myBusinessScheduler);
           Scene loginScene = new Scene(mainPane);
           primaryStage.setTitle("Welcome, Please Login");
           primaryStage.setScene(loginScene);
           primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public static void newUserScreen(int numSetting)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/newUserScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            NewUserSceneController newUserSceneController = loader.getController();
            newUserSceneController.setupNewUserScene(numSetting);
            Scene createUserScene = new Scene(mainPane);
            primaryStage.setTitle("Create a new user");
            primaryStage.setScene(createUserScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void reportScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/reportScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            ReportSceneController reportSceneController = loader.getController();
            reportSceneController.setupReportScreen(myBusinessScheduler, DashboardSceneController.getUserID());
            Scene reportScene = new Scene(mainPane);
            primaryStage.setTitle("Reports");
            primaryStage.setScene(reportScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void clientListingScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/clientListing.fxml"));
            Pane mainPane = (Pane) loader.load();
            ClientListingController clientListingController = loader.getController();
            clientListingController.setupClientListing(SceneGenerator.myBusinessScheduler);
            Scene clientScene = new Scene(mainPane);
            primaryStage.setTitle("Client Rolodex");
            primaryStage.setScene(clientScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void appointmentScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/appointmentScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            AppointmentSceneController appointmentSceneController = loader.getController();
            appointmentSceneController.setupAppointmentScene(SceneGenerator.myBusinessScheduler, userSet);
            Scene appointmentScene = new Scene(mainPane);
            primaryStage.setTitle("New Appointment");
            primaryStage.setScene(appointmentScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void sideMenuShown()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/sideMenu.fxml"));
            Pane sidePane = (Pane) loader.load();
            SideMenuController sideMenuController = loader.getController();
            sideMenuController.setupMenu(myBusinessScheduler);
            primaryStage.setOpacity(0.5);
            Scene sideMenuScene = new Scene(sidePane);
            FadeTransition openSideMenu = new FadeTransition(Duration.seconds(0.5), sidePane);
            openSideMenu.setFromValue(0);
            openSideMenu.setToValue(1);
            openSideMenu.play();
            secondStage.setScene(sideMenuScene);
            secondStage.setOpacity(1.0);
            secondStage.setOnCloseRequest((event) -> {
            primaryStage.setOpacity(1.0);
            });
            secondStage.show();
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void hideClicked()
    {
        try {
            BusinessScheduler.primaryStage.setOpacity(1.0);
            BusinessScheduler.secondStage.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
    }
    
    // Side Menu Screen Commands+++++++++++++++++++++++++++++++++++++++++++
    
    public static void newClientScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/clientScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            ClientSceneController clientSceneController = loader.getController();
            clientSceneController.setupClient(SceneGenerator.myBusinessScheduler);
           Scene clientScene = new Scene(mainPane);
           primaryStage.setTitle("New Client");
           primaryStage.setScene(clientScene);
           primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public static void clientDetailsScreen(int clientID)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/clientScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            ClientSceneController clientSceneController = loader.getController();
            clientSceneController.setupClient(myBusinessScheduler, clientID);
            Scene clientScene = new Scene(mainPane);
            primaryStage.setTitle("Client Details");
            primaryStage.setScene(clientScene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void newAppointmentScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/appointmentScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            AppointmentSceneController appointmentSceneController = loader.getController();
            appointmentSceneController.setupAppointmentScene(SceneGenerator.myBusinessScheduler, 0);
            Scene appointmentScene = new Scene(mainPane);
            primaryStage.setTitle("New Appointment");
            primaryStage.setScene(appointmentScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void newTodoScreen()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/todoScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            TodoSceneController todoSceneController = loader.getController();
            todoSceneController.setupTodoScene(SceneGenerator.myBusinessScheduler, 0);
            Scene todoScene = new Scene(mainPane);
            primaryStage.setTitle("New Todo");
            primaryStage.setScene(todoScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void appointmentFeeScreen(int userSetup)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/appointmentFeeScene.fxml"));
            Pane sidePane = (Pane) loader.load();
            AppointmentFeeSceneController appointmentFeeSceneController = loader.getController();
            appointmentFeeSceneController.setupAptFeeScene(myBusinessScheduler, userSetup);
            primaryStage.setOpacity(0.5);
            Scene sideMenuScene = new Scene(sidePane);
            FadeTransition openSideMenu = new FadeTransition(Duration.seconds(0.5), sidePane);
            openSideMenu.setFromValue(0);
            openSideMenu.setToValue(1);
            openSideMenu.play();
            secondStage.setScene(sideMenuScene);
            secondStage.setOpacity(1.0);
            secondStage.setOnCloseRequest((event) -> {
            primaryStage.setOpacity(1.0);
            });
            secondStage.show();
            
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void appointmentFeeScreen(ApptFee userFee, int userSetup)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/appointmentFeeScene.fxml"));
            Pane sidePane = (Pane) loader.load();
            AppointmentFeeSceneController appointmentFeeSceneController = loader.getController();
            appointmentFeeSceneController.setupAptFeeScene(myBusinessScheduler, userFee, userSetup);
            primaryStage.setOpacity(0.5);
            Scene sideMenuScene = new Scene(sidePane);
            FadeTransition openSideMenu = new FadeTransition(Duration.seconds(0.5), sidePane);
            openSideMenu.setFromValue(0);
            openSideMenu.setToValue(1);
            openSideMenu.play();
            secondStage.setScene(sideMenuScene);
            secondStage.setOpacity(1.0);
            secondStage.setOnCloseRequest((event) -> {
            primaryStage.setOpacity(1.0);
            });
            secondStage.show();
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void showTodoFeeScene(Todo userIn)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/todoFeeScene.fxml"));
            Pane subPane = (Pane) loader.load();
            Scene subtodoScene = new Scene(subPane);
            TodoFeeSceneController todoFeeSceneController = loader.getController();
            todoFeeSceneController.setupTodoFeeScene(myBusinessScheduler, todoId);
            BusinessScheduler.secondStage.setScene(subtodoScene);
            BusinessScheduler.secondStage.show();
            FadeTransition openSideMenu = new FadeTransition(Duration.seconds(0.5), subPane);
            openSideMenu.setFromValue(0);
            openSideMenu.setToValue(1);
            openSideMenu.play();
            secondStage.setScene(subtodoScene);
            secondStage.setOpacity(1.0);
            primaryStage.setOpacity(0.5);
            secondStage.setOnCloseRequest((event) -> {
            primaryStage.setOpacity(1.0);
            });
            secondStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Details on different elements ++++++++++++++++++++++++++++++++++++++++
    public static void showAppointmentDetails(int appointmentID)
    {
       
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/appointmentScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            AppointmentSceneController appointmentSceneController = loader.getController();
            appointmentSceneController.setupAppointmentScene(myBusinessScheduler, appointmentID);
            Scene appointmentScene = new Scene(mainPane);
            primaryStage.setTitle("New Appointment");
            primaryStage.setScene(appointmentScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    
    }
    
    public static void showTodoDetails(int todoID)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/todoScene.fxml"));
            Pane mainPane = (Pane) loader.load();
            TodoSceneController todoSceneController = loader.getController();
            todoSceneController.setupTodoScene(myBusinessScheduler, todoID);
            Scene todoScene = new Scene(mainPane);
            primaryStage.setTitle("Edit Todo");
            primaryStage.setScene(todoScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void showSubTodoDetails(SubTodo userIn)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/subtodoScene.fxml"));
            Pane subPane = (Pane) loader.load();
            Scene subtodoScene = new Scene(subPane);
            SubtodoSceneController subtodoSceneController = loader.getController();
            subtodoSceneController.setupSubTodoScene(myBusinessScheduler, userIn);
            BusinessScheduler.secondStage.setScene(subtodoScene);
            BusinessScheduler.secondStage.show();
            FadeTransition openSideMenu = new FadeTransition(Duration.seconds(0.5), subPane);
            openSideMenu.setFromValue(0);
            openSideMenu.setToValue(1);
            openSideMenu.play();
            secondStage.setScene(subtodoScene);
            secondStage.setOpacity(1.0);
            primaryStage.setOpacity(0.5);
            secondStage.setOnCloseRequest((event) -> {
            primaryStage.setOpacity(1.0);
            });
            secondStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
   // Adding new subitems +++++++++++++++++++++++++++++++++++++++++++++++++++
    
    public static void setupSideMenu()
    {
        BusinessScheduler.secondStage.setX(BusinessScheduler.primaryStage.getX() + BusinessScheduler.primaryStage.getWidth() - 205);
        BusinessScheduler.secondStage.setY(BusinessScheduler.primaryStage.getY());
        BusinessScheduler.secondStage.setResizable(false);
    }
    public static void addSubTodoClicked()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BusinessScheduler.class.getResource("/Scenes/subtodoScene.fxml"));
            Pane subPane = (Pane) loader.load();
            Scene subtodoScene = new Scene(subPane);
            SubtodoSceneController subtodoSceneController = loader.getController();
            subtodoSceneController.setupSubTodoScene(myBusinessScheduler, todoId);
            BusinessScheduler.secondStage.setScene(subtodoScene);
            BusinessScheduler.secondStage.show();
            FadeTransition openSideMenu = new FadeTransition(Duration.seconds(0.5), subPane);
            openSideMenu.setFromValue(0);
            openSideMenu.setToValue(1);
            openSideMenu.play();
            secondStage.setScene(subtodoScene);
            secondStage.setOpacity(1.0);
            primaryStage.setOpacity(0.5);
            secondStage.setOnCloseRequest((event) -> {
            primaryStage.setOpacity(1.0);
            });
            secondStage.show();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        
    }

}
