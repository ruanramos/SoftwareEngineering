package application.dbclass;

import application.model.Cliente;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDao extends DefaultDao<Cliente> {

    public ClienteDao() {
        tableName = "clientes";
        columnNames = "cpf,nome,endereco,telefone,nascimento,validadecnh";
        interrogationMarks = "?,?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "nome=?,endereco=?,telefone=?,nascimento=?,validadecnh=?"; // sem a primaryKey
        primaryKey = "cpf";
    }

    protected void fillInsertStatement(PreparedStatement statement, Cliente cliente) throws SQLException {
        statement.setString(1, cliente.getCpf());
        statement.setString(2, cliente.getNome());
        statement.setString(3, cliente.getEndereco());
        statement.setString(4, cliente.getTelefone());
        statement.setDate(5, Date.valueOf(cliente.getNascimento()));
        statement.setDate(6, Date.valueOf(cliente.getValidadeCnh()));
    }

    protected void fillDeleteStatement(PreparedStatement statement, Cliente cliente) throws SQLException {
    	statement.setString(1, cliente.getCpf());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Cliente cliente) throws SQLException {
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getEndereco());
        statement.setString(3, cliente.getTelefone());
        statement.setDate(4, Date.valueOf(cliente.getNascimento()));
        statement.setDate(5, Date.valueOf(cliente.getValidadeCnh()));
        statement.setString(6, cliente.getCpf());
    }

    protected Cliente getObjectWithDbInformation(ResultSet set) throws SQLException{
        Cliente cliente = new Cliente();

        cliente.setCpf(set.getString("cpf"));
        cliente.setNome(set.getString("nome"));
        cliente.setEndereco(set.getString("endereco"));
        cliente.setTelefone(set.getString("telefone"));
        cliente.setNascimento(set.getDate("nascimento").toLocalDate());
        cliente.setValidadeCnh(set.getDate("validadecnh").toLocalDate());
        
        return cliente;
    }
}
