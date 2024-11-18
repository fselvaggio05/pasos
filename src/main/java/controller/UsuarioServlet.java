package controller;

import entity.ObraSocial;
import entity.Paciente;
import entity.Profesional;
import entity.Usuario;
import service.ObraSocialService;
import service.PacienteService;
import service.ProfesionalService;
import service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/usuarios")

public class UsuarioServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	public UsuarioService usServ = new UsuarioService();
    public PacienteService pacServ = new PacienteService();
    public ProfesionalService profServ = new ProfesionalService();
    public ObraSocialService osServ = new ObraSocialService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Usuario> administradores = usServ.getAllAdministradores();
		List<Profesional> profesionales = profServ.getAll();
		List<Paciente> pacientes = pacServ.getAll();
		List<ObraSocial> obrasSociales = osServ.getAllActivas();
		request.setAttribute("tablaAdministradores", administradores);
		request.setAttribute("tablaProfesionales", profesionales);
		request.setAttribute("tablaPacientes", pacientes);
		request.setAttribute("obrasSociales", obrasSociales);
    	request.getRequestDispatcher("abmUsuario.jsp").forward(request,response);
    }    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        Integer opcUs;
    	if (request.getParameter("tipoUsuario")==null) 
    	{
        	if(request.getParameter("tipoUsuarioUpdate")==null)
        	{
        		opcUs=Integer.parseInt(request.getParameter("tipoUsuarioBusqueda"));
        	}
        	else 
        	{
        		opcUs = Integer.parseInt(request.getParameter("tipoUsuarioUpdate"));
        	}
        } else 
        {
        	opcUs = Integer.parseInt(request.getParameter("tipoUsuario"));
        }
        String accion = request.getParameter("accion");

        if("buscar".equals(accion)) 
        {
            if(request.getParameter("dniBuscado").isBlank()) 
            {
            	List<Usuario> administradores = usServ.getAllAdministradores();
        		List<Profesional> profesionales = profServ.getAll();
        		List<Paciente> pacientes = pacServ.getAll();
        		List<ObraSocial> obrasSociales = osServ.getAllActivas();
        		request.setAttribute("tablaAdministradores", administradores);
        		request.setAttribute("tablaProfesionales", profesionales);
        		request.setAttribute("tablaPacientes", pacientes);
        		request.setAttribute("obrasSociales", obrasSociales);
        		request.setAttribute("tipoUsuarioSeleccionado", opcUs);
            	request.getRequestDispatcher("abmUsuario.jsp").forward(request,response);
            }
            else 
            {
            	int dniBuscado = Integer.parseInt(request.getParameter("dniBuscado"));
            	switch(opcUs) 
            	{
    	        	case 1: 
    	        	{
    	        		List<Usuario> administradores = usServ.getAllAdministradoresPorDNI(dniBuscado);
    	        		request.setAttribute("tablaAdministradores", administradores);
    	        		request.setAttribute("tipoUsuarioSeleccionado", opcUs);
    	        		request.setAttribute("dniBuscado", dniBuscado);
    	            	request.getRequestDispatcher("abmUsuario.jsp").forward(request,response);
    	            	break;
    	        	}
    	        	case 2:
    	        	{
    	        		List<Profesional> profesionales = profServ.getAllPorDNI(dniBuscado);
    	        		request.setAttribute("tablaProfesionales", profesionales);
    	        		request.setAttribute("tipoUsuarioSeleccionado", opcUs);
    	        		request.setAttribute("dniBuscado", dniBuscado);
    	            	request.getRequestDispatcher("abmUsuario.jsp").forward(request,response);
    	            	break;
    	        	}
    	        	case 3: 
    	        	{
    	        		List<Paciente> pacientes = pacServ.getAllPorDNI(dniBuscado);
    	        		request.setAttribute("tablaPacientes", pacientes);
    	        		request.setAttribute("tipoUsuarioSeleccionado", opcUs);
    	        		request.setAttribute("dniBuscado", dniBuscado);
    	            	request.getRequestDispatcher("abmUsuario.jsp").forward(request,response);
    	            	break;
    	        	}
            	}
            }
        }
        else 
        {
        	Integer dni = Integer.parseInt(request.getParameter("dni"));
    		String apellido = request.getParameter("apellido");
    		String nombre = request.getParameter("nombre"); 
            String email = request.getParameter("email"); 
            LocalDate fecha_nacimiento = LocalDate.parse(request.getParameter("fechaNacimiento")); 
            String telefono = request.getParameter("telefono");
            String contraseña = request.getParameter("contraseña"); 
            String genero = request.getParameter("genero");
            String modal = request.getParameter("idModal");
            String respuestaOperacion = null;
            String mensaje = null;
        	switch(opcUs) {
            case 1: {               
                    Usuario us;
					try {
						us = new Usuario(dni,apellido,nombre,email,fecha_nacimiento,telefono, contraseña, genero,1);
						if(usServ.validarAdministrador(us)) 
						{							
							if("altaUsuario".equals(modal)) 
							{
								respuestaOperacion = "El Administrador ya existe.";
							}
							else 
							{
								if (request.getParameter("contraseña").isBlank()) 
								{
									respuestaOperacion = usServ.updateUsuarioSinContraseña(us);
								} else 
								{
									respuestaOperacion = usServ.updateUsuarioConContraseña(us);
								}
							}
						}
						 else 
						 {
							    respuestaOperacion = usServ.insertarUsuario(us);
						 }
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}                 
                break;              
            }

            case 2: { 
                    if(request.getParameter("matricula").isBlank()) {
                    	respuestaOperacion ="Debe ingresar la matrícula.";
                    	break;
                    }
                    else {
                    	Integer matricula = Integer.parseInt(request.getParameter("matricula"));
    					try {
    						Profesional prof = new Profesional(dni, apellido, nombre, email,fecha_nacimiento,telefono,contraseña,genero, matricula,opcUs);
    						if(profServ.validarProfesional(prof)) 
    						{
    							if("altaUsuario".equals(modal)) 
    							{
    								respuestaOperacion = "El Profesional ya existe.";
    							}
    							else 
    							{
    								if(request.getParameter("contraseña").isBlank()) 
    								{
    									respuestaOperacion = profServ.updateProfesionalSinContraseña(prof);
    								}
    								else 
    								{
    									respuestaOperacion = profServ.updateProfesionalConContraseña(prof);
    								}
    							}
    						}
    						else 
    						{
    							respuestaOperacion = profServ.insertarProfesional(prof);
    						}
    					} catch (NumberFormatException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} catch (ParseException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					break; 
                    }
            }

            case 3:{               
                    Paciente pac = new Paciente();
            		Integer id_obra_social = Integer.parseInt(request.getParameter("id_obra_social"));
                    if(id_obra_social==0) 
                    {
                    	pac.setDni(dni); 
                    	pac.setApellido(apellido);
                    	pac.setNombre(nombre);
                    	pac.setEmail(email);
                    	pac.setFecha_nacimiento(fecha_nacimiento);
                    	pac.setTelefono(telefono);
                    	pac.setClave(contraseña);
                    	pac.setGenero(genero);
                    	pac.setTipo_usuario(3);
                    	pac.setObra_social(null);
                    	pac.setNro_afiliado(null);
                    }
                    else 
                    {
                        ObraSocialService osServ = new ObraSocialService();
                        ObraSocial obraSocial = osServ.getObraSocial(id_obra_social);
                        String nroAfiliado = request.getParameter("nroAfiliado");
                        pac.setDni(dni); 
                    	pac.setApellido(apellido);
                    	pac.setNombre(nombre);
                    	pac.setEmail(email);
                    	pac.setFecha_nacimiento(fecha_nacimiento);
                    	pac.setTelefono(telefono);
                    	pac.setClave(contraseña);
                    	pac.setGenero(genero);
                    	pac.setTipo_usuario(3);
                    	pac.setObra_social(obraSocial);
                    	pac.setNro_afiliado(nroAfiliado);
                    }                    
					try {
						if(pacServ.validarPaciente(pac)) 
						{
							if("altaUsuario".equals(modal)) 
							{
								respuestaOperacion ="El Paciente ya existe.";
							}
							else 
							{
								if(request.getParameter("contraseña").isBlank()) 
								{
									respuestaOperacion = pacServ.updatePacienteSinContraseña(pac);
								}
								else 
								{
									respuestaOperacion = pacServ.updatePacienteConContraseña(pac);
								}
							}
						}
						else 
						{
							respuestaOperacion = pacServ.insertarPaciente(pac);
						}
					} catch (NumberFormatException	 e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}            
					break;	               
            }
        }  
        if ("OK".equals(respuestaOperacion)) {
			mensaje = "Operacion realizada correctamente";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("tipoUsuarioSeleccionado", opcUs);
			this.doGet(request, response);
		} 
        else 
        {
			mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);
			this.doGet(request, response);
		}     
    }
   }
}