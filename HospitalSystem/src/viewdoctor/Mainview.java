package viewdoctor;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
public class Mainview extends Application{
public static void main(String[] args) {
		// TODO Auto-generated method stub
launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("Loginsrn.fxml"));
		Stage stage= new Stage(StageStyle.DECORATED);
		stage.setResizable(false);
		Image icon=new Image(getClass().getResourceAsStream("ecitizen.png"));
		Scene scene=new Scene(root);
		stage.getIcons().add(icon);
		stage.setScene(scene);
		stage.setTitle("Health care system");
		stage.show();
			}
				}
