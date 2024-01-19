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
			
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select id_equipo, desc_equipo from equipo where estado=1");
			rs = stmt.executeQuery();
			while (rs!=null && rs.next())
			{
				Equipo eq = new Equipo();
				eq.setId_equipo(rs.getInt("id_equipo"));
				eq.setDescripcion(rs.getString("desc_equipo"));				
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
