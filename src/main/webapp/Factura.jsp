<%@page import="net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter"%>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="config.Conexion" %>
<%@ page import="net.sf.jasperreports.engine.export.JRPdfExporter" %>
<%@ page import="net.sf.jasperreports.export.SimpleExporterInput" %>
<%@ page import="net.sf.jasperreports.export.SimpleOutputStreamExporterOutput" %>

<%
    // Obtener el formato de exportación seleccionado por el usuario
    String formatoExportacion = request.getParameter("formatoExportacion");
    
    if (formatoExportacion == null) {
        formatoExportacion = "pdf"; // Valor por defecto si no se proporciona ninguno
    }

    // Establecer encabezados y el nombre del archivo basado en el formato
    if (formatoExportacion.equals("excel")) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Reporte.xlsx");
    } else {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Reporte.pdf");
    }

    // Ruta al archivo .jasper
    String reportPath = request.getServletContext().getRealPath("/reportes/Fact.jasper.jasper");

    // Parámetros del informe (agrega parámetros según sea necesario)
    Map<String, Object> parameters = new HashMap<>();
    // Ejemplo: parameters.put("idProducto", 1);

    Connection connection = null;
    ServletOutputStream outputStream = null;
    try {
        Conexion conexionDB = new Conexion();
        connection = conexionDB.getConnection(); // Obtener la conexión utilizando la clase Conexion

        // Llenar el informe con datos de la base de datos y parámetros
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, connection);

        // Exportar el informe basado en el formato seleccionado
        if (formatoExportacion.equals("excel")) {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            outputStream = response.getOutputStream();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
        } else {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            outputStream = response.getOutputStream();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
        }
        
        outputStream.flush(); // Asegurar que todo el contenido se envíe al cliente

    } catch (JRException | SQLException | IOException e) {
        e.printStackTrace(); // Manejar adecuadamente las excepciones
        response.setContentType("text/html");
        out.println("<html><body><h3>Error al generar el informe:</h3>");
        out.println("<pre>" + e.getMessage() + "</pre>");
        out.println("</body></html>");
    } finally {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace(); // Manejar adecuadamente las excepciones
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejar adecuadamente las excepciones
            }
        }
    }
%>

<h1>Exportar Base de Datos</h1>
<form action="reporte1.jsp" method="post">
    <!-- Selección de formato de exportación -->
    <label for="formatoExportacion">Formato de Exportación:</label>
    <select id="formatoExportacion" name="formatoExportacion">
        <option value="pdf">PDF</option>
        <option value="excel">Excel</option>
        <!-- Agrega más opciones según sea necesario -->
    </select>
    <button type="submit">Exportar</button>
</form>
