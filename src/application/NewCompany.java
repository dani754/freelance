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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class NewCompany  implements Initializable {
	
	private static String[] states = {"blank", "copy index", "copy with records"};
	private static int _currentState = 1;
	
	private String[] _CompaniesListNames;
	private String _currentCompany;
	
	@FXML
	private RadioButton blankCompany;
	@FXML
	private RadioButton copyCompany;
	@FXML
	private ListView<String> companiesListView;
	@FXML
	private CheckBox copyRecords;
	@FXML
	private Button cancel;
	@FXML
	private Button createCompany;

	public NewCompany() {
		// TODO Auto-generated constructor stub
	}
	
	public void blankCompany(ActionEvent e) throws IOException, Exception {
		System.out.println("blank Company screen");
	}
	
	public void copyCompany(ActionEvent e) throws IOException, Exception {
		System.out.println("copy Company list view");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		_CompaniesListNames = Main.getCompaniesListNames();
		companiesListView.getItems().addAll(_CompaniesListNames);
		companiesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				_currentCompany = companiesListView.getSelectionModel().getSelectedItem();
			}
		});
	}
	
	public void copyRecords(ActionEvent e) throws IOException, Exception {
		System.out.println("copy records checked");
	}
	
	public void cancel(ActionEvent e) throws IOException, Exception {
		Main m = new Main();
		m.changeScene("CompaniesListScreen.fxml");
	}
	
	public void createCompany(ActionEvent e) throws IOException, Exception {
		System.out.println("createCompany");
	}

}
