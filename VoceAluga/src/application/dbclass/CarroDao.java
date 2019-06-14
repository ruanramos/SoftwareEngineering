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
                "quilometragem=?,grupo=?,ano=?,modelo=?"; //sem primaryKey
        primaryKey = "placa";
    }

    protected void fillInsertStatement(PreparedStatement statement, Carro carro) throws SQLException {
        statement.setString(1, carro.getPlaca());
        statement.setDouble(2, carro.getQuilometragem());
        statement.setString(3, carro.getGrupo());
        statement.setInt(4, carro.getAno());
        statement.setString(5, carro.getModelo());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Carro carro) throws SQLException {
        statement.setString(1, carro.getPlaca());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Carro carro) throws SQLException {
        statement.setDouble(1, carro.getQuilometragem());
        statement.setString(2, carro.getGrupo());
        statement.setInt(3, carro.getAno());
        statement.setString(4, carro.getModelo());
        statement.setString(5, carro.getPlaca());
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
