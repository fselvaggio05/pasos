package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import entity.Horario;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
import entity.Profesional;
import entity.Turno;
import entity.MontosPractica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.HorarioService;
import service.PacienteService;
import service.PracticaService;
import service.PrescripcionService;
import service.ProfesionalService;
import service.TurnoService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

@WebServlet("/listadodiscapacidad")
public class ListadoDiscapacidadServlet extends HttpServlet {

    private TurnoService turServ;
    private PacienteService pacServ;
    private PrescripcionService prescServ;
    private ProfesionalService profServ;

    public ListadoDiscapacidadServlet() {
        this.turServ = new TurnoService();
        this.pacServ = new PacienteService();
        this.prescServ = new PrescripcionService();
        this.profServ = new ProfesionalService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profesional> profesionales = profServ.getAll(); 
        request.setAttribute("profesionales", profesionales);
        request.getRequestDispatcher("ListadoDiscapacidad.jsp").forward(request, response);    
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacion = request.getParameter("operacion");
        String respuestaOperacion = null;
        HttpSession sesion = request.getSession();     
        List<Turno> tur = null;
        
        switch (operacion) {
        case "listado": 
        {   
            Integer matricula = Integer.parseInt(request.getParameter("profesional"));
            Profesional profesional = profServ.getProfesional(matricula);
            LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
            LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
            tur = turServ.buscarTurnosAsistidosDiscapacidad(fecha_desde, fecha_hasta, profesional.getMatricula());
            
            if(tur.size() == 0) {
                respuestaOperacion = "No existen turnos de discapacidad pendientes de cobro para el período ingresado";
            }                                               
            request.setAttribute("turnos", tur);
            request.setAttribute("profesionalSeleccionado", profesional.getMatricula());
            request.setAttribute("fecha_desde", fecha_desde);
            request.setAttribute("fecha_hasta", fecha_hasta);
            request.setAttribute("mensaje", respuestaOperacion);
            this.doGet(request, response);
            break;
        }

        case "exportar": 
        {   
            LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
            LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
            Integer matricula = Integer.parseInt(request.getParameter("profesional"));
            Profesional profesional = profServ.getProfesional(matricula);
            request.setAttribute("fecha_desde", fecha_desde);
            request.setAttribute("fecha_hasta", fecha_hasta);
            request.setAttribute("turnos", tur);
            request.setAttribute("profesionalSeleccionado", profesional.getMatricula());
            
            Document documento = new Document();

            try {
                PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\Pich\\Documents\\Prescripciones medicas de Discapacidad - " + profesional.getApellido() + ", " + profesional.getNombre() + " - Periodo " + fecha_desde + " - " + fecha_hasta + ".pdf"));
                documento.open();

                Image imagenClinica = Image.getInstance("C:\\Users\\Pich\\Documents\\pasosClinica.png");
                imagenClinica.scaleToFit(100, 100);
                imagenClinica.setAlignment(Element.ALIGN_CENTER);
                documento.add(imagenClinica);

                Paragraph datosClinica = new Paragraph("Pasos - Kinesiología Integral - Email: contacto@clinicapasos.com", FontFactory.getFont(FontFactory.HELVETICA, 12));
                datosClinica.setAlignment(Element.ALIGN_CENTER);
                Paragraph datosClinica2 = new Paragraph("Remito de Prestaciones de Discapacidad Fisio - Kinésicas", FontFactory.getFont(FontFactory.HELVETICA, 12));
                datosClinica2.setAlignment(Element.ALIGN_CENTER);
                Paragraph datosClinica3 = new Paragraph("Profesional: " + profesional.getApellido() + ", " + profesional.getNombre() + " - Matrícula: " + profesional.getMatricula(), FontFactory.getFont(FontFactory.HELVETICA, 12));
                datosClinica3.setAlignment(Element.ALIGN_CENTER);
                documento.add(datosClinica);
                documento.add(datosClinica2);
                documento.add(datosClinica3);

                documento.add(new Paragraph("\n"));

                tur = turServ.buscarTurnosAsistidosDiscapacidad(fecha_desde, fecha_hasta, profesional.getMatricula());
                Map<String, Integer> groupedTurnos = groupTurnos(tur);
                Map<String, List<Double>> practicaPrecios = getPracticaPrecios(tur);

                PdfPTable table = new PdfPTable(7); // Cambiado a 7 columnas
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Obra Social"));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Práctica"));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Precio unitario"));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Paciente"));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Fecha"));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Sesiones asistidas"));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Total"));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);

                for (Map.Entry<String, Integer> entry : groupedTurnos.entrySet()) {
                    String[] keys = entry.getKey().split(";");
                    String practicaDescripcion = keys[1];
                    List<Double> precios = practicaPrecios.get(practicaDescripcion);

                    table.addCell(keys[0]); // Obra Social
                    table.addCell(keys[1]); // Práctica

                    // Combinar los precios en una sola celda
                    StringBuilder preciosStr = new StringBuilder();
                    double precioUnitario = 0;
                    if (!precios.isEmpty()) {
                        precioUnitario = precios.get(0); // Usamos el primer precio como precio unitario
                        preciosStr.append(precioUnitario);
                    }
                    table.addCell(preciosStr.toString()); // Precio unitario

                    table.addCell(keys[2]); // Paciente
                    table.addCell(keys[3]); // Fecha
                    int sesionesAsistidas = entry.getValue();
                    table.addCell(String.valueOf(sesionesAsistidas)); // Sesiones asistidas

                    double total = precioUnitario * sesionesAsistidas;
                    table.addCell(String.valueOf(total)); // Total
                }

                documento.add(table);
                documento.close();

                respuestaOperacion = "Documento exportado exitosamente";
                request.setAttribute("mensaje", respuestaOperacion);
                this.doGet(request, response);
                
            } catch (FileNotFoundException | DocumentException e) {
                e.printStackTrace();
            }

            break;
        }
        
        }
    }
    
    private Map<String, Integer> groupTurnos(List<Turno> turnos) {
        Map<String, Integer> groupedTurnos = new HashMap<>();

        for (Turno turno : turnos) {
            String key = turno.getPaciente().getObra_social().getNombre_os() + ";" +
                         turno.getHorario().getPractica().getDescripcion() + ";" +
                         turno.getPaciente().getApellido() + ", " + turno.getPaciente().getNombre() + ";" +
                         turno.getFecha_t().toString();

            groupedTurnos.put(key, groupedTurnos.getOrDefault(key, 0) + 1);
        }

        return groupedTurnos;
    }

    private Map<String, List<Double>> getPracticaPrecios(List<Turno> turnos) {
        Map<String, List<Double>> practicaPrecios = new HashMap<>();

        for (Turno turno : turnos) {
            String practicaDescripcion = turno.getHorario().getPractica().getDescripcion();
            List<MontosPractica> montos = turno.getHorario().getPractica().getMontos();
            List<Double> precios = new ArrayList<>();
            for (MontosPractica monto : montos) {
                precios.add(monto.getMonto()); 
            }
            practicaPrecios.put(practicaDescripcion, precios);
        }

        return practicaPrecios;
    }
}