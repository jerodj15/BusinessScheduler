package Scenes;

import Database.DBRequests;
import Main.BusinessScheduler;
import Objects.Appointment;
import Objects.ApptFee;
import Objects.Client;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class ReportSceneController {

    @FXML
    private Button backButton;
    @FXML
    private Label reportLabel;
    @FXML
    private TabPane reportsTabPane;
    @FXML
    private Tab serviceFeesTab;
    @FXML
    private DatePicker startDP;
    @FXML
    private DatePicker endDP;
    @FXML
    private Label servicesTimeLabel;
    @FXML
    private Button serviceGoButton;
    @FXML
    private BarChart<String, Double> serviceFeesBarChart;
    @FXML
    private Tab productivityTimeLabel;
    @FXML
    private DatePicker startProductivityDP;
    @FXML
    private DatePicker endProductivityDP;
    @FXML
    private Label p;
    @FXML
    private Button productivityGoButton;
    @FXML
    private PieChart productivityPieChart;
    @FXML
    private Label productivityLabel;
    @FXML
    private Tab clientVisitsTab;
    @FXML
    private DatePicker startVisitsDP;
    @FXML
    private DatePicker endVisitsDP;
    @FXML
    private Label visitTimeframeLabel;
    @FXML
    private Button clientGoButton;
    @FXML
    private Label visitationLabel;
    @FXML
    private TableView<?> visitsTV;
    @FXML
    private TableColumn<?, ?> serviceNameColumn;
    @FXML
    private TableColumn<?, ?> numberPerformedColumn;
    @FXML
    private TableColumn<?, ?> averageColumn;
    @FXML
    private Button exportButton;
    @FXML
    private Button printButton;

    private BusinessScheduler myBusinessScheduler;
    private String serviceStart, serviceEnd, prodStart, prodEnd, clientStart, clientEnd;
    private static DateTimeFormatter fullDTF = DateTimeFormatter.ofPattern("MM" + "/" + "dd" + "/" + "yyyy");
    
    public void setupReportScreen(BusinessScheduler scheduler, int userId)
    {
        this.myBusinessScheduler = scheduler;
    }
    
    public void backButtonPressed()
    {
        SceneGenerator.dashboardScreen(myBusinessScheduler);
    }
    
    public String convertDate(TemporalAccessor userIn)
    {
        String result = "";
        result = fullDTF.format(userIn);
        return result;
    }
    
    // Methods for the Service / Fees Reports Tab
    
    public void goButtonPressed()
    {
        serviceStart = convertDate(startDP.getValue());
        serviceEnd = convertDate(endDP.getValue());
        List<ApptFee> apptFees = DBRequests.getServicesAndFeesByDate(DashboardSceneController.getUserID(), serviceStart, serviceEnd);
        List<String> apptFeeStrings = new ArrayList<>();
        List<Double> apptFeeAmounts = new ArrayList<>();
        int numDifferentAppts = 0;
        for (int i = 0; i < apptFees.size(); i++)
        {
            if (apptFeeStrings.isEmpty())
            {
                apptFeeStrings.add(apptFees.get(i).getTitle());
                apptFeeAmounts.add(Double.parseDouble(apptFees.get(i).getAmount()));
                numDifferentAppts++;
            }
            else
            {
                if (apptFeeStrings.contains(apptFees.get(i).getTitle()))
                {
                    int stringIndex = apptFeeStrings.indexOf(apptFees.get(i).getTitle());
                    apptFeeAmounts.set(stringIndex, apptFeeAmounts.get(stringIndex) + Double.parseDouble(apptFees.get(i).getAmount()));
                    
                }
                else
                {
                    apptFeeStrings.add(apptFees.get(i).getTitle());
                    apptFeeAmounts.add(Double.parseDouble(apptFees.get(i).getAmount()));
                    numDifferentAppts++;
                }
            }
        }
        serviceFeesBarChart.getXAxis().setLabel("Service");
        serviceFeesBarChart.getYAxis().setLabel("Amount");
        List<XYChart.Series> seriesList = new ArrayList<>();
        double totalFees = 0.00;
        if (apptFeeStrings.size() != apptFeeAmounts.size())
        {
            System.out.println("Something went wrong");
        }
        else
        {
            for (int i = 0; i < apptFeeStrings.size(); i ++)
            {
                XYChart.Series series1 = new XYChart.Series<>();
                series1.getData().add(new XYChart.Data("",apptFeeAmounts.get(i)));
                series1.setName(apptFeeStrings.get(i));
                totalFees += apptFeeAmounts.get(i);
                serviceFeesBarChart.getData().add(series1);
            }
        }
        XYChart.Series totalSeries = new XYChart.Series<>();
        totalSeries.setName("Total");
        totalSeries.getData().add(new XYChart.Data("", totalFees));
        serviceFeesBarChart.getData().add(totalSeries);
    }
    
    public void exportButtonClicked()
    {
        String savePath = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        File selectedDirectory = fileChooser.showSaveDialog(BusinessScheduler.primaryStage);
        System.out.println(selectedDirectory.getAbsolutePath());
        saveAsPng(serviceFeesBarChart, selectedDirectory.getAbsolutePath());
    }
    
    public void saveAsPng(BarChart userChart, String path)
    {
        WritableImage image = userChart.snapshot(new SnapshotParameters(), null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    // Methods for Productivity Reports
    
    public void prodGoButtonPressed()
    {
        prodStart = convertDate(startProductivityDP.getValue());
        prodEnd = convertDate(endProductivityDP.getValue());
        List<Appointment> userAppointments = DBRequests.getAppointmentsByUserID(DashboardSceneController.getUserID());
        List<Client> apptClients = new ArrayList<>();
        List<String> clientNames = new ArrayList<>();
        
        
    }
    
}
