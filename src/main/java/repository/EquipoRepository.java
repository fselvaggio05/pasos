package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import conexionDB.FactoryConnection;
import entity.Equipo;

public class EquipoRepository {
	
	ResultSet rs = null;
	PreparedStatement stmt = null;

	public List<Equipo> getAll() {
		
		List<Equipo> equipos = new ArrayList<>();
		
		try {
			
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from equipo");
			rs = stmt.executeQuery();
			while (rs!=null && rs.next())
			{
				Equipo eq = new Equipo();
				eq.setId_equipo(rs.getInt("id_equipo"));
				eq.setTipo_equipo(rs.getString("tipo_equipo"));
				eq.setDescripcion(rs.getString("descripcion"));
				eq.setEstado(rs.getString("estado"));
				eq.setFecha_baja(rs.getDate("fecha_baja"));
				equipos.add(eq);
			}
			
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			
			 try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		          } 
		        catch (Exception e) {
		            e.printStackTrace();
		        }

		        FactoryConnection.getInstancia().releaseConn();
			
		}
		
		return equipos;
	}

}
