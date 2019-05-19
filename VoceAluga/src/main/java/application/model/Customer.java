package application.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
	private IntegerProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty cpf;
	private StringProperty cnh;
	private ObjectProperty<LocalDate> birthday;
	private StringProperty cellphone;

	public Customer() {
        this(null, null);
    }

	public Customer(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // daqui pra baixo sao valores padrao
		// TODO: fazer construtor com todos os valores
		this.id = new SimpleIntegerProperty();
        this.cpf = new SimpleStringProperty("");
		this.cnh = new SimpleStringProperty("");
		this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1970,1,1));
		this.cellphone = new SimpleStringProperty("");
	}

	public int getId() {
		return id.get();
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public void setId(int id) {
		this.id.set(id);
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
		return birthday.get();
	}

	public ObjectProperty<LocalDate> birthdayProperty() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
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
}
