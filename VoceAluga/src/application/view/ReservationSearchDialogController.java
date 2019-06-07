package application.view;

import application.Main;
import application.controller.ReservationController;
import application.dbclass.ReservationDao;
import application.model.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationSearchDialogController {
    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Reservation> reservationTable;

    private String filter = "";
    private String searchValue = "";

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleSearchReservation() {
  
    }

    @FXML
    private void handleEditReservation() throws IOException {

    }

    @FXML
    private void handleDeleteReservation() {
        int selectedIndex = reservationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Reservation selectedReservation = reservationTable.getItems().get(selectedIndex);
            reservationTable.getItems().remove(selectedIndex);

            ReservationDao reservationDao = new ReservationDao();
            reservationDao.delete(selectedReservation);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma seleção");
            alert.setHeaderText("Nenhuma Reservationro Selecionadp");
            alert.setContentText("Por favor, selecione um reservationro na tabela.");
            alert.showAndWait();
        }
    }
}
