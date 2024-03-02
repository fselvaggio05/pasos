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
    	if (request.getParameter("tipoUsuario")==null) {
        	opcUs = Integer.parseInt(request.getParameter("tipoUsuarioUpdate"));
        } else {
        	opcUs = Integer.parseInt(request.getParameter("tipoUsuario"));
        }
        Integer dni = Integer.parseInt(request.getParameter("dni"));
		String apellido = request.getParameter("apellido");
		String nombre = request.getParameter("nombre"); 
        String email = request.getParameter("email"); 
        LocalDate fecha_nacimiento = LocalDate.parse(request.getParameter("fechaNacimiento")); 
        String telefono = request.getParameter("telefono");
        String contraseña = request.getParameter("contraseña"); 
        String genero = request.getParameter("genero");
        String respuestaOperacion = null;
        String mensaje = null;

        //TODO AGREGAR ENVIO DE MAIL LUEGO DE LA CREACION DEL PACIENTE Y PROFESIONAL

        switch(opcUs) {
            case 1: {               
                    Usuario us;
					try {
						us = new Usuario(dni,apellido,nombre,email,fecha_nacimiento,telefono, contraseña, genero);
						if(usServ.validarAdministrador(us)) {							
							//deberia mostrar que YA existe y si queres updatear no hacer el update automatico, mostrando mensajes con datos del usuario actual.
							if (request.getParameter("contraseña").isBlank()) {
								respuestaOperacion = usServ.updateUsuarioSinContraseña(us);
							} else {
								respuestaOperacion = usServ.updateUsuarioConContraseña(us);
							}
						} else {
					    respuestaOperacion = usServ.insertarUsuario(us);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}                 
                break;              
            }

            case 2: { //TODO REVISAR MATRICULA QUE LLEGA EN NULL
                    Integer matricula = Integer.parseInt(request.getParameter("matricula"));
					try {
						Profesional prof = new Profesional(dni, apellido, nombre, email,fecha_nacimiento,telefono,contraseña,genero, matricula);
						if(profServ.validarProfesional(prof)) 
						{
							//deberia mostrar que YA existe y si queres updatear no hacer el update automatico, mostrando mensajes con datos del usuario actual.
							if(request.getParameter("contraseña").isBlank()) 
							{
								respuestaOperacion = profServ.updateProfesionalSinContraseña(prof);
							}
							else 
							{
								respuestaOperacion = profServ.updateProfesionalConContraseña(prof);
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

            case 3:{               
                    Integer obraSocial = Integer.parseInt(request.getParameter("id_obra_social"));
                    String nroAfiliado = request.getParameter("nroAfiliado");
					try {
						Paciente pac = new Paciente(dni, apellido, nombre, email, fecha_nacimiento, telefono, contraseña, genero, obraSocial,nroAfiliado);
						if(pacServ.validarPaciente(pac)) 
						{
							//deberia mostrar que YA existe y si queres updatear no hacer el update automatico, mostrando mensajes con datos del usuario actual.
							if(request.getParameter("contraseña").isBlank()) 
							{
								respuestaOperacion = pacServ.updatePacienteSinContraseña(pac);
							}
							else 
							{
								respuestaOperacion = pacServ.updatePacienteConContraseña(pac);
							}
						}
						else 
						{
							respuestaOperacion = pacServ.insertarPaciente(pac);
						}
					} catch (NumberFormatException | ParseException e) {
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
		} else {
			mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);
			this.doGet(request, response);
		}     
    }
}