module freelance {
	requires javafx.controls;
	requires java.sql;
	requires javafx.base;
	requires javafx.fxml;
	requires jdk.compiler;
	
	opens application to javafx.graphics, javafx.fxml;
}
