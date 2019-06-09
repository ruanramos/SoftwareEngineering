package application.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Cliente {
	private StringProperty cpf;
	private StringProperty nome;
	private StringProperty endereco;
	private StringProperty telefone;
	private ObjectProperty<LocalDate> nascimento;
	private ObjectProperty<LocalDate> validadecnh;

	public Cliente() {
		this.cpf = new SimpleStringProperty("");
		this.nome = new SimpleStringProperty("");
		this.endereco = new SimpleStringProperty("");
		this.telefone = new SimpleStringProperty("");
		this.nascimento = new SimpleObjectProperty<LocalDate>(LocalDate.of(1900,1,1));
		this.validadecnh = new SimpleObjectProperty<LocalDate>(LocalDate.of(1900,1,1));
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
	
	public String getNome() {
		return nome.get();
	}

	public StringProperty nomeProperty() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public String getEndereco() {
		return endereco.get();
	}

	public StringProperty enderecoProperty() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco.set(endereco);
	}

	public String getTelefone() {
		return telefone.get();
	}

	public StringProperty telefoneProperty() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone.set(telefone);
	}
	
	public LocalDate getNascimento() {
		return nascimento.get();
	}

	public ObjectProperty<LocalDate> nascimentoProperty() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento.set(nascimento);
	}
	
	public LocalDate getValidadecnh() {
		return validadecnh.get();
	}

	public ObjectProperty<LocalDate> validadecnhProperty() {
		return validadecnh;
	}

	public void setValidadecnh(LocalDate validadecnh) {
		this.validadecnh.set(validadecnh);
	}
}
