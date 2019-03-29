package application.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty cpf;
	private StringProperty rg;
	private StringProperty cnh;
	private LocalDate birthday;
	private StringProperty cellphone;
	private StringProperty address;
	private StringProperty email;

	public Customer() {
        this(null, null);
    }

	public Customer(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // daqui pra baixo sao valores padrao
		// TODO: fazer construtor com todos os valores
        this.cpf = new SimpleStringProperty("cpf number");
        this.rg = new SimpleStringProperty("RG number");
		this.cnh = new SimpleStringProperty("CNH number");
		this.birthday = (LocalDate.of(2001, 9, 11));
		this.cellphone = new SimpleStringProperty("4002-8922");
		this.address = new SimpleStringProperty("Rua ccmn");
	}

	public String getFirstName() {
		return firstName.get();
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getCpf() {
		return cpf.get();
	}

	public StringProperty cpfProperty() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf.set(cpf);
	}

	public String getRg() {
		return rg.get();
	}

	public StringProperty rgProperty() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg.set(rg);
	}

	public String getCnh() {
		return cnh.get();
	}

	public StringProperty cnhProperty() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh.set(cnh);
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCellphone() {
		return cellphone.get();
	}

	public StringProperty cellphoneProperty() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone.set(cellphone);
	}

	public String getAddress() {
		return address.get();
	}

	public StringProperty addressProperty() {
		return address;
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public String getEmail() {
		return email.get();
	}

	public StringProperty emailProperty() {
		return email;
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
}
