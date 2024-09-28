package controller;

import entity.ObraSocial;
import entity.Paciente;
import entity.Usuario;
import service.EnvioMailService;
import service.ObraSocialService;
import service.PacienteService;
import service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {

    
    private EnvioMailService envMailServ;
    public ObraSocialService osServ = new ObraSocialService();
    private static final long serialVersionUID = 1L;
	private PacienteService pacServ;
    

	public LoginServlet ()
    {
    	this.envMailServ = new EnvioMailService();
    	this.pacServ = new PacienteService();
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	List<ObraSocial> obrasSociales = osServ.getAllActivas();
    	req.setAttribute("obrasSociales", obrasSociales);
    	req.getRequestDispatcher("index.jsp").forward(req,resp);
	}
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    
        UsuarioService usServ = new UsuarioService();
        Usuario us = new Usuario();
        
    	String operacion;
    	String respuesta=null;
    	
    	operacion = req.getParameter("operacion");
    	
    	switch(operacion)
    	{
    	
    	case "login":
    	{    		
    		
          
            Integer dni = usServ.buscarDniUsuario(req.getParameter("mail"));

            if(dni==null)
            {
                req.setAttribute("error", "Mail no registrado");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }

            else
            {
            	us = usServ.buscarUsuario(dni,req.getParameter("pass"));
            	
            	if(us!=null)
            	{
            		 HttpSession session = req.getSession(true);
                     session.setAttribute("usuario", us);
                     session.setAttribute("rol", us.getTipo_usuario());                 
                     resp.sendRedirect(req.getContextPath() + "/menu_final.jsp");
            	}               
            }
            
            break;
    	}
    	
    	
    	case "recuperar":
    	{
    		   		
    		us.setEmail(req.getParameter("mailRecupera"));
    		us.setClave(usServ.generarClave());    		
    		usServ.setearClaveGenerada(us);
    		envMailServ.enviarEmail(us);
    	}
    	
    	
    	case "registro":
					    	{
					    		Integer dni = Integer.parseInt(req.getParameter("dni"));
					    		String apellido = req.getParameter("apellido");
					    		String nombre = req.getParameter("nombre"); 
					            String email = req.getParameter("email"); 
					            LocalDate fecha_nacimiento = LocalDate.parse(req.getParameter("fechaNacimiento")); 
					            String telefono = req.getParameter("telefono");
					            String contraseña = req.getParameter("contraseña"); 
					            String genero = req.getParameter("genero");
					            Integer id_obra_social = Integer.parseInt(req.getParameter("id_obra_social"));
			                    ObraSocialService osServ = new ObraSocialService();
			                    ObraSocial obraSocial = osServ.getObraSocial(id_obra_social);
			                    String nroAfiliado = req.getParameter("nroAfiliado");
								Paciente unPaciente;
								try {
									unPaciente = new Paciente(dni, apellido, nombre, email, fecha_nacimiento, telefono, contraseña, genero, 3,obraSocial,nroAfiliado);
									respuesta = this.pacServ.insertarPaciente(unPaciente);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
								req.setAttribute("mensaje", respuesta);
								this.doGet(req, resp);
								break;
					    	}
    	
    	
    	
    	}
    	
    	
    }
					           
}