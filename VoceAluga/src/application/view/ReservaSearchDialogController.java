package application.view;

import application.Main;
import application.controller.ReservaController;
import application.dbclass.ReservaDao;
import application.model.Carro;
import application.model.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservaSearchDialogController {
    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Reserva> reservaTable;
    @FXML
    private TableColumn<Reserva, String> cpfColumn;
    @FXML
    private TableColumn<Reserva, String> grupoColumn;
    @FXML
    private TableColumn<Reserva, String> modeloColumn;

    
    private String filter = "";
    private String searchValue = "";

    @FXML
    private void initialize() {
    	filterChoice.getItems().addAll("CPF","Grupo","Modelo");
        filterChoice.setValue("CPF");
        reservaTable.setPlaceholder(new Label("Nenhuma reserva encontrada"));
        cpfColumn.setCellValueFactory(cellData -> cellData.getValue().idclienteProperty());
        grupoColumn.setCellValueFactory(cellData -> cellData.getValue().grupoProperty());
        modeloColumn.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());

    }

    @FXML
    private void handleSearchReservation() {
        ObservableList<Reserva> reservaResult = FXCollections.observableArrayList();
        searchValue = searchTextField.getText();
        ReservaController reservaController = new ReservaController();

        if (filterChoice.getValue().equals("CPF")) {
            reservaController.searchByCpf(reservaResult, searchValue);
        } else if (filterChoice.getValue().equals("Grupo")) {
            reservaController.searchByGrupo(reservaResult, searchValue);
        } else if(filterChoice.getValue().equals("Modelo")) {
            reservaController.searchByModelo(reservaResult, searchValue);
        }
        reservaTable.setItems(reservaResult);    	
    }

    @FXML
    private void handleEditReservation() throws IOException {
        Reserva selectedReserva = reservaTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ReservaEditDialog.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Editar reserva");
        stage.setScene(scene);

        ReservaEditDialogController controller = loader.getController();
        controller.setNewEntryFlag(false);
        controller.setReserva(selectedReserva);

        stage.showAndWait();
    }

    @FXML
    private void handleDeleteReservation() {
        int selectedIndex = reservaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Reserva selectedReserva = reservaTable.getItems().get(selectedIndex);
            reservaTable.getItems().remove(selectedIndex);

            ReservaDao reservaDao = new ReservaDao();
            reservaDao.delete(selectedReserva);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Reservationro Selecionadp");
            alert.setContentText("Por favor, selecione um reservationro na tabela.");
            alert.showAndWait();
        }
    }
}
