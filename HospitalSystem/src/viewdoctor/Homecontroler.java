package viewdoctor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
public class Homecontroler implements Initializable{
    @FXML
    private Pane pane1,pane2,pane3,pane4,colorlesspane,loader;
    @FXML
    private JFXButton b1,b2,b3,b4,b5,b6;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private GridPane grid;
    @FXML
    private JFXProgressBar progress;
    @FXML
    private AnchorPane anchorpane;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 backgroundanimation();
		 menushow();
			}
	public void backgroundanimation() {
		FadeTransition fade1=new FadeTransition(Duration.millis(20000),pane4);
		fade1.setFromValue(1);
		fade1.setToValue(0);
		fade1.play();
		
		fade1.setOnFinished(event->{
			FadeTransition fade2=new FadeTransition(Duration.millis(10000),pane3);
			fade2.setFromValue(1);
			fade2.setToValue(0);
			fade2.play();
		
			fade2.setOnFinished(event1->{
			FadeTransition fade3=new FadeTransition(Duration.millis(10000),pane2);
			fade3.setFromValue(1);
			fade3.setToValue(0);
			fade3.play();

		});	
		});

	}
	
	public void menushow() {
colorlesspane.setVisible(false);
		
		TranslateTransition translate=new TranslateTransition(Duration.millis(500),grid);
		translate.setByX(-600);
		translate.play();
	
		
		hamburger.setOnMouseClicked(event->{
			colorlesspane.setVisible(true);
			grid.setVisible(true);
			TranslateTransition translate1=new TranslateTransition(Duration.millis(500),grid);
			translate1.setByX(+600);
			translate1.play();
		});
		
    		colorlesspane.setOnMouseClicked(event->{
			colorlesspane.setVisible(false);
			TranslateTransition translate1=new TranslateTransition(Duration.millis(500),grid);
			translate1.setByX(-600);
			translate1.play();
		});
	}
	
		public void doctorclick(ActionEvent event1) throws IOException {
		
		Parent root1=FXMLLoader.load(getClass().getResource("Viewdoctors.fxml"));
		anchorpane.getChildren().setAll(root1);
	}

	public void patientclick(ActionEvent event2) throws IOException {
		Parent root1=FXMLLoader.load(getClass().getResource("Patient.fxml"));
		anchorpane.getChildren().setAll(root1);
	}
	
	public void about(ActionEvent event3) throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("About.fxml"));
		Stage stage= new Stage(StageStyle.DECORATED);
		stage.setResizable(false);
		Image icon=new Image(getClass().getResourceAsStream("ecitizen.png"));
		Scene scene=new Scene(root);
		stage.getIcons().add(icon);
		stage.setScene(scene);
		stage.setTitle("Health care system");
		stage.show();
}
	
	public void logout(ActionEvent event3) throws IOException {
	Parent root=FXMLLoader.load(getClass().getResource("Loginsrn.fxml"));
	Stage stage= new Stage();
	Scene scene=new Scene(root);
	stage.setScene(scene);
	stage.show();
	//Hide previos window
	((Node)event3.getSource()).getScene().getWindow().hide();
}
}