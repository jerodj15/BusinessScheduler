/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Themes;

import Main.BusinessScheduler;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author jerod
 */
public class DarkBlueTheme {
    
    public static AnchorPane setDarkBlueTheme(AnchorPane userPane)
    {
        String anchorimage = BusinessScheduler.class.getResource("/Themes/darkBlue.png").toExternalForm();
//        String tableImage = BusinessScheduler.class.getResource("/Resources/darkBlueTable.png").toExternalForm();
        Image tableImage = new Image("/Themes/darkBlue.png");
        BackgroundImage tableBackgroundImage = new BackgroundImage(tableImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        BackgroundFill buttonFill = new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background buttonBackground = new Background(buttonFill);
        AnchorPane result = userPane;
        result.setStyle("-fx-background-image: url('" + anchorimage +"'); " +
            "-fx-background-size: cover;");
        List<Node> paneChildList = result.getChildren();
        List<Button> buttonsList = new ArrayList<>();
        List<Label> labelsList = new ArrayList<>();
        List<TextField> textFieldsList = new ArrayList<>();
        List<TextArea> textAreasList = new ArrayList<>();
        List<TableView> tableViewsList = new ArrayList<>();
        
        for (Node item : paneChildList)
        {
            String buttonString = "button";
            String labelString = "label";
            String tableString = "table-view";
            if (item.getStyleClass().contains(buttonString))
            {
                buttonsList.add((Button) item);
            }
            if (item.getStyleClass().contains(labelString))
            {
                labelsList.add((Label) item);
            }
            if (item.getStyleClass().contains(tableString))
            {
                tableViewsList.add((TableView) item);
            }
            
        }
        
        for (Button button : buttonsList)
        {
            button.setBackground(buttonBackground);
        }
        for (Label label : labelsList)
        {
            label.setTextFill(Color.WHITESMOKE);
        }
        for (TableView tableView : tableViewsList)
        {
            tableView.setBackground(new Background(tableBackgroundImage));
        }
        return result;
    }
    
    public static GridPane setDarkBlueTheme(GridPane userPane)
    {
        GridPane result = userPane;
        String anchorimage = BusinessScheduler.class.getResource("/Themes/darkBlue.png").toExternalForm();
//        String tableImage = BusinessScheduler.class.getResource("/Resources/darkBlueTable.png").toExternalForm();
        Image tableImage = new Image("/Themes/darkBlue.png");
        BackgroundImage tableBackgroundImage = new BackgroundImage(tableImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        BackgroundFill buttonFill = new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background buttonBackground = new Background(buttonFill);
        result.setStyle("-fx-background-image: url('" + anchorimage +"'); " +
            "-fx-background-size: cover;");
        List<Node> paneChildList = result.getChildren();
        List<Button> buttonsList = new ArrayList<>();
        List<Label> labelsList = new ArrayList<>();
        List<TextField> textFieldsList = new ArrayList<>();
        List<TextArea> textAreasList = new ArrayList<>();
        List<TableView> tableViewsList = new ArrayList<>();
        
        for (Node item : paneChildList)
        {
            String buttonString = "button";
            String labelString = "label";
            String tableString = "table-view";
            if (item.getStyleClass().contains(buttonString))
            {
                buttonsList.add((Button) item);
            }
            if (item.getStyleClass().contains(labelString))
            {
                labelsList.add((Label) item);
            }
            if (item.getStyleClass().contains(tableString))
            {
                tableViewsList.add((TableView) item);
            }
            
        }
        
        for (Button button : buttonsList)
        {
            button.setBackground(buttonBackground);
        }
        for (Label label : labelsList)
        {
            label.setTextFill(Color.WHITESMOKE);
        }
        for (TableView tableView : tableViewsList)
        {
            tableView.setBackground(new Background(tableBackgroundImage));
        }
        return result;
    }
    
    public static AnchorPane setDarkBlueSideMenu(AnchorPane userPane)
    {
        AnchorPane result = userPane;
        String anchorImageString = BusinessScheduler.class.getResource("/Themes/darkBlueTable.png").toExternalForm();
        Image tableImage = new Image("/DarkBlueTheme/darkBlueTable.png");
        result.setStyle("-fx-background-image: url('" + anchorImageString +"'); " +
            "-fx-background-size: cover;");
        return result;
    }
    
    public static TabPane setDarkBlueTheme(TabPane userPane)
    {
        TabPane result = userPane;
        String anchorimage = BusinessScheduler.class.getResource("/Themes/darkBlue.png").toExternalForm();
//        String tableImage = BusinessScheduler.class.getResource("/Resources/darkBlueTable.png").toExternalForm();
        Image tableImage = new Image("/Themes/darkBlue.png");
        BackgroundImage tableBackgroundImage = new BackgroundImage(tableImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        BackgroundFill buttonFill = new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background buttonBackground = new Background(buttonFill);
        result.setStyle("-fx-background-image: url('" + anchorimage +"'); " +
            "-fx-background-size: cover;");
        List<Tab> paneChildList = result.getTabs();
        List<Button> buttonsList = new ArrayList<>();
        List<Label> labelsList = new ArrayList<>();
        List<TextField> textFieldsList = new ArrayList<>();
        List<TextArea> textAreasList = new ArrayList<>();
        List<TableView> tableViewsList = new ArrayList<>();
        List<TabPane> tabsList = new ArrayList<>();

        return result;
    }
    
}
