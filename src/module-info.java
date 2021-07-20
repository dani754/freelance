module freelance {
	requires javafx.controls;
	requires java.sql;
	requires javafx.base;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
