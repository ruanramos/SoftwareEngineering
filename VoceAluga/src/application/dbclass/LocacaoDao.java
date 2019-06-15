package application.dbclass;

import application.model.Locacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocacaoDao extends DefaultDao<Locacao> {
	
	public LocacaoDao() {
		tableName = "locacoes";
        columnNames = "idcliente,idcarro,problema";
        interrogationMarks = "?,?,?"; //mesmo número de interrogações que de colunas em 'columnNames'
        formatForModifyingColumnsWhenUpdatingTableEntries =
                "idcliente=?,idcarro=?,problema=?";
        primaryKey = "id";
    }

    protected void fillInsertStatement(PreparedStatement statement, Locacao locacao) throws SQLException {
        statement.setString(1, locacao.getIdcliente());
        statement.setString(2, locacao.getIdcarro());
        statement.setString(3, locacao.getProblema());
    }

    protected void fillDeleteStatement(PreparedStatement statement, Locacao locacao) throws SQLException {
    	statement.setInt(1, locacao.getId());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Locacao locacao) throws SQLException {
    	statement.setString(1, locacao.getIdcliente());
        statement.setString(2, locacao.getIdcarro());
        statement.setString(3, locacao.getProblema());
        statement.setInt(4, locacao.getId());
    }

    protected Locacao getObjectWithDbInformation(ResultSet set) throws SQLException{
        Locacao locacao = new Locacao();

        locacao.setId(set.getInt("id"));
        locacao.setIdcliente(set.getString("idcliente"));
        locacao.setIdcarro(set.getString("idcarro"));
        locacao.setProblema(set.getString("problema"));
        
        return locacao;
    }
}
