package application;

import basic.User;
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

		
	public Register() {
		// TODO Auto-generated constructor stub
	}
	
	public void newUser() {
		checkDetails();
	}
	
	private void checkDetails() {
		if (newUsername.getText().isEmpty() || newPassword.getText().isEmpty()) {
			registerStatus.setText("Please enter username and password");
		} else if (verifyPassword.getText().isEmpty() || !verifyPassword.getText().equals(newPassword.getText())) {
			registerStatus.setText("Please re-enter your new password again");
		} else if (User.registerNewUser(newUsername.getText(),newPassword.getText())) {
			Main m = new Main();
			m.changeScene("CompaniesListScreen.fxml");
		} else {
			registerStatus.setText("This username already exists, please select another username");
		}
	}

}
