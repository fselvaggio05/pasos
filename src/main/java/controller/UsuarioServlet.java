package controller;

import entity.Paciente;
import entity.Profesional;
import entity.Usuario;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/usuarios")

public class UsuarioServlet extends HttpServlet {

    public UsuarioService usServ;

    public PacienteService pacServ;

    public ProfesionalService profServ;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioService usServ = new UsuarioService();
        Integer opcUs = Integer.parseInt(req.getParameter("tipoUsuario"));

        //TODO AGREGAR ENVIO DE MAIL LUEGO DE LA CREACION DEL PACIENTE Y PROFESIONAL

        switch(opcUs) {
            case 1: {
                try {
                    Usuario us = new Usuario(Integer.parseInt(req.getParameter("dni")), req.getParameter("nombre"), req.getParameter("apellido"), req.getParameter("email"), req.getParameter("fechaNac"), req.getParameter("telefono"), req.getParameter("clave"), req.getParameter("genero"));
                    usServ.insertarUsuario(us);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case 2: { //TODO REVISAR MATRICULA QUE LLEGA EN NULL
                try {
                    String matricula = req.getParameter("matricula");
                    Profesional prof = new Profesional(Integer.parseInt(req.getParameter("dni")), req.getParameter("nombre"), req.getParameter("apellido"), req.getParameter("email"), req.getParameter("fechaNac"), req.getParameter("telefono"), req.getParameter("clave"), req.getParameter("genero") ,req.getParameter("matricula"));
                    ProfesionalService profServ = new ProfesionalService();
                    profServ.insertarProfesional(prof);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case 3:{
                try {
                    Integer obraSocial = Integer.parseInt(req.getParameter("obraSocial"));
                    Paciente pac = new Paciente(Integer.parseInt(req.getParameter("dni")), req.getParameter("nombre"), req.getParameter("apellido"), req.getParameter("email"), req.getParameter("fechaNac"), req.getParameter("telefono"), req.getParameter("clave"), req.getParameter("genero"), obraSocial , req.getParameter("nroAfiliado"));
                    PacienteService pacServ = new PacienteService();
                    pacServ.insertarPaciente(pac);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

        }



    }





}