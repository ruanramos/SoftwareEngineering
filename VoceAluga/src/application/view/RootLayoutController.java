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
    private void handleSearchCliente() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ClienteSearchDialog.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Buscar cliente");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void handleNewCliente() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ClienteEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Novo cliente");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        ClienteEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(true);

        stage.showAndWait();
    }

    @FXML
    private void handleSearchCarro() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CarroSearchDialog.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Buscar carro");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void handleNewCarro() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/CarroEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastrar carro");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        CarroEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(true);

        stage.showAndWait();
    }
    @FXML
    private void handleSearchReservation() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ReservaSearchDialog.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Buscar reserva");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void handleNewReservation() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ReservaEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Criar Loca��o");
//        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        ReservaEditDialogController controller = loader.getController();
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
