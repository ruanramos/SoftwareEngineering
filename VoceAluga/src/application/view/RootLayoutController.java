package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RootLayoutController {

    @FXML
    Node rootNode;

    private void initialize() {
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
    @FXML
    private void handleSearchReservation() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ReservationSearchDialog.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Buscar reserva");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void handleNewReservation() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ReservationEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Criar Locação");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        ReservationEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(true);

        stage.showAndWait();
    }

    private Stage getStage() {
        return (Stage) rootNode.getScene().getWindow();
    }

    @FXML
    private void handleQuit() {
        this.getStage().close();
    }
}
