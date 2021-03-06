package application.view;


import application.controller.ClienteController;
import application.controller.ControllerException;
import application.model.Cliente;
import application.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ClienteEditDialogController {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @FXML
    private Node rootNode;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField cpfField;
    @FXML
    private DatePicker nascimentoPicker;
    @FXML
    private DatePicker validadeCnhPicker;

    private Cliente cliente;
    private boolean newEntryFlag;

    @FXML
    public void initialize() {
        nascimentoPicker.setConverter(DateUtil.getStringConverter());
        validadeCnhPicker.setConverter(DateUtil.getStringConverter());
    }

    public void setNewEntryFlag(boolean newEntryFlag) {
        cliente = new Cliente();
        this.newEntryFlag = newEntryFlag;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cpfField.setText(cliente.getCpf());
        nomeField.setText(cliente.getNome());
        enderecoField.setText(cliente.getEndereco());
        telefoneField.setText(cliente.getTelefone());
        nascimentoPicker.setValue(cliente.getNascimento());
        validadeCnhPicker.setValue(cliente.getValidadecnh());
    }

    @FXML
    private void handleOk() {
        Map<String,String> clienteFields = buildFieldsMap();
        getDisplayedCliente();

        try {
            ClienteController clienteController = new ClienteController();

            if (newEntryFlag) {
                clienteController.add(clienteFields);

            } else {
                clienteController.edit(clienteFields);
            }
        } catch (ControllerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(getStage());
            alert.setTitle("Campo inválido");
            alert.setHeaderText("Por favor corrija os campos inválidos.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
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

    private Map<String,String> buildFieldsMap() {
        Map<String,String> fields = new HashMap<>();
        fields.put("cpf", cpfField.getText());
        fields.put("nome", nomeField.getText());
        fields.put("endereco", enderecoField.getText());
        fields.put("telefone", telefoneField.getText());
        fields.put("nascimento", String.valueOf(DateUtil.parse(nascimentoPicker.getEditor().getText())));
        fields.put("validadecnh", String.valueOf(DateUtil.parse(validadeCnhPicker.getEditor().getText())));
        return fields;
    }

    private void getDisplayedCliente() {
        cliente.setCpf(cpfField.getText());
        cliente.setNome(nomeField.getText());
        cliente.setEndereco(enderecoField.getText());
        cliente.setTelefone(telefoneField.getText());
        cliente.setNascimento(nascimentoPicker.getValue());
        cliente.setValidadecnh(validadeCnhPicker.getValue());
    }
}
