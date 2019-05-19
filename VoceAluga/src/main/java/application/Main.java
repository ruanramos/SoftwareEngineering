package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
