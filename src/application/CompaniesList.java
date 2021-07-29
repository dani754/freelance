package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CompaniesList implements Initializable {

	private String[] _CompaniesListNames;
	private String _currentCompany;
	@FXML
	private ListView<String> companiesListView;
	@FXML
	private Label headline;
	@FXML
	private Button logout;
	@FXML
	private Button newCopmany;
	@FXML 
	private Button selectCompany;
	
	public CompaniesList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		_CompaniesListNames = Main.getCompaniesListNames();
	
		companiesListView.getItems().addAll(_CompaniesListNames);
		
		companiesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				_currentCompany = companiesListView.getSelectionModel().getSelectedItem();
				headline.setText(_currentCompany);		
			}
		});
	}
	
	public void selectCompany(ActionEvent e) throws IOException, Exception {
		System.out.println("the selected company is: " + _currentCompany);
	}
	
	public void newCompany(ActionEvent e) throws IOException, Exception {
		System.out.println("new company screen");
	}
	
	public void logOut(ActionEvent e) throws IOException, Exception {
		System.out.println("return to logIn screen");
	}

}
