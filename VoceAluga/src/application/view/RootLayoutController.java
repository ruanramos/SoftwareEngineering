package application.view;

import application.Main;
import application.dbclass.CarDao;
import application.model.Car;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RootLayoutController {

    private Main main;

    private void initialize() {
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void handleSearchCustomer() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CustomerSearchDialog.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Buscar cliente");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void handleNewCustomer() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CustomerEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Novo cliente");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        CustomerEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(true);

        stage.showAndWait();
    }

    @FXML
    private void handleSearchCar() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CarSearchDialog.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Buscar carro");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void handleNewCar() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CarEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastrar carro");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        CarEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(true);

        stage.showAndWait();
    }
}
