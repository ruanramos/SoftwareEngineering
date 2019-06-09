package application.dbclass;

import application.model.Carro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarroDao extends DefaultDao<Carro> {
	public CarroDao() {
        tableName = "carros";
        columnNames = "placa,quilometragem,grupo,ano,modelo";
        interrogationMarks = "?,?,?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "placa=?,quilometragem=?,grupo=?,ano=?,modelo=?";
    }

    protected void fillInsertStatement(PreparedStatement statement, Carro carro) throws SQLException {
        statement.setString(1, carro.getPlaca());
        statement.setDouble(2, carro.getQuilometragem());
        statement.setString(3, carro.getGrupo());
        statement.setInt(4, carro.getAno());
        statement.setString(5, carro.getModelo());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Carro carro) throws SQLException {
    	statement.setString(1, "placa");
        statement.setString(2, carro.getPlaca());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Carro carro) throws SQLException {
    	fillInsertStatement(statement, carro);
    }

    protected Carro getObjectWithDbInformation(ResultSet set) throws SQLException{
        Carro carro = new Carro();

        carro.setPlaca(set.getString("placa"));
        carro.setQuilometragem(set.getDouble("quilometragem"));
        carro.setGrupo(set.getString("grupo"));
        carro.setAno(set.getInt("ano"));
        carro.setModelo(set.getString("modelo"));

        return carro;
    }
}
