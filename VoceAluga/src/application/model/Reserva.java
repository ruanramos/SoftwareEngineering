package application.model;

import java.time.LocalDate;

import javafx.beans.property.*;


public class Reserva {
	private IntegerProperty id;
	private StringProperty idcliente;
	private ObjectProperty<LocalDate> data;
	private StringProperty grupo;
	private StringProperty modelo;
	private IntegerProperty duracaodias;
	
	public Reserva() {
		this.id = new SimpleIntegerProperty(0);
		this.idcliente = new SimpleStringProperty("");
		this.data = new SimpleObjectProperty<LocalDate>(LocalDate.of(1970,1,1));
		this.grupo = new SimpleStringProperty("");
		this.modelo = new SimpleStringProperty("");
		this.duracaodias = new SimpleIntegerProperty(0);
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
	
	public String getIdcliente() {
		return idcliente.get();
	}

	public StringProperty idclienteProperty() {
		return idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente.set(idcliente);
	}
	
	public LocalDate getData() {
		return data.get();
	}

	public ObjectProperty<LocalDate> dataProperty() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data.set(data);
	}
	
	public String getGrupo() {
		return grupo.get();
	}

	public StringProperty grupoProperty() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo.set(grupo);
	}
	
	public String getModelo() {
		return modelo.get();
	}

	public StringProperty modeloProperty() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo.set(modelo);
	}
	
	public int getDuracaodias() {
		return duracaodias.get();
	}

	public IntegerProperty duracaodiasProperty() {
		return duracaodias;
	}

	public void setDuracaodias(int duracaodias) {
		this.duracaodias.set(duracaodias);
	}
}
