package viewdoctor;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import dbutil.ConnectDB;
import java.sql.*;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.Button;
public class Viewdoctorcontroler implements Initializable{
    @FXML 
    private  TableView<Doctor>doctortable;
    @FXML
    private JFXButton Bdelete;
    @FXML
    private Pane pane1;
    @FXML
    private JFXHamburger menu;
    @FXML
    private VBox vbox1;
    @FXML
    private Button logout;
    @FXML
    private AnchorPane anchorpane;
	@FXML
	private TableColumn<Doctor,String>idcol;
	@FXML
	private TableColumn<Doctor,String>firstcol;
	@FXML
	private TableColumn<Doctor,String>lastcol;
	@FXML
	private TableColumn<Doctor,String>mobilecol;
	@FXML
	private TableColumn<Doctor,String>emailcol;
    @FXML
    private TextField Tidno;
	static ObservableList<Doctor>list=FXCollections.observableArrayList();
	 private static final int MAX_SIZE=8;
	 private static final int MAX_SIZE1=10;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initcol();
		new ConnectDB();
		loadData();
		filtertable();
        animate();
        numberlimit();
}
	public void logout(ActionEvent e1) throws Exception {
			Parent root=FXMLLoader.load(getClass().getResource("Loginsrn.fxml"));
      		Stage stage=new Stage();
    		stage.setResizable(false);
    		Scene scene= new Scene(root);
    		stage.setScene(scene);
      		stage.show();	
    		//Hide previos window
    		((Node)e1.getSource()).getScene().getWindow().hide();
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
	public void homepage(ActionEvent event1) throws IOException {
		
		Parent root1=FXMLLoader.load(getClass().getResource("Homepage.fxml"));
		anchorpane.getChildren().setAll(root1);
		refreshTable();
	}
	/**************************************************************
	      SET NUMBER LIMIT FOR THE TEXT FIELDS
	 **************************************************************
	 */
    public void numberlimit() {
	//Set maximum number limit.
	idno.lengthProperty().addListener(new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable,
                Number oldValue, Number newValue) {
            if (newValue.intValue() > oldValue.intValue()) {
                // Check if the new character is greater than MAX_SIZE
                if (idno.getText().length() >= MAX_SIZE) {
  // if it's 9th character then setText to previous one
                	idno.setText(idno.getText().substring(0, MAX_SIZE));
                }
            }
        }
    });
	
	//Set maximum number limit.
	mobileno.lengthProperty().addListener(new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable,
                Number oldValue, Number newValue) {
            if (newValue.intValue() > oldValue.intValue()) {
                // Check if the new character is greater than MAX_SIZE
                if (mobileno.getText().length() >= MAX_SIZE1) {
  // if it's 11th character then setText to previous one
                	mobileno.setText(mobileno.getText().substring(0, MAX_SIZE1));
                }
            }
        }
    });
}
    /***************************************************
       					ANIMATIONS
     ***************************************************
     */
	private void animate() {
	pane1.setVisible(false);
	
	TranslateTransition translate=new TranslateTransition(Duration.millis(500),vbox1);
	translate.setByX(-600);
	translate.play();

	
	menu.setOnMouseClicked(event->{
		pane1.setVisible(true);
		pane1.setVisible(true);
		TranslateTransition translate1=new TranslateTransition(Duration.millis(500),vbox1);
		translate1.setByX(+600);
		translate1.play();
	});
	
		pane1.setOnMouseClicked(event->{
		pane1.setVisible(false);
		TranslateTransition translate1=new TranslateTransition(Duration.millis(500),vbox1);
		translate1.setByX(-600);
		translate1.play();
	});
	}
	public void refreshTable() {
		try {
		list.clear();
		loadData();
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void cleartextfield() {
		idno.setText("");
		fname.setText("");
		lname.setText("");
		mobileno.setText("");
		email.setText("");
	}
	/******************************************************
	 Setting data on the table view into text field
	 ******************************************************
	 */
	public void setonTF() {
try {
				Doctor user=(Doctor)doctortable.getSelectionModel().getSelectedItem();
				idno.setText(user.getid());
				fname.setText(user.getfname());
				lname.setText(user.getlname());
				mobileno.setText(user.getmobile());
				email.setText(user.getemail());
}catch(Exception e) {
	e.printStackTrace();
}
	}
/*************************************************
 	SEARCHING DATA ON THE TABLE
 *************************************************
 */
	public void filtertable() {
	FilteredList<Doctor>filteredData=new FilteredList<>(list,ev -> true);
	Tidno.setOnKeyReleased(ev->{
		Tidno.textProperty().addListener((observableValue,oldValue,newValue)->{
			filteredData.setPredicate((Predicate<? super Doctor>)doctor->{
				if(newValue==null||newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(doctor.getid().contains(newValue)) {
					return true;
				}else if(doctor.getfname().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				else if(doctor.getlname().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		});
		SortedList<Doctor>sortedData=new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(doctortable.comparatorProperty());
		doctortable.setItems(sortedData);
	});
}
	/***************************************************
	 	LOADING DATA ON THE TABLE FROM DATABASE
	 ***************************************************
	 */
		
	public void initcol() {
		idcol.setCellValueFactory(cellData->cellData.getValue().idProperty());
		firstcol.setCellValueFactory(cellData->cellData.getValue().fnameProperty());
		lastcol.setCellValueFactory(cellData->cellData.getValue().lnameProperty());
		mobilecol.setCellValueFactory(cellData->cellData.getValue().mobileProperty());
		emailcol.setCellValueFactory(cellData->cellData.getValue().emailProperty());
	}
	
	public void loadData() {
		try {
		String loadquery="SELECT*FROM doctor";
		Connection conn=ConnectDB.getConnections();
		PreparedStatement pst=conn.prepareStatement(loadquery);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			String id=rs.getString("id");
			String fname=rs.getString("fname");
			String lname=rs.getString("lname");
			String mobile=rs.getString("mobile");
			String email=rs.getString("email");
			list.add(new Doctor(id,fname,lname,mobile,email));
		}
		pst.close();
		rs.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(Viewdoctorcontroler.class.getName()).log(Level.SEVERE,null,ex);
		}
		doctortable.getItems().setAll(list);
	}

	public static class Doctor{
		private final SimpleStringProperty id;
		private final SimpleStringProperty fname;
		private final SimpleStringProperty lname;
		private final SimpleStringProperty mobile;
		private final SimpleStringProperty email;
		public Doctor(String id,String fname,String lname,String mobile,String email) {
			this.id=new SimpleStringProperty(id);
			this.fname=new SimpleStringProperty(fname);
			this.lname=new SimpleStringProperty(lname);
			this.mobile=new SimpleStringProperty (mobile);
			this.email=new SimpleStringProperty (email);
		}
		//set id
		public  String getid() {
			return id.get();
		}
		public void setId(String id) {
			this.id.set(id);
		}
		public StringProperty idProperty() {
			return id;
		}
		//set first name
		public String getfname() {
			return fname.get();
		}
		public void setFname(String fname) {
			this.fname.set(fname);
		}
		public StringProperty fnameProperty() {
			return fname;
		}
		//set last name
		public String getlname() {
			return lname.get();
		}
		public void setLname(String lname) {
			this.fname.set(lname);
		}
		public StringProperty lnameProperty() {
			return lname;
		}
		
		//set mobile
		public String getmobile() {
			return mobile.get();
		}
		public void setMobile(String mobile) {
			this.mobile.set(mobile);
		}
		public StringProperty mobileProperty() {
			return mobile;
		}
		
		//set email
		public String getemail() {
			return email.get();
		}
		
		public void setEmail(String email) {
			this.email.set(email);
		}
		public StringProperty emailProperty() {
			return email;
		}
	
	}
/***************************************************
 DATABASE OPERATIONS
 ***************************************************
 */
	@FXML 
	private JFXTextField idno,fname,lname,mobileno,email;
	//Add doctor into the database
		public void adddoctor(ActionEvent e){
		String midno=idno.getText();
		String mfname=fname.getText().toUpperCase();
		String mlname=lname.getText().toUpperCase();
		String mmobileno=mobileno.getText();
		String memail=email.getText().toUpperCase();
		
		if(mfname.isEmpty()||mlname.isEmpty()||mmobileno.isEmpty()||midno.isEmpty()||memail.isEmpty()) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Fields must not be empty");
			alert.show();
			return;
		}
		try {
		String insertquery="INSERT INTO doctor(id,fname,lname,mobile,email)VALUES(?,?,?,?,?)";
		Connection conn=ConnectDB.getConnections();
		PreparedStatement pst=conn.prepareStatement(insertquery);
		pst.setString(1, midno);
		pst.setString(2, mfname);
		pst.setString(3, mlname);
		pst.setString(4, mmobileno);
		pst.setString(5, memail);
		pst.execute();
		refreshTable();
		cleartextfield();
		//Set confirmation alert
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Success");
		alert.setContentText("Doctor added successfully");
		alert.show();
		return;
	}catch(SQLException exsq) {
		Alert alert=new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("ID number already exists");
		alert.show();
		return;
	}
		}
	//update data in the database.
	public void update(ActionEvent e3) {
		String midno=idno.getText();
		String mfname=fname.getText();
		String mlname=lname.getText();
		String mmobileno=mobileno.getText();
		String memail=email.getText();
		if(mfname.isEmpty()||mlname.isEmpty()||mmobileno.isEmpty()||midno.isEmpty()||memail.isEmpty()) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Fields must not be empty");
			alert.show();
			return;
		}
			try {
			String updatequery="UPDATE doctor SET fname=?, lname=?, mobile=?, email=? WHERE id=?";
    		Connection conn=ConnectDB.getConnections();
    		PreparedStatement pst=conn.prepareStatement(updatequery);
   			pst.setString(1,fname.getText().toUpperCase());
			pst.setString(2,lname.getText().toUpperCase());  
			pst.setString(3,mobileno.getText());
			pst.setString(4,email.getText().toUpperCase());
			pst.setString(5,idno.getText());
			int i=pst.executeUpdate();
			if(i>0) {
				setonTF();
				refreshTable();
				cleartextfield();
				//set success alert
				Alert alert=new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("Doctor successfully updated.");
				alert.show();
				return;
			}

			else {
				//set failure alert
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("The id number does not exist.");
				alert.show();
				return;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Can't update empty fields");
			alert.show();
			return;
		}

	}
	
	//Delete from database
public void deletedoctor(ActionEvent event) {
	String midno=idno.getText();
	String mfname=fname.getText();
	String mlname=lname.getText();
	String mmobileno=mobileno.getText();
	String memail=email.getText();
	if(mfname.isEmpty()||mlname.isEmpty()||mmobileno.isEmpty()||midno.isEmpty()||memail.isEmpty()) {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Select an item to delete");
		alert.show();
		return;
	}
Alert alert1=new Alert(Alert.AlertType.CONFIRMATION);
alert1.setTitle("PLEASE CONFIRM.");
alert1.setHeaderText(null);
alert1.setContentText("Are you sure you want to permanently delete " + fname.getText() +
		" "+lname.getText()+","+"\n"+"id= " + idno.getText()+" ?");
Optional<ButtonType>type=
alert1.showAndWait();
if(type.get()==ButtonType.OK) {
	
				try {
String sql="DELETE FROM doctor WHERE id=?";
Connection conn=ConnectDB.getConnections();
PreparedStatement pst=conn.prepareStatement(sql);
pst.setString(1,idno.getText());
pst.executeUpdate();
pst.close();
refreshTable();

}
		catch(SQLException ex) {
Logger.getLogger(Viewdoctorcontroler.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
		cleartextfield();	
  }
	
}
