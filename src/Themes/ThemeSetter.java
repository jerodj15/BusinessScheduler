/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Themes;

import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jerod
 */
public class ThemeSetter {
    
    public static AnchorPane setMainPane(AnchorPane userIn, int themeNumber)
    {
        AnchorPane result = userIn;
        switch(themeNumber)
        {
            case 0:
                setDarkBlueTheme(userIn);
        }
        return result;
    }
    
    public static GridPane setGridPane(GridPane userIn, int themeNumber)
    {
        GridPane result = userIn;
        switch(themeNumber)
        {
            case 0:
                setDarkBlueTheme(userIn);
        }
        return result;
    }
    
    public static TabPane setupTabPane(TabPane userIn, int themeNumber)
    {
        TabPane result = userIn;
        return result;
    }
    
    public static AnchorPane setDarkBlueTheme(AnchorPane methodIn)
    {
        AnchorPane result;
        System.out.println("Anchorpane info " + methodIn.getId());
        if (methodIn.getId().equalsIgnoreCase("smallpane"))
        {
            result = DarkBlueTheme.setDarkBlueSideMenu(methodIn);
        }
        else
        {
            result = DarkBlueTheme.setDarkBlueTheme(methodIn);
        }
        return result;
    }
    
    public static GridPane setDarkBlueTheme(GridPane methodIn)
    {
        GridPane result = methodIn;
        result = DarkBlueTheme.setDarkBlueTheme(result);
        return result;
    }
    
    public static TabPane setDarkBlueTheme(TabPane methodIn)
    {
        TabPane result;
        result = DarkBlueTheme.setDarkBlueTheme(methodIn);
        return result;
    }
    
}
