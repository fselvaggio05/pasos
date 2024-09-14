package controller;

import entity.Usuario;
import service.EnvioMailService;
import service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {

    private UsuarioService usServ;
    private EnvioMailService envMailServ;
    
    public LoginServlet ()
    {
    	this.usServ = new UsuarioService();
    	this.envMailServ = new EnvioMailService();
    }
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    
        UsuarioService usServ = new UsuarioService();
        Usuario us = new Usuario();
        
    	String operacion;
    	
    	operacion = req.getParameter("operacion");
    	
    	switch(operacion)
    	{
    	
    	case "ingresar":
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
    	
    	
    	case "registrar":
    	{
    		
    	}
    	
    	
    	
    	}
    	
    	
    }
    

    
  


}

