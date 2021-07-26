package application;
	
import java.io.IOException;

import basic.Company;
import basic.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage stg;
	private static User _user;
	private static Company _currentCompany;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Freelance");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene(String fxml) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource(fxml));
			stg.getScene().setRoot(pane);
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setUser(User user) {
		_user = new User(user);
	}
	
	public static User getUser() {
		return _user;
	}
	
	public static void setCompany(Company company) {
		_currentCompany = new Company(company);
	}
	
	public static Company getCompany() {
		return _currentCompany;
	}
}
