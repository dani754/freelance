package application;

import java.io.IOException;
import java.sql.SQLException;

import basic.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Register {

	@FXML
	private TextField newUsername;
	@FXML
	private PasswordField newPassword;
	@FXML
	private PasswordField verifyPassword;
	@FXML
	private Label registerStatus;
	@FXML
	private Button submit;
	@FXML
	private Button logout;

		
	public Register() {
		// TODO Auto-generated constructor stub
	}
	
	public void newUser(ActionEvent e) throws IOException, Exception {
		newUser();
	}
	
	private void newUser()  throws IOException, SQLException {
		Main m = new Main();
		if (newUsername.getText().isEmpty() || newPassword.getText().isEmpty()) {
			registerStatus.setText("Please enter username and password");
		}
		else if (verifyPassword.getText().isEmpty() || !verifyPassword.getText().equals(newPassword.getText())) {
			registerStatus.setText("Please re-enter your new password");
		}
		else if (User.newUser(newUsername.getText(), newPassword.getText())) {
			Main.setUser(User.getUserByName(newUsername.getText()));
			Main.getCompaniesByUser();
			m.changeScene("CompaniesListScreen.fxml");
		}	
		else {
			registerStatus.setText("This username already exists, please select another username");
		}
	}
	
	public void logOut(ActionEvent e) throws IOException, Exception {
		Main m = new Main();
		m.logOut();
	}


}
