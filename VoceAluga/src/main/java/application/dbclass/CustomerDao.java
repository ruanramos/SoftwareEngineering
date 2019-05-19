package application.dbclass;

import application.model.Customer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerDao extends DefaultDao<Customer> {

    public CustomerDao() {
        tableName = "Customer";
        columnNames = "Cpf,FirstName,LastName,Cnh,Birthday,Cellphone";
        interrogationMarks = "?,?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "Cpf=?,FirstName=?,LastName=?,Cnh=?,Birthday=?,Cellphone=?";
    }

    protected void fillInsertStatement(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getCpf());
        statement.setString(2, customer.getFirstName());
        statement.setString(3, customer.getLastName());
        statement.setString(4, customer.getCnh());
        statement.setDate(5, Date.valueOf(customer.getBirthday()));
        statement.setString(6, customer.getCellphone());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setInt(1, customer.getId());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getCpf());
        statement.setString(2, customer.getFirstName());
        statement.setString(3, customer.getLastName());
        statement.setString(4, customer.getCnh());
        statement.setDate(5, Date.valueOf(customer.getBirthday()));
        statement.setString(6, customer.getCellphone());
        statement.setString(7, String.valueOf(customer.getId()));
    }

    protected Customer getObjectWithDbInformation(ResultSet set) throws SQLException{
        Customer customer = new Customer();

        customer.setId(set.getInt("Id"));
        customer.setCpf(set.getString("Cpf"));
        customer.setFirstName(set.getString("FirstName"));
        customer.setLastName(set.getString("LastName"));
        customer.setCnh(set.getString("Cnh"));
        customer.setBirthday(set.getDate("Birthday").toLocalDate());
        customer.setCellphone(set.getString("Cellphone"));

        return customer;
    }
}
