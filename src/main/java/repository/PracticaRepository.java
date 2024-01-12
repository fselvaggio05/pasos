package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionDB.FactoryConnection;
import entity.Practica;

public class PracticaRepository {
	
	ResultSet rs = null;
	
	PreparedStatement stmt = null;
	
	
	public List<Practica> getAll()
	{
		List<Practica> practicas = new ArrayList<>();
		
		
		//colocar try
			
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from practica");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Practica pr = new Practica ();
					pr.setId_practica(rs.getInt("id_practica"));
					pr.setDescripcion(rs.getString("descripcion"));
					pr.setId_equipo(rs.getInt("id_equipo"));
					practicas.add(pr);
					
					
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
			
			return practicas;	

	}
	
	

	
	
	

	public void insertarPractica(String descPractica, Integer idEquipo) {
		
			
		try
		{
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into practica (descripcion,id_equipo) values (?,?)");
			stmt.setString(1, descPractica);
			stmt.setInt(2, idEquipo);
			stmt.executeUpdate();
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			System.out.println("Se ha producido un error al insertar la practica");
		}
		
		finally {

	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        	} 
	        catch (Exception e) {
	            e.printStackTrace();
	        }

	        FactoryConnection.getInstancia().releaseConn(); //es correcta esta forma de cerrar la conexion?
	    }
		
	}

}





