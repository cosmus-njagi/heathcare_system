package viewdoctor;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dbutil.ConnectDB;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
public class Patientcontroler implements Initializable{
    @FXML
    private Label pid,pname,pparent,pmobile,page,pgender,pdate,lpayement,
    ldisplay,lamount,tpatient,lcharges,lksh,lbalance,lnamebal;
    @FXML
    private JFXTextField tid,tname,tparent,tmobile,tage;
    @FXML
    private ComboBox<String> cgender;
    @FXML
    private DatePicker tarehe;
    @FXML
    private Separator s1,s2,s3,s4,s5;
    @FXML
    private TextField tamount;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton bprint,submit,home,about,log;
    @FXML
    private JFXRadioButton rcach,rmpesa,rairtel,requitel;
    @FXML
    private ToggleGroup payement;
    @FXML
    private GridPane grid;
    @FXML
    private Pane colorlesspane;
    @FXML
    private AnchorPane anchorpane;
    ObservableList<String>list1=FXCollections.observableArrayList("Male","Female","Intersex");
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cgender.setItems(list1);
		new ConnectDB();
		menushow();
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
	
	 public void radioevent(ActionEvent e) {
	     	if(rcach.isSelected()) {
	     		ldisplay.setText("***********Procedure***********"
	     				+"\n"+ "1.Enter amount and continue"
	     				+ "\n"+"2.THANKYOU");
	     				
	     	}
     	if(rmpesa.isSelected()) {
     		ldisplay.setText("***********Procedure***********"
     				+"\n"+ "1.open M-pesa menu"
     				+ "\n"+"2.Select lipa na M-pesa"
     				+"\n"+"3.Select Paybill option"
     				+"\n"+"4.Enter business no. 206206"
     				+"\n"+"5.Enter account no. RG67N"
     				+"\n"+"6.Enter amount "
     				+"\n"+"7.Enter pin and send");
     	}
     	if(rairtel.isSelected()) {
     		ldisplay.setText("***********Procedure***********"
     				+"\n"+ "1.open Airtel money menu"
     				+ "\n"+"2.Select make payments"
     				+"\n"+"3.Select Paybill option"
     				+"\n"+"4.Enter business name ntsa"
     				+"\n"+"5.Enter amount "
     				+"\n"+"6.Enter pin and send"
                      );
     }
     	if(requitel.isSelected()) {
     		ldisplay.setText("***********Procedure***********"
     				+"\n"+ "1.open T-kash menu"
     				+ "\n"+"2.Select paybill"
     				+"\n"+"3.select make payments"
     				+"\n"+"4.Enter business no. 206206"
     				+"\n"+"5.Enter account no. RG67N"
     				+"\n"+"6.Enter amount "
     				+"\n"+"7.Enter pin and send");
 }
     	
}
	 private void delete() {
		 tid.setText("");
		 tname.setText("");
		 tparent.setText("");
		 tmobile.setText("");
		 tage.setText("");
		 tamount.setText("");
		 ldisplay.setText("");
		 lnamebal.setText("");
		 tarehe.getEditor().setText(null);
		 cgender.setValue(null);
	 }
	 public void amount(KeyEvent event2) {
		 if(!(ldisplay.getText().isEmpty())) {
		try {
			double b=200;
			double a=Double.parseDouble(tamount.getText());
			double c=a-b;
				lnamebal.setText("Ksh"+String.valueOf(c));
			}catch(Exception ex3) {
				tamount.setText("");
			}
		}else {
				Alert alert=new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText(null);
				alert.setContentText("Please select mode of payement first!");
				alert.show();
				return;
			}
	 }
		public void homepage(ActionEvent event1) throws IOException {
			
			Parent root1=FXMLLoader.load(getClass().getResource("Homepage.fxml"));
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
public void submitbtn(ActionEvent event1) throws IOException {

	try {
	//Add patient into the database
	String midno=tid.getText();
	String mtname=tname.getText().toUpperCase();
	String mparent=tparent.getText().toUpperCase();
	String mmobile=tmobile.getText();
	String mage=tage.getText().toUpperCase();
	String mamount=tamount.getText();
	String gender=cgender.getValue();
	String mdate=tarehe.getEditor().getText().toString();
	if(midno.isEmpty()||mtname.isEmpty()||mparent.isEmpty()||mmobile.isEmpty()||mage.isEmpty()
			||mamount.isEmpty()||gender.isEmpty()||mdate.isEmpty()) {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Fields must not be empty");
		alert.show();
		return;
	}else {
	
	String insertquery="INSERT INTO patient(idno,pname,gname,mobile,age,gender,date,amount)VALUES(?,?,?,?,?,?,?,?)";
	Connection conn=ConnectDB.getConnections();
	PreparedStatement pst=conn.prepareStatement(insertquery);
	pst.setString(1, midno);
	pst.setString(2, mtname);
	pst.setString(3, mparent);
	pst.setString(4, mmobile);
	pst.setString(5, mage);
	pst.setString(6, gender);
	pst.setString(7,mdate);
	pst.setString(8, mamount);
	pst.execute();
	delete();
	
	//Set confirmation alert
	Alert alert=new Alert(Alert.AlertType.INFORMATION);
	alert.setHeaderText(null);
	alert.setTitle("Success");
	alert.setContentText("Patient data saved");
	alert.show();
	Parent root=FXMLLoader.load(getClass().getResource("Receipt.fxml"));
  	Stage stage=new Stage();
	stage.setResizable(false);
	Scene scene= new Scene(root);
	stage.setScene(scene);
	stage.show();	
	return;
	
	}
	}catch(Exception exsq) {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Failed!");
		alert.setContentText("Make sure the server is open");
		alert.show();
	exsq.printStackTrace();
	return;

	}


}	
     }
    

