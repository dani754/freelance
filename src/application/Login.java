package application;

import java.io.IOException;
import java.sql.SQLException;

import basic.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {
	
	@FXML
	private Button login;
	@FXML
	private Button register;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label loginStatus;
	

	public Login() {
		// TODO Auto-generated constructor stub
	}
	
	public void userLogin(ActionEvent e) throws IOException, Exception {
		userLogin();
	}
	
	private void userLogin() throws IOException, SQLException {

		Main m = new Main();
		if (username.getText().isEmpty() || password.getText().isEmpty()) {
			loginStatus.setText("Please enter username and password");
		} else if (User.checkLogin(username.getText(), password.getText())) {
			Main.setUser(User.getUserByName(username.getText()));
			Main.getCompaniesByUser();
			m.changeScene("CompaniesListScreen.fxml");
		} else {
			loginStatus.setText("Wrong username and password");
		}
	}
	
	public void registerScreen(ActionEvent e) throws IOException {
		Main m = new Main();
		m.changeScene("RegisterScreen.fxml");
	}
	
	

}
