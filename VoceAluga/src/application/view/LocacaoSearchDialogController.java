package application.view;

import application.dbclass.ReservaDao;
import application.model.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class LocacaoSearchDialogController {
    @FXML
    private ChoiceBox<String> filterChoice;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Reserva> reservationTable;

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
            Reserva selectedReserva = reservationTable.getItems().get(selectedIndex);
            reservationTable.getItems().remove(selectedIndex);

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
