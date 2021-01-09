package viewdoctor;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Lgncontroler implements Initializable{

    @FXML
    private Pane dpane;

    @FXML
    private ImageView hospital;

    @FXML
    private Label lhospital;

    @FXML
    private JFXTextField jfxusername;
    @FXML
    private JFXPasswordField jfxpassword;
    @FXML
    private ImageView iusername;
    @FXML
    private ImageView ipassword;

    @FXML
    private Button signin;
    @FXML
    private ImageView logo;

    @FXML
    private Button signup;

    	@Override
    	public void initialize(URL arg0, ResourceBundle arg1) {
    		// TODO Auto-generated method stub
    		RotateTransition rt=new RotateTransition(Duration.seconds(15),logo);
    		 rt.setByAngle(9*360);
    		 rt.play();
    	}
    	public void loginbtn(ActionEvent e1) throws Exception {
    		if(jfxusername.getText().isEmpty()||jfxpassword.getText().isEmpty()) {
        		Alert alert=new Alert(Alert.AlertType.ERROR);
        		alert.setHeaderText(null);
        		alert.setTitle("Failed");
        		alert.setContentText("Check field(s) is empty!");
        		alert.show();
        		return;
    		}else {
    		if(jfxusername.getText().equals("admin")&&jfxpassword.getText().equals("123")) {
    			Parent root=FXMLLoader.load(getClass().getResource("Homepage.fxml"));
    			Stage stage= new Stage(StageStyle.DECORATED);
    			stage.setResizable(false);
    			Image icon=new Image(getClass().getResourceAsStream("ecitizen.png"));
    			Scene scene=new Scene(root);
    			stage.getIcons().add(icon);
    			stage.setScene(scene);
    			stage.setTitle("Health care system");
    			stage.show();
        		//Hide previos window
        		((Node)e1.getSource()).getScene().getWindow().hide();
    }
    		else {
    		Alert alert=new Alert(Alert.AlertType.WARNING);
    		alert.setHeaderText(null);
    		alert.setTitle("Failed");
    		alert.setContentText("Invalid Username or Password");
    		alert.show();
    		return;
      }
    	}
    		}
    	public void close_window() {
    		System.exit(0);
    	}

    }


