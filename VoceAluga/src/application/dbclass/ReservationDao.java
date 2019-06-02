package application.dbclass;
import application.model.Reservation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDao extends DefaultDao<Reservation> {
	public ReservationDao() {
       
    }

    protected void fillInsertStatement(PreparedStatement statement, Reservation reservation) throws SQLException {
       
    }

    protected void fillDeleteStatement(PreparedStatement statement, Reservation reservation) throws SQLException {
        statement.setInt(1, reservation.getId());
    }

    protected void fillUpdateStatement(PreparedStatement statement, Reservation reservation) throws SQLException {
    	
    }

    protected Reservation getObjectWithDbInformation(ResultSet set) throws SQLException{
        Reservation reservation = new Reservation();

        return reservation;
    }
}
