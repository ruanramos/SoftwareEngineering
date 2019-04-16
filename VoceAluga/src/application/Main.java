package application;
import java.io.IOException;

import application.model.Car;
import application.model.Customer;
import application.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Customer> customerData = FXCollections.observableArrayList();
	private ObservableList<Car> carData = FXCollections.observableArrayList();


	public Main() {
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tela inicial");

		initRootLayout();
	}
	public ObservableList<Customer> getCustomerData() {
    	return customerData;
    }

//	private void showCustomerCrud() {
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(Main.class.getResource("view/Customercrud.fxml"));
//			AnchorPane CustomerCrud = (AnchorPane) loader.load();
//
//			/* Define Customer Crud in root layout */
//			rootLayout.setCenter(CustomerCrud);
//			// Give to controller access to the main class
//	        CustomercrudController controller = loader.getController();
//	        controller.setMain(this);
//
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//	}

	private void initRootLayout() throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			RootLayoutController controller = loader.getController();
			controller.setMain(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public Stage getPrimaryStage() {
        return primaryStage;
    }

	public boolean showCustomerEditDialog(Customer customer){
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/CustomerEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Editar Cliente");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        CustomerEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setcustomer(customer);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public void showCustomerSearchDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CustomerSearchDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Buscar Cliente");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CustomerSearchDialogController controller = loader.getController();
			controller.setMain(this);

			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Car> getCarData() {
		return carData;
	}

	public boolean showCarEditDialog(Car car){
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CarEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editar Carro");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			CarEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCar(car);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}


	public void showCarSearchDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CarSearchDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Buscar Veículo");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CarSearchDialogController controller = loader.getController();
			controller.setMain(this);

			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
