package repository;

import conexionDB.FactoryConnection;
import entity.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
								    ResultSet rs = null;
								    PreparedStatement stmt = null;
								    Usuario us = new Usuario();

								    public Usuario buscarUsuario(String mail, String pass) 
								    {
								        try {								
									            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from usuario where email=? and clave=?");
									            stmt.setString(1, mail);
									            stmt.setString(2, pass);
									            rs = stmt.executeQuery();
									
									            if (rs != null && rs.next()) {
									                us.setApellido(rs.getString("apellido"));
									                us.setNombre(rs.getString("nombre"));
									                us.setDni((rs.getInt("dni")));
									            }
								        	} catch (Exception e) {
								        							e.printStackTrace();
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
								        return us;
								    }								
								
								    public List<Usuario> getAllAdministradores() {
								        List<Usuario> administradores = new ArrayList<>();
								        try {
								            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM usuario WHERE dni NOT IN (SELECT dni FROM paciente) AND dni NOT IN (SELECT dni FROM profesional)");
								            rs = stmt.executeQuery();

								            while (rs.next()) {
								                Usuario us = new Usuario();
								                us.setDni(rs.getInt("dni"));
								                us.setApellido(rs.getString("apellido"));
								                us.setNombre(rs.getString("nombre"));
								                us.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
								                us.setGenero(rs.getString("genero"));
								                us.setTelefono(rs.getString("telefono"));
								                us.setEmail(rs.getString("email"));
								                administradores.add(us);
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
								        return administradores;
								    }

								    
								    public String insertarUsuario(Usuario us) {	
								    											String respuestaOperacion;
																		        try
																		        {
																		            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("INSERT INTO usuario (dni,apellido, nombre, telefono, clave, fecha_nacimiento, genero, email) VALUES (?,?,?,?,?,?,?,?)");
																		            stmt.setInt(1,us.getDni());
																		            stmt.setString(2, us.getApellido());
																		            stmt.setString(3, us.getNombre());
																		            stmt.setString(4, us.getTelefono());
																		            stmt.setString(5, us.getClave());
																		            stmt.setDate(6,java.sql.Date.valueOf(us.getFecha_nacimiento()));
																		            stmt.setString(7, us.getGenero());
																		            stmt.setString(8, us.getEmail());
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
								}