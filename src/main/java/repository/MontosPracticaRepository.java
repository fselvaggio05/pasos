package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.MontosPractica;
import entity.Practica;

public class MontosPracticaRepository {
	
	ResultSet rs2 = null;
	PreparedStatement stmt = null;
	String respuestaOperacion;
	
	
	//Create
	public String insertarMontoPractica(Integer id_practica, LocalDate fecha_desde, LocalDate fecha_hasta, Double monto) {
		try
		{
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into monto_practica (id_practica, fecha_desde, fecha_hasta, monto) values (?,?,?,?)");
			stmt.setInt(1,id_practica);
			stmt.setDate(2, Date.valueOf(fecha_desde));
			stmt.setDate(3, Date.valueOf(fecha_hasta));
			stmt.setDouble(4, monto);
			stmt.executeUpdate();
			respuestaOperacion = "OK";
		}
		catch (SQLException e) {														
			respuestaOperacion = e.toString();
		}										
		finally {								
			        try {
			            if (rs2 != null) rs2.close();
			            if (stmt != null) stmt.close();
			        	} 
			        catch (Exception e) {
			            e.printStackTrace();
			        }
	        FactoryConnection.getInstancia().releaseConn(); //es correcta esta forma de cerrar la conexion?
	    }										
		return respuestaOperacion;
	}
	
	//Read
	public List<MontosPractica> getMontosPractica(Practica pr) {
		List<MontosPractica> montos = new ArrayList<>();
		try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM monto_practica mp where mp.id_practica=?");
				stmt.setInt(1, pr.getId_practica());
				rs2 = stmt.executeQuery();
				while (rs2!=null && rs2.next())
				{
					MontosPractica mp = new MontosPractica();
					mp.setId_monto(rs2.getInt("mp.id_monto"));
					mp.setFecha_desde(rs2.getDate("mp.fecha_desde").toLocalDate());
					mp.setFecha_hasta(rs2.getDate("mp.fecha_hasta").toLocalDate());
					mp.setMonto(rs2.getDouble("mp.monto"));
					montos.add(mp);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				        try {
				            if (rs2!= null) rs2.close();
				            if (stmt != null) stmt.close();
				        	} 
				        catch (Exception e) {
				            e.printStackTrace();
				        }								
		        FactoryConnection.getInstancia().releaseConn(); 
		    }											
			return montos;								
	}	
	
	//Update
	public String actualizarMonto(Integer idMonto, Double monto) {
		try
		{
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("update monto_practica set monto = ? where id_monto =?");
			stmt.setDouble(1, monto);
			stmt.setInt(2,idMonto);
			stmt.executeUpdate();
			respuestaOperacion = "OK";
		}
		catch (SQLException e) {														
			respuestaOperacion = e.toString();
		}										
		finally {								
			        try {
			            if (rs2!= null) rs2.close();
			            if (stmt != null) stmt.close();
			        	} 
			        catch (Exception e) {
			            e.printStackTrace();
			        }
	        FactoryConnection.getInstancia().releaseConn(); //es correcta esta forma de cerrar la conexion?
	    }										
		return respuestaOperacion;
	}
	
	//Delete
}
