package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Paciente;
import entity.Profesional;
import entity.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.PacienteService;
import service.PrescripcionService;
import service.ProfesionalService;
import service.TurnoService;

@WebServlet("/listadoambulatorias")
public class ListadoAmbulatoriasServlet extends HttpServlet {

    private TurnoService turServ;
    private PacienteService pacServ;
    private PrescripcionService prescServ;
    private ProfesionalService profServ;

    public ListadoAmbulatoriasServlet() {
        this.turServ = new TurnoService();
        this.pacServ = new PacienteService();
        this.prescServ = new PrescripcionService();
        this.profServ = new ProfesionalService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profesional> profesionales = profServ.getAll();
        request.setAttribute("profesionales", profesionales);
        request.getRequestDispatcher("ListadoAmbulatorias.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String operacion = request.getParameter("operacion");
        String respuestaOperacion = null;    
        List<Turno> tur = null;

        switch (operacion) {
            case "listado": {
                // Validaciones
                
            	 Integer matricula = Integer.parseInt(request.getParameter("profesional"));
                 Profesional profesional = profServ.getProfesional(matricula);
                 LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
                 LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
                 
                 request.setAttribute("fecha_desde", fecha_desde);
                 request.setAttribute("fecha_hasta", fecha_hasta);
                 request.setAttribute("turnos", tur);
                 request.setAttribute("profesionalSeleccionado", profesional.getMatricula());
            	
            	
                if (matricula == null || (matricula) == 1) {
                    respuestaOperacion = "Seleccione un profesional";
                    request.setAttribute("mensaje", respuestaOperacion);
                    this.doGet(request, response);
                    return;
                }

                if (fecha_desde == null ) {
                    respuestaOperacion = "Ingrese fecha de inicio del período";
                    request.setAttribute("mensaje", respuestaOperacion);
                    this.doGet(request, response);
                    return;
                }

                if (fecha_hasta == null) {
                    respuestaOperacion = "Ingrese fecha final del período";
                    request.setAttribute("mensaje", respuestaOperacion);
                    this.doGet(request, response);
                    return;
                }

                if (profesional == null) {
                	respuestaOperacion = "Seleccione un profesional";
                	request.setAttribute("mensaje", respuestaOperacion);
                    this.doGet(request, response);
                    return;
                }
                
                tur = turServ.buscarTurnosAsistidosAmbulatorios(fecha_desde, fecha_hasta, profesional.getMatricula());

                if (tur.isEmpty()) {
                    respuestaOperacion = "No existen turnos ambulatorios pendientes de cobro para el período ingresado";
                }
                request.setAttribute("turnos", tur);
                request.setAttribute("profesionalSeleccionado", profesional.getMatricula());
                request.setAttribute("fecha_desde", fecha_desde);
                request.setAttribute("fecha_hasta", fecha_hasta);
                request.setAttribute("mensaje", respuestaOperacion);
                this.doGet(request, response);
                break;
            }

            case "exportar": {
                // Validaciones
            	 Integer matricula = Integer.parseInt(request.getParameter("profesional"));
                 Profesional profesional = profServ.getProfesional(matricula);
                 LocalDate fecha_desde = LocalDate.parse(request.getParameter("fecha_desde"));
                 LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fecha_hasta"));
                 request.setAttribute("fecha_desde", fecha_desde);
                 request.setAttribute("fecha_hasta", fecha_hasta);
                 request.setAttribute("turnos", tur);
                 request.setAttribute("profesionalSeleccionado", profesional.getMatricula());
            	
                if (matricula == null || (matricula) == 1) {
                    respuestaOperacion = "Seleccione un profesional";
                    request.setAttribute("mensaje", respuestaOperacion);
                    this.doGet(request, response);
                    return;
                }

//                if (fecha_desde == null ) {
//                    respuestaOperacion = "Ingrese fecha de inicio del período";
//                    request.setAttribute("mensaje", respuestaOperacion);
//                    this.doGet(request, response);
//                    return;
//                }

//                if (fecha_hasta == null) {
//                    respuestaOperacion = "Ingrese fecha final del período";
//                    request.setAttribute("mensaje", respuestaOperacion);
//                    this.doGet(request, response);
//                    return;
//                }

                if (profesional == null) {
                	respuestaOperacion = "Seleccione un profesional";
                	request.setAttribute("mensaje", respuestaOperacion);
                    this.doGet(request, response);
                    return;
                }
               
                
                
                              
                
                request.setAttribute("fecha_desde", fecha_desde);
                request.setAttribute("fecha_hasta", fecha_hasta);
                request.setAttribute("turnos", tur);
                request.setAttribute("profesionalSeleccionado", profesional.getMatricula());

                Document documento = new Document();
                try {
                    PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\Pich\\Documents\\Prescripciones medicas - " + profesional.getApellido() + ", " + profesional.getNombre() + " - Periodo " + fecha_desde + " - " + fecha_hasta + ".pdf"));
                    documento.open();

                    // Agrega imagen en el encabezado
                    Image imagenClinica = Image.getInstance("C:\\Users\\Pich\\Documents\\pasosClinica.png");
                    imagenClinica.scaleToFit(100, 100);
                    imagenClinica.setAlignment(Element.ALIGN_CENTER);
                    documento.add(imagenClinica);

                    // Agrega datos de la clínica
                    Paragraph datosClinica = new Paragraph("Pasos - Kinesiología Integral - Email: contacto@clinicapasos.com", FontFactory.getFont(FontFactory.HELVETICA, 12));
                    datosClinica.setAlignment(Element.ALIGN_CENTER);
                    Paragraph datosClinica2 = new Paragraph("Remito de Prestaciones Fisio - Kinésicas", FontFactory.getFont(FontFactory.HELVETICA, 12));
                    datosClinica2.setAlignment(Element.ALIGN_CENTER);
                    Paragraph datosClinica3 = new Paragraph("Profesional: " + profesional.getApellido() + ", " + profesional.getNombre() + " - Matrícula: " + profesional.getMatricula(), FontFactory.getFont(FontFactory.HELVETICA, 12));
                    datosClinica3.setAlignment(Element.ALIGN_CENTER);
                    documento.add(datosClinica);
                    documento.add(datosClinica2);
                    documento.add(datosClinica3);
                    documento.add(new Paragraph("\n"));

                    tur = turServ.buscarTurnosAsistidosAmbulatorios(fecha_desde, fecha_hasta, profesional.getMatricula());

                    // Agrupar turnos
                    Map<GroupingKey, Integer> agrupados = new HashMap<>();
                    for (Turno t : tur) {
                        GroupingKey key = new GroupingKey(
                                t.getPaciente().getObra_social().getNombre_os(),
                                t.getHorario().getPractica().getDescripcion(),
                                t.getPaciente().getApellido() + ", " + t.getPaciente().getNombre(),
                                t.getFecha_t());
                        agrupados.putIfAbsent(key, t.getPrescripcion().getSesiones_asistidas());
                    }

                    // Crear encabezado del reporte
                    documento.add(new Paragraph("Prescripciones Médicas para el período: " + fecha_desde + " - " + fecha_hasta));
                    documento.add(new Paragraph("\n"));

                    // Crear tabla con 5 columnas
                    PdfPTable table = new PdfPTable(5);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    // Agregar encabezados de columna
                    PdfPCell cell;
                    cell = new PdfPCell(new Phrase("Obra Social"));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase("Práctica"));
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

                    // Agregar filas agrupadas
                    for (Map.Entry<GroupingKey, Integer> entry : agrupados.entrySet()) {
                        GroupingKey key = entry.getKey();
                        Integer sesiones = entry.getValue();
                        table.addCell(key.obraSocial);
                        table.addCell(key.practica);
                        table.addCell(key.paciente);
                        table.addCell(key.fecha.toString());
                        table.addCell(sesiones.toString());
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

            // ... otros casos ...
        }
    }

    // Clase auxiliar para agrupar
    private static class GroupingKey {
        private String obraSocial;
        private String practica;
        private String paciente;
        private LocalDate fecha;

        public GroupingKey(String obraSocial, String practica, String paciente, LocalDate fecha) {
            this.obraSocial = obraSocial;
            this.practica = practica;
            this.paciente = paciente;
            this.fecha = fecha;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GroupingKey that = (GroupingKey) o;
            return obraSocial.equals(that.obraSocial) &&
                    practica.equals(that.practica) &&
                    paciente.equals(that.paciente) &&
                    fecha.equals(that.fecha);
        }

        @Override
        public int hashCode() {
            return Objects.hash(obraSocial, practica, paciente, fecha);
        }
    }
}