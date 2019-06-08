package application.dbclass;

import application.model.Customer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerDao extends DefaultDao<Customer> {

    public CustomerDao() {
        tableName = "clientes";
        columnNames = "cpf,nome,endereco,telefone,nascimento,validadecnh";
        interrogationMarks = "?,?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "cpf=?,nome=?,endereco=?,telefone=?,nascimento=?,validadecnh=?";
    }

    protected void fillInsertStatement(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getCpf());
        statement.setString(2, customer.getNome());
        statement.setString(3, customer.getEndereco());
        statement.setString(4, customer.getTelefone());
        statement.setDate(5, Date.valueOf(customer.getNascimento()));
        statement.setDate(6, Date.valueOf(customer.getValidadecnh()));
    }

    protected void fillDeleteStatement(PreparedStatement statement, Customer customer) throws SQLException {
    	statement.setString(1, "cpf");
    	statement.setString(2, customer.getCpf());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Customer customer) throws SQLException {
    	fillInsertStatement(statement, customer);
    }

    protected Customer getObjectWithDbInformation(ResultSet set) throws SQLException{
        Customer customer = new Customer();

        customer.setCpf(set.getString("cpf"));
        customer.setNome(set.getString("nome"));
        customer.setEndereco(set.getString("endereco"));
        customer.setTelefone(set.getString("telefone"));
        customer.setNascimento(set.getDate("nascimento").toLocalDate());
        customer.setValidadecnh(set.getDate("validadecnh").toLocalDate());
        
        return customer;
    }
}
