package application.view;


import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import application.model.Customer;
import application.util.DateUtil;

public class CustomerEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField rgField;
    @FXML
    private TextField cnhField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField cellphoneField;
    @FXML
    private TextField adressField;
    
    private Stage dialogStage;
    private Customer customer;
    private boolean okClicked = false;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    
    @FXML
    private void initialize() {
    }
    
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */

    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    /**
     * Sets the customer to be edited in the dialog.
     * 
     * @param customer
     */
    public void setcustomer(Customer customer) {
        this.customer = customer;
        firstNameField.setText(customer.getFirstName().get());
        lastNameField.setText(customer.getLastName().get());
        cpfField.setText(customer.getCpf().get());
        rgField.setText(customer.getRg().get());
        cnhField.setText(customer.getCnh().get());
        birthdayField.setPromptText("dd.mm.yyyy");
        adressField.setText(customer.getAdress().get());
        cellphoneField.setText(customer.getCellphone().get());
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            customer.setFirstName(new SimpleStringProperty(firstNameField.getText()));
            customer.setLastName(new SimpleStringProperty(lastNameField.getText()));
            customer.setCpf(new SimpleStringProperty(cpfField.getText()));
            customer.setRg(new SimpleStringProperty(rgField.getText()));
            customer.setCnh(new SimpleStringProperty(cnhField.getText()));           
            customer.setBirthday(DateUtil.parse(birthdayField.getText()));            
            customer.setAdress(new SimpleStringProperty(adressField.getText()));
            customer.setCellphone(new SimpleStringProperty(cellphoneField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Campo nome obrigat�rio."; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Campo Sobrenome obrigat�rio."; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (cpfField.getText() == null || cpfField.getText().length() != 11) {
            errorMessage += "Cpf inv�lido, o n�mero de cpf deve ter 11 d�gitos!\n"; 
        } else {
            // try to parse the cpf into an int.
            try {
                Integer.parseInt(cpfField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Cpf inv�lido, use somente n�meros!\n"; 
            }
        }
        if (rgField.getText() == null || rgField.getText().length() != 9) {
            errorMessage += "RG inv�lido!,  o n�mero de RG deve ter 9 d�gitos\n"; 
        } else {
            // try to parse the rg into an int.
            try {
                Integer.parseInt(rgField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "RG inv�lido, use somente n�meros!\n"; 
            }
        }
        if (cnhField.getText() == null || cnhField.getText().length() != 10) {
            errorMessage += "CNH inv�lida!,  o n�mero da CNH deve ter 10 d�gitos\n";
        } else {
            // try to parse the cnh into an int.
            try {
                Integer.parseInt(cnhField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "CNH inv�lida, use somente n�meros!\n"; 
            }
        }
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Data de nascimento inv�lida!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Data de nascimento inv�lida. Use o formato dd.mm.aaaa!\n";
            }
        }
        if (adressField.getText() == null || adressField.getText().length() == 0) {
            errorMessage += "Campo endere�o obrigat�rio."; 
        }
        if (rgField.getText() == null || rgField.getText().length() != 9) {
            errorMessage += "RG inv�lido!,  o n�mero de RG deve ter 9 d�gitos\n"; 
        } else {
            // try to parse the rg into an int.
            try {
                Integer.parseInt(rgField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "RG inv�lido, use somente n�meros!\n"; 
            }
        }
        if (cellphoneField.getText() == null || cellphoneField.getText().length() != 9) {
            errorMessage += "N�mero de celular inv�lido!, o n�mero do celular deve ter 9 d�gitos\n";
        } else {
            // try to parse the cellphone into an int.
            try {
                Integer.parseInt(cnhField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "N�mero de celular inv�lido, use somente n�meros!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campo inv�lido");
            alert.setHeaderText("Por favor corrija os campos inv�lidos.");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    
}
