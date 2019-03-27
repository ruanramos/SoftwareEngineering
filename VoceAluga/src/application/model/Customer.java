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
	private StringProperty adress;
	public Customer() {
        this(null, null);
    }
	public Customer(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.cpf = new SimpleStringProperty("cpf number");
        this.rg = new SimpleStringProperty("RG number");
		this.cnh = new SimpleStringProperty("CNH number");
		this.birthday = (LocalDate.of(2001, 9, 11));
		this.cellphone = new SimpleStringProperty("4002-8922");
		this.adress = new SimpleStringProperty("Rua ccmn");
	}

	/**
	 * @return the firstName
	 */
	public StringProperty getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public StringProperty getLastName() {
		return lastName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(StringProperty firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(StringProperty lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(StringProperty cpf) {
		this.cpf = cpf;
	}

	/**
	 * @param rg the rg to set
	 */
	public void setRg(StringProperty rg) {
		this.rg = rg;
	}

	/**
	 * @param cnh the cnh to set
	 */
	public void setCnh(StringProperty cnh) {
		this.cnh = cnh;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param cellphone the cellphone to set
	 */
	public void setCellphone(StringProperty cellphone) {
		this.cellphone = cellphone;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(StringProperty adress) {
		this.adress = adress;
	}

	/**
	 * @return the cpf
	 */
	public StringProperty getCpf() {
		return cpf;
	}

	/**
	 * @return the rg
	 */
	public StringProperty getRg() {
		return rg;
	}

	/**
	 * @return the cnh
	 */
	public StringProperty getCnh() {
		return cnh;
	}

	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @return the cellphone
	 */
	public StringProperty getCellphone() {
		return cellphone;
	}

	/**
	 * @return the adress
	 */
	public StringProperty getAdress() {
		return adress;
	}
	
}
