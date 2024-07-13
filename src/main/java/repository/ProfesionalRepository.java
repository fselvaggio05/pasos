package repository;

import conexionDB.FactoryConnection;
import entity.Profesional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfesionalRepository {

    ResultSet rs = null;
    PreparedStatement stmt = null;

    public String insertarProfesional(Profesional prof) {    	
    	String respuestaOperacion;
        try{
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into profesional (dni,matricula) values (?,?)");
            stmt.setInt(1,prof.getDni());
            stmt.setInt(2,prof.getMatricula());
            stmt.executeUpdate();
            respuestaOperacion = "OK";
        } catch (SQLException e) {
            respuestaOperacion = e.toString();
        }
        finally {
            try {
                //se cierran conexiones abiertas en el orden inverso en que fueron abiertas
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FactoryConnection.getInstancia().releaseConn(); //reveer esto, no me convene
        }
        return respuestaOperacion;
    }

    public List<Profesional> getAll() {
        List<Profesional> profesionales = new ArrayList<>();
        try {
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM profesional p INNER JOIN usuario u ON p.dni = u.dni ORDER BY apellido ASC");
            rs = stmt.executeQuery();

            while (rs != null && rs.next()) {
                Profesional pr = new Profesional();
                pr.setDni(rs.getInt("dni"));
                pr.setApellido(rs.getString("apellido"));
                pr.setNombre(rs.getString("nombre"));
                pr.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
                pr.setGenero(rs.getString("genero"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setEmail(rs.getString("email"));
                pr.setMatricula(rs.getInt("matricula"));
                profesionales.add(pr);
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
        return profesionales;
    }
    
	public boolean validarProfesional(Profesional prof) {
		boolean rta = false;
        try {
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM profesional WHERE dni = ?");
            stmt.setInt(1, prof.getDni());
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

	public String updateProfesional(Profesional prof) {
		String respuestaOperacion;
        try
        {
        	stmt = FactoryConnection.getInstancia().getConn().prepareStatement("UPDATE profesional SET matricula = ? WHERE dni = ?");
            stmt.setInt(1, prof.getMatricula());
            stmt.setInt(2,prof.getDni());
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

	public List<Profesional> getAllPorDNI(int dniBuscado) {
		List<Profesional> profesionales = new ArrayList<>();
        try {
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM profesional p INNER JOIN usuario u ON p.dni = u.dni WHERE u.dni = ?");
            stmt.setInt(1,dniBuscado);
            rs = stmt.executeQuery();

            while (rs != null && rs.next()) {
                Profesional pr = new Profesional();
                pr.setDni(rs.getInt("dni"));
                pr.setApellido(rs.getString("apellido"));
                pr.setNombre(rs.getString("nombre"));
                pr.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
                pr.setGenero(rs.getString("genero"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setEmail(rs.getString("email"));
                pr.setMatricula(rs.getInt("matricula"));
                profesionales.add(pr);
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
        return profesionales;
	}


public Profesional getProfesional(int matricula) {
	Profesional pr = new Profesional();
    try {
        stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM profesional p INNER JOIN usuario u ON p.dni = u.dni WHERE p.matricula = ?");
        stmt.setInt(1,matricula);
        rs = stmt.executeQuery();

        while (rs != null && rs.next()) {
            
            pr.setDni(rs.getInt("dni"));
            pr.setApellido(rs.getString("apellido"));
            pr.setNombre(rs.getString("nombre"));
            pr.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
            pr.setGenero(rs.getString("genero"));
            pr.setTelefono(rs.getString("telefono"));
            pr.setEmail(rs.getString("email"));
            pr.setMatricula(rs.getInt("matricula"));
           
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
    return pr;
}}

