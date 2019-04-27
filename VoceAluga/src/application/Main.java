package application;
import java.io.IOException;

import application.model.Car;
import application.model.Customer;
import application.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

// todo: apagar customer crud e seu controller

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/RootLayout.fxml"));
		Scene scene = new Scene(loader.load());
		stage.setTitle("Tela inicial");
		stage.setScene(scene);
		stage.show();
	}
}
