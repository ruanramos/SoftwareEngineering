package application.model;

import javafx.beans.property.*;

public class Locacao {
	private IntegerProperty id;
	private StringProperty idcliente;
	private StringProperty idcarro;
	private StringProperty problema;
	
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
		this.idcliente.set(idcliente);;
	}
	
	public String getIdcarro() {
		return idcarro.get();
	}
	public StringProperty idcarroProperty() {
		return idcarro;
	}
	public void setIdcarro(String idcarro) {
		this.idcarro.set(idcarro);;
	}
	
	public String getProblema() {
		return problema.get();
	}
	public StringProperty problemaProperty() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema.set(problema);;
	}
}
