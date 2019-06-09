package application.model;

import javafx.beans.property.*;

public class Carro {
	private StringProperty placa;
	private DoubleProperty quilometragem;
	private StringProperty grupo;
	private IntegerProperty ano;
	private StringProperty modelo;

	public Carro() {
		this.placa = new SimpleStringProperty("");
		this.quilometragem = new SimpleDoubleProperty(0.0);
		this.grupo = new SimpleStringProperty("");
		this.ano = new SimpleIntegerProperty(0);
		this.modelo = new SimpleStringProperty("");
	}

	public String getPlaca() {
		return placa.get();
	}

	public StringProperty placaProperty() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa.set(placa);
	}
	
	public double getQuilometragem() {
		return quilometragem.get();
	}

	public DoubleProperty quilometragemProperty() {
		return quilometragem;
	}

	public void setQuilometragem(double quilometragem) {
		this.quilometragem.set(quilometragem);
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
	
	public int getAno() {
		return ano.get();
	}

	public IntegerProperty anoProperty() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano.set(ano);
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

}
