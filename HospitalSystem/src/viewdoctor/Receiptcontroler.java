package viewdoctor;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
public class Receiptcontroler implements Initializable{
    @FXML
	private AnchorPane anchorpane;
    @FXML
    private Label lb1,lb2,lb3,lb4,lb5,lb6,lb7;
    @FXML
    private Button printbtn;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    
    
    public void printing(ActionEvent event) {
  	  try {
PrinterJob job=PrinterJob.createPrinterJob();
Printer printer=Printer.getDefaultPrinter();
PageLayout layout=printer.createPageLayout(Paper.A3,PageOrientation.PORTRAIT,
		Printer.MarginType.HARDWARE_MINIMUM);
if(job!=null&&job.showPrintDialog(anchorpane.getScene().getWindow())) {
	boolean success=job.printPage(layout,anchorpane);
	if(success) {
		job.endJob();
}
  }
}catch(Exception e) {
	e.printStackTrace();
}
 }


   }
