package repository;

import conexionDB.FactoryConnection;
import entity.ObraSocial;
import entity.Paciente;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {

    ResultSet rs = null;
    PreparedStatement stmt = null;
    String respuestaOperacion;
    
    Paciente pac = new Paciente();

    public String insertarPaciente(Paciente pac)
    {
	    try
	    {   	
	        stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into paciente (dni,nro_afiliado,id_obra_social) values (?,?,?)");
	        stmt.setInt(1,pac.getDni());
	        stmt.setString(2,pac.getNro_afiliado());
	        stmt.setInt(3,pac.getObra_social().getId_obra_social());
	        stmt.execute();
	        respuestaOperacion = "OK";
	    } 
	    catch (SQLException e) {
	        respuestaOperacion = e.toString();
	    }
	    finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();	
	        } catch (Exception e) {
	            respuestaOperacion = e.toString();
	        }
	        FactoryConnection.getInstancia().releaseConn(); //reveer esto, no me convene
	    }
	    return respuestaOperacion;
	    }

	public Paciente buscarPaciente(Integer dni) {
		Paciente pac = null;
		ObraSocial obraSocial = new ObraSocial();
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from paciente p inner join usuario u on u.dni=p.dni inner join obra_social os on os.id_obra_social=p.id_obra_social where p.dni=?");
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				pac = new Paciente();
				pac.setApellido(rs.getString("u.apellido"));
				pac.setNombre(rs.getString("nombre"));
				pac.setDni(rs.getInt("dni"));
				obraSocial.setId_obra_social(rs.getInt("id_obra_social"));
				obraSocial.setNombre(rs.getString("nombre_os"));
				//obraSocial.setEstado(rs.getBoolean("estado_os"));
				//obraSocial.setFecha_baja(rs.getDate("fecha_baja_os").toLocalDate());
				pac.setObra_social(obraSocial);
				pac.setNro_afiliado(rs.getString("nro_afiliado"));
				respuestaOperacion = "OK";	
			}
		}
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		return pac;
	}

	public List<Paciente> getAll() {
		List<Paciente> pacientes = new ArrayList<>();		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from paciente p inner join usuario u on u.dni=p.dni inner join obra_social os on os.id_obra_social=p.id_obra_social");
			rs = stmt.executeQuery();
			
			while (rs != null && rs.next()) 
			{
				Paciente unPaciente = new Paciente();
				ObraSocial obraSocial = new ObraSocial();
				unPaciente.setDni(rs.getInt("dni"));
	            unPaciente.setApellido(rs.getString("apellido"));
	            unPaciente.setNombre(rs.getString("nombre"));
	            unPaciente.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
	            unPaciente.setGenero(rs.getString("genero"));
	            unPaciente.setTelefono(rs.getString("telefono"));
	            unPaciente.setEmail(rs.getString("email"));
	            obraSocial.setId_obra_social(rs.getInt("id_obra_social"));
				obraSocial.setNombre(rs.getString("nombre_os"));
				obraSocial.setEstado(rs.getBoolean("estado_os"));
				if (rs.getDate("fecha_baja_os")==null) 
				{
					obraSocial.setFecha_baja(null);
				}
				else 
				{
					obraSocial.setFecha_baja(rs.getDate("fecha_baja_os").toLocalDate());
				}				
				unPaciente.setObra_social(obraSocial);
				unPaciente.setNro_afiliado(rs.getString("nro_afiliado"));
	            pacientes.add(unPaciente);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Error al ejecutar la consulta SQL", e);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        FactoryConnection.getInstancia().releaseConn();
	    }
	    return pacientes;	
	}

	public boolean validarPaciente(Paciente pac) {
		boolean rta = false;
        try {
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM paciente WHERE dni = ?");
            stmt.setInt(1, pac.getDni());
            rs = stmt.executeQuery();

            if (rs.next()) {
            	rta = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FactoryConnection.getInstancia().releaseConn();
        }
        return rta;
	}

	public String updatePaciente(Paciente pac) {
		String respuestaOperacion;
        try
        {
        	stmt = FactoryConnection.getInstancia().getConn().prepareStatement("UPDATE paciente SET id_obra_social = ?, nro_afiliado = ? WHERE dni = ?");
            stmt.setInt(1, pac.getObra_social().getId_obra_social());
            stmt.setString(2, pac.getNro_afiliado());
            stmt.setInt(3,pac.getDni());
            stmt.executeUpdate();
            respuestaOperacion = "OK";								            							        	
        }								
        catch (SQLException e) {
           respuestaOperacion= e.toString();
        }

        finally {
		            try {              
			                if (rs != null) rs.close();
			                if (stmt != null) stmt.close();
			            } catch (Exception e) {
			                e.printStackTrace();
			            }																				
		            FactoryConnection.getInstancia().releaseConn();
        		}
        return respuestaOperacion;
	}

	public List<Paciente> getAllPorDNI(int dniBuscado) {
		List<Paciente> pacientes = new ArrayList<>();		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from paciente p inner join usuario u on u.dni=p.dni inner join obra_social os on os.id_obra_social=p.id_obra_social where u.dni = ?");
			stmt.setInt(1,dniBuscado);
			rs = stmt.executeQuery();
			
			while (rs != null && rs.next()) 
			{
				Paciente unPaciente = new Paciente();
				ObraSocial obraSocial = new ObraSocial();
				unPaciente.setDni(rs.getInt("dni"));
	            unPaciente.setApellido(rs.getString("apellido"));
	            unPaciente.setNombre(rs.getString("nombre"));
	            unPaciente.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
	            unPaciente.setGenero(rs.getString("genero"));
	            unPaciente.setTelefono(rs.getString("telefono"));
	            unPaciente.setEmail(rs.getString("email"));obraSocial.setId_obra_social(rs.getInt("id_obra_social"));
				obraSocial.setNombre(rs.getString("nombre_os"));
				obraSocial.setEstado(rs.getBoolean("estado_os"));
				if (rs.getDate("fecha_baja_os")==null) 
				{
					obraSocial.setFecha_baja(null);
				}
				else 
				{
					obraSocial.setFecha_baja(rs.getDate("fecha_baja_os").toLocalDate());
				}
				pac.setObra_social(obraSocial);
	            unPaciente.setNro_afiliado(rs.getString("nro_afiliado"));
	            pacientes.add(unPaciente);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Error al ejecutar la consulta SQL", e);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        FactoryConnection.getInstancia().releaseConn();
	    }
	    return pacientes;
	 }
}