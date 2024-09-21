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

								    public Usuario buscarUsuario(Integer dni, String pass) 
								    {
						
								       
								    	try {								
									            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT us.dni, nombre, apellido, tipo_usuario, cambio_clave FROM pasos.usuario us left join paciente pac on pac.dni=us.dni left join profesional prof on prof.dni=us.dni where us.dni=? and us.clave=?");
									            stmt.setInt(1, dni);
									            stmt.setString(2, pass);
									            rs = stmt.executeQuery();
									
									            if (rs != null && rs.next()) {
									            	us.setDni((rs.getInt("dni")));
									            	us.setApellido(rs.getString("apellido"));
									                us.setNombre(rs.getString("nombre"));
									                us.setTipo_usuario(rs.getInt("tipo_usuario"));
									                us.setCambio_clave(rs.getBoolean("cambio_clave"));
									                
									            }
									            
									            else
									            {
									            	us = null;
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
																		            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("INSERT INTO usuario (dni,apellido, nombre, telefono, clave, fecha_nacimiento, genero, email,tipo_usuario) VALUES (?,?,?,?,?,?,?,?,?)");
																		            stmt.setInt(1,us.getDni());
																		            stmt.setString(2, us.getApellido());
																		            stmt.setString(3, us.getNombre());
																		            stmt.setString(4, us.getTelefono());
																		            stmt.setString(5, us.getClave());
																		            stmt.setDate(6,java.sql.Date.valueOf(us.getFecha_nacimiento()));
																		            stmt.setString(7, us.getGenero());
																		            stmt.setString(8, us.getEmail());
																		            stmt.setInt(9, us.getTipo_usuario());
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

									public boolean validarAdministrador(Usuario us) {
										boolean rta = false;
								        try {
								            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM usuario WHERE dni = ?");
								            stmt.setInt(1, us.getDni());
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

									public String updateUsuarioSinContraseña(Usuario us) {
										String respuestaOperacion;
								        try
								        {
								        	stmt = FactoryConnection.getInstancia().getConn().prepareStatement("UPDATE usuario SET apellido = ?, nombre = ?, telefono = ?, fecha_nacimiento = ?, genero = ?, email = ? WHERE dni = ?");
								            stmt.setString(1, us.getApellido());
								            stmt.setString(2, us.getNombre());
								            stmt.setString(3, us.getTelefono());
								            stmt.setDate(4,java.sql.Date.valueOf(us.getFecha_nacimiento()));
								            stmt.setString(5, us.getGenero());
								            stmt.setString(6, us.getEmail());
								            stmt.setInt(7,us.getDni());
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
									
									public String updateUsuarioConContraseña(Usuario us) {
										String respuestaOperacion;
								        try
								        {
								        	stmt = FactoryConnection.getInstancia().getConn().prepareStatement("UPDATE usuario SET apellido = ?, nombre = ?, telefono = ?, fecha_nacimiento = ?, genero = ?, email = ?, clave = ? WHERE dni = ?");
								            stmt.setString(1, us.getApellido());
								            stmt.setString(2, us.getNombre());
								            stmt.setString(3, us.getTelefono());
								            stmt.setDate(4,java.sql.Date.valueOf(us.getFecha_nacimiento()));
								            stmt.setString(5, us.getGenero());
								            stmt.setString(6, us.getEmail());
								            stmt.setString(7,us.getClave());
								            stmt.setInt(8,us.getDni());
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


									public List<Usuario> getAllAdministradoresPorDNI(int dniBuscado) {
										List<Usuario> administradores = new ArrayList<>();
								        try {
								            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM usuario WHERE dni NOT IN (SELECT dni FROM paciente) AND dni NOT IN (SELECT dni FROM profesional) AND dni = ?");
								            stmt.setInt(1,dniBuscado);
								            rs = stmt.executeQuery();

								            while (rs.next()) 
								            {
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
									
									

									public Integer buscarDniUsuario(String mail) {

										
										Integer dni = null;
										
										 try {								
									            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select dni from usuario where email=?");
									            stmt.setString(1, mail);
									          
									            rs = stmt.executeQuery();
									
									            if (rs != null && rs.next()) {	
									            	
									                dni = rs.getInt("dni");
									            }
									            
									            else
									            {
									            	dni = null;
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
								        
								        return dni;
										

									}

								public String setearClaveGenerada(Usuario us) {
									
										String respuestaOperacion;
										
								        try
								        {
								        	stmt = FactoryConnection.getInstancia().getConn().prepareStatement("UPDATE usuario SET clave = ?, cambio_clave = 1 WHERE email = ?");
								            stmt.setString(1, us.getClave());
								            stmt.setString(2, us.getEmail());
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

								public String cambiarClave(Usuario us) {
									
									String respuestaOperacion;
									
							        try
							        {
							        	stmt = FactoryConnection.getInstancia().getConn().prepareStatement("UPDATE usuario SET clave = ?, cambio_clave = 0 WHERE dni = ?");
							            stmt.setString(1, us.getClave());
							            stmt.setInt(2, us.getDni());
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