package application.view;
import application.controller.CarroController;
import application.controller.ClienteController;
import application.controller.ControllerException;
import application.controller.LocacaoController;

import application.model.Locacao;
import application.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LocacaoEditDialogController {

    @FXML
    Node rootNode;
    @FXML
    TextField idField;
    @FXML
    ChoiceBox<String> idclienteField;
    @FXML
    ChoiceBox<String> idcarroField;
    @FXML
    TextField problemaField;
 

    private Locacao locacao;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        // bloqueia edição do id
        idField.setDisable(true);

    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        locacao = new Locacao();
        this.newEntryFlag = newEntryFlag;
        ClienteController clienteController = new ClienteController();
        CarroController carroController = new CarroController();
        this.idclienteField.setItems(clienteController.getIdsClienteField());
        this.idcarroField.setItems(carroController.getIdsCarroField());
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
        this.idField.setText(String.valueOf(locacao.getId()));
        this.idclienteField.setValue(String.valueOf(locacao.getIdcliente()));
        this.idcarroField.setValue(String.valueOf(locacao.getIdcarro()));
        this.problemaField.setText(String.valueOf(locacao.getProblema()));
    }
    private Map<String,String> buildFieldsMap() {
    	Map<String,String> fields = new HashMap<>();
    	fields.put("id",idField.getText());
    	fields.put("idcliente", idclienteField.getValue());
    	fields.put("idcarro", idcarroField.getValue());
    	fields.put("problema",problemaField.getText());
    	return fields;
    }
    
    @FXML
    private void handleOk() {
        Map<String, String> locacaoFields = buildFieldsMap();
       

        try {
            LocacaoController locacaoManager = new LocacaoController();
            if (newEntryFlag) {
                locacaoManager.add(locacaoFields);
            } else {
                locacaoFields.put("id", String.valueOf(locacao.getId()));
                locacaoManager.edit(locacaoFields);
            }
        } catch (ControllerException e) {
            e.printStackTrace();
        }

        this.getStage().close();
    }

    @FXML
    private void handleCancel() {
        this.getStage().close();
    }

    private Stage getStage() {
        return (Stage) rootNode.getScene().getWindow();
    }
}
