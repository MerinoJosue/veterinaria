package Controlador;

import config.Fecha;
import Modelo.Carrito;

import Modelo.Compra;
import Modelo.ComprasDAO;
import Modelo.DAOUSUARIO;

import Modelo.DetalleCompra;
import Modelo.Pago;

import Modelo.Productos;
import Modelo.ProductosDAO;
import Modelo.usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Controlador extends HttpServlet {

    Pago pago = new Pago();
    usuario cl = new usuario();
    DAOUSUARIO udao = new DAOUSUARIO();
    ComprasDAO cdao = new ComprasDAO();
    ProductosDAO pdao = new ProductosDAO();
    Productos p = new Productos();
    int item = 0;
    int cantidad = 1;
    double subtotal = 0.0;
    double totalPagar = 0.0;

    List<Carrito> listaProductos = new ArrayList<>();
    List productos = new ArrayList();

    int idcompra;
    int idpago;
    double montopagar;
    int idProducto = 0;

    Carrito car = new Carrito();

    Fecha fechaSistem = new Fecha();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productos = pdao.listar();
        String accion = request.getParameter("accion");

        switch (accion) {

            case "carrito":
                totalPagar = 0.0;
                item = 0;
                request.setAttribute("Carrito", listaProductos);
                for (int i = 0; i < listaProductos.size(); i++) {
                    totalPagar = totalPagar + listaProductos.get(i).getSubTotal();
                    listaProductos.get(i).setItem(item + i + 1);
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("vistas/carrito.jsp").forward(request, response);
                break;

            case "RealizarPago":
                realizarPago(request, response);
                break;

            case "Comprar":
                agregarCarrito(request);
                request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);
                break;
            case "AgregarCarrito":
                agregarCarrito(request);
                request.setAttribute("cont", listaProductos.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;

            case "deleteProducto":
                idProducto = Integer.parseInt(request.getParameter("id"));
                if (listaProductos != null) {
                    for (int j = 0; j < listaProductos.size(); j++) {
                        if (listaProductos.get(j).getId_Producto() == idProducto) {
                            listaProductos.remove(j);
                        }
                    }
                }
            case "updateCantidad":
                idProducto = Integer.parseInt(request.getParameter("id"));
                int cant = Integer.parseInt(request.getParameter("cantidad"));
                for (int j = 0; j < listaProductos.size(); j++) {
                    if (listaProductos.get(j).getId_Producto() == idProducto) {
                        listaProductos.get(j).setCantidad(cant);
                        listaProductos.get(j).setSubTotal(listaProductos.get(j).getPrecioCompra() * cant);
                    }
                }
                break;

            case "Nuevo":
                listaProductos = new ArrayList();
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "Buscar":
                String nombre = request.getParameter("txtbuscar");
                productos = pdao.buscar(nombre);
                request.setAttribute("cont", listaProductos.size());
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "GenerarCompra":
                GenerarCompra(request, response);
                break;

            case "MisCompras":
                MisCompras(request, response);
                break;
            case "verDetalle":
                totalPagar = 0.0;
                int idcompras = Integer.parseInt(request.getParameter("idcompra"));
                List<DetalleCompra> detalle = cdao.Detalle(idcompras);

                // Configurar el documento PDF
                Document document = new Document();
                try {
                    // Configurar el OutputStream para enviar el PDF como respuesta
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=\"detalle_compra_" + idcompras + ".pdf\"");
                    OutputStream out = response.getOutputStream();

                    // Crear el escritor para el documento PDF
                    PdfWriter.getInstance(document, out);
                    document.open();

                    // Agregar encabezado
                    // Agregar el logo
                    try {
                        String logoPath = getServletContext().getRealPath("../reportes/logohyp.jpg"); // Ajusta la ruta del logo
                        Image logo = Image.getInstance(logoPath);
                        logo.setAlignment(Element.ALIGN_CENTER);
                        logo.scaleToFit(200, 100); // Ajusta el tamaño del logo según tus necesidades
                        document.add(logo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Agregar título
                    document.add(new Paragraph("HappyPet", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.BOLD)));

                    // Agregar información adicional
                    document.add(new Paragraph("RUC: 12345678901")); // Ajusta el RUC
                    document.add(new Paragraph("Dirección: Mercado ASPROPA puesto 260, Av. Luna Pizarro de, San Martín de Porres 15103 ")); // Ajusta la dirección
                    document.add(new Paragraph(" "));

                    // Agregar boleta de venta electrónica
                    document.add(new Paragraph("Boleta de Venta Electrónica", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.BOLD)));
                    document.add(new Paragraph("Nombre del Archivo: detalle_compra_" + idcompras + ".pdf"));
                    document.add(new Paragraph(" "));

                    // Agregar contenido del documento
                    document.add(new Paragraph("Detalles de Compra"));
                    document.add(new Paragraph("ID Compra: C00" + idcompras));

                    // Verificar si la lista de detalles no está vacía
                    if (detalle != null && !detalle.isEmpty()) {
                        document.add(new Paragraph(" "));
                        document.add(new Paragraph("Detalles de la compra:"));

                        // Crear tabla para los detalles de la compra
                        PdfPTable table = new PdfPTable(5); // 4 columnas: Producto, Cantidad, Precio Unitario, Subtotal
                        table.setWidthPercentage(100);

                        // Agregar encabezados a la tabla
                        table.addCell("Producto");
                        table.addCell("Cantidad");
                        table.addCell("Precio Unitario");
                        table.addCell("IGV 18%");
                        table.addCell("Subtotal");

                        double totalPagar = 0.0;
                        for (DetalleCompra dc : detalle) {
                            table.addCell(dc.getProducto().getNombres());
                            table.addCell(String.valueOf(dc.getCantidad()));
                            double precioOriginal = dc.getPrecioCompra();
                            double precioConDescuento = precioOriginal - (precioOriginal * 0.18);
                            table.addCell(String.format("%.2f", precioConDescuento));
                            double igv = dc.getPrecioCompra() * 0.18;
                            table.addCell(String.format("%.2f", igv));

                            double subtotal = (precioConDescuento + igv) * dc.getCantidad();
                            table.addCell(String.format("%.2f", subtotal));
                            totalPagar += subtotal;
                        }

                        document.add(table);
                        document.add(new Paragraph(" "));
                        document.add(new Paragraph("Total a pagar: " + String.format("%.2f", totalPagar)));
                    } else {
                        document.add(new Paragraph("No se encontraron detalles para esta compra."));
                    }

                } catch (DocumentException e) {
                    e.printStackTrace();
                } finally {
                    // Asegurarse de cerrar el documento y OutputStream
                    if (document.isOpen()) {
                        document.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
                break;
            case "Factura":
                Factura(request, response);

                break;

            default:
                request.setAttribute("cont", listaProductos.size());
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("/vistas/formVendedor.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void agregarCarrito(HttpServletRequest request) {
        cantidad = 1;
        int pos = 0;
        int idpp = Integer.parseInt(request.getParameter("id"));
        if (listaProductos.size() > 0) {
            for (int i = 0; i < listaProductos.size(); i++) {
                if (listaProductos.get(i).getId_Producto() == idpp) {
                    pos = i;
                }
            }
            if (idpp == listaProductos.get(pos).getId_Producto()) {
                cantidad = listaProductos.get(pos).getCantidad() + cantidad;
                subtotal = listaProductos.get(pos).getPrecioCompra() * cantidad;
                listaProductos.get(pos).setCantidad(cantidad);
                listaProductos.get(pos).setSubTotal(subtotal);
            } else {
                car = new Carrito();
                p = pdao.listarId(idpp);
                car.setId_Producto(p.getId_Producto());
                car.setNombres(p.getNombres());
                car.setImagen(p.getFoto());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                subtotal = cantidad * p.getPrecio();
                car.setSubTotal(subtotal);
                listaProductos.add(car);
            }
        } else {
            car = new Carrito();
            p = pdao.listarId(idpp);
            car.setId_Producto(p.getId_Producto());
            car.setNombres(p.getNombres());
            car.setImagen(p.getFoto());
            car.setDescripcion(p.getDescripcion());
            car.setPrecioCompra(p.getPrecio());
            car.setCantidad(cantidad);
            subtotal = cantidad * p.getPrecio();
            car.setSubTotal(subtotal);
            listaProductos.add(car);
        }
    }

    private void realizarPago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");

        if (usuario != null) {
            int userId = usuario.getIDUSUARIO();
            System.out.println("Usuario autenticado: " + userId);

            // Aquí colocas la lógica para realizar el pago
            totalPagar = calcularTotalPagar(); // Función para calcular el total a pagar

            if (userId > 0 && totalPagar > 0) {
                // Lógica para procesar el pago
                cdao.Pagar(totalPagar);  // Ejemplo de método en tu DAO para realizar el pago

                // Redireccionar a una página después de realizar el pago
                request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);
            } else {
                System.out.println("No se puede realizar el pago. Usuario no identificado o total a pagar es cero.");
                request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);
            }
        } else {
            System.out.println("Usuario no autenticado.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void GenerarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");

        if (usuario != null && usuario.getIDUSUARIO() != 0 && listaProductos.size() > 0) {
            totalPagar = calcularTotalPagar(); // Calcular el total a pagar
            idpago = cdao.IdPago(); // Obtener el ID de pago
            if (totalPagar > 0.0) {
                Compra co = new Compra();
                co.setIDUSUARIO(usuario.getIDUSUARIO());
                co.setFecha(fechaSistem.FechaBD());
                co.setMonto(totalPagar);
                co.setIdPago(idpago);
                co.setEstado("Cancelado");

                // Guardar la compra en la base de datos
                cdao.guardarCompra(co);

                // Obtener el ID de la compra recién guardada
                idcompra = cdao.IdCompra();

                // Guardar los detalles de la compra
                for (int i = 0; i < listaProductos.size(); i++) {
                    DetalleCompra dc = new DetalleCompra();
                    dc.setIdcompra(idcompra);
                    dc.setId_Producto(listaProductos.get(i).getId_Producto());
                    dc.setCantidad(listaProductos.get(i).getCantidad());
                    dc.setPrecioCompra(listaProductos.get(i).getPrecioCompra());
                    cdao.guardarDetalleCompra(dc);
                }

                // Limpiar la lista de productos del carrito
                listaProductos = new ArrayList<>();

                // Obtener y mostrar las compras del cliente
                List compra = cdao.misCompras(usuario.getIDUSUARIO());
                request.setAttribute("myCompras", compra);
                request.getRequestDispatcher("vistas/compras.jsp").forward(request, response);
            } else {
                // Si el total a pagar es cero o menor, redirigir a la página de inicio
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
            }
        }
    }

    private double calcularTotalPagar() {
        totalPagar = 0.0;

        // Aquí debes implementar la lógica para calcular el total a pagar,
        // por ejemplo, sumando los montos de los productos en el carrito.
        // Esto depende de cómo tengas estructurada tu lista de productos en el carrito.
        for (Carrito item : listaProductos) {
            totalPagar += item.getSubTotal();
        }

        return totalPagar;
    }

    private void MisCompras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");

        if (usuario != null) {
            int userId = usuario.getIDUSUARIO();
            System.out.println("Usuario autenticado: " + userId);

            if (userId > 0) {
                List<Compra> compra = cdao.misCompras(userId);
                request.setAttribute("myCompras", compra); // Asegúrate de pasar la lista de compras al JSP

                // Redireccionar a la página de compras después de obtener las compras
                request.getRequestDispatcher("vistas/compras.jsp").forward(request, response);
            } else if (listaProductos.size() > 0) {
                System.out.println("Redirigiendo al carrito porque hay productos en el carrito.");
                request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);
            } else {
                System.out.println("No se puede mostrar las compras. Usuario no identificado o lista de productos vacía.");
                request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);
            }
        }
    }

    private void Factura(HttpServletRequest request, HttpServletResponse response) throws IOException {
        totalPagar = 0.0;
        int idcompras = Integer.parseInt(request.getParameter("idcompra"));
        List<DetalleCompra> detalle = cdao.Detalle(idcompras);

        // Configurar el documento PDF
        Document document = new Document();
        try {
            // Configurar el OutputStream para enviar el PDF como respuesta
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"detalle_compra_" + idcompras + ".pdf\"");
            OutputStream out = response.getOutputStream();

            // Crear el escritor para el documento PDF
            PdfWriter.getInstance(document, out);
            document.open();

            // Agregar encabezado
            try {
                String logoPath = getServletContext().getRealPath("../reportes/logohyp.jpg"); // Ajusta la ruta del logo
                Image logo = Image.getInstance(logoPath);
                logo.setAlignment(Element.ALIGN_CENTER);
                logo.scaleToFit(200, 100); // Ajusta el tamaño del logo según tus necesidades
                document.add(logo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            document.add(new Paragraph("HappyPet", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.BOLD)));
            document.add(new Paragraph("RUC: 12345678901")); // Ajusta el RUC
            document.add(new Paragraph("Dirección: Mercado ASPROPA puesto 260, San Martín de Porres ")); // Ajusta la dirección
            document.add(new Paragraph(" "));
            // Agregar información adicional en encabezado
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new int[]{70, 30});
            // Información de la factura
            PdfPTable invoiceTable = new PdfPTable(1);
            invoiceTable.addCell(new Phrase("RUC: 12345678901")); // Ajusta el RUC
            PdfPCell facturaCell = new PdfPCell(new Phrase("Factura Electrónica", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD)));
            facturaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            facturaCell.setBorder(Rectangle.NO_BORDER);
            invoiceTable.addCell(facturaCell);
            PdfPCell invoiceCell = new PdfPCell(invoiceTable);
            invoiceCell.setBorder(Rectangle.BOX);
            headerTable.addCell(invoiceCell);

            // Información del remitente y destinatario
            PdfPTable infoTable = new PdfPTable(1);
            infoTable.addCell(new Phrase("Remitente: HappyPet"));
            infoTable.addCell(new Phrase("Fecha: " + new java.util.Date())); // Ajusta la fecha
            infoTable.addCell(new Phrase("Tipo de Moneda: SOLES"));
            PdfPCell infoCell = new PdfPCell(infoTable);
            infoCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(infoCell);

            document.add(headerTable);
            document.add(new Paragraph(" ")); // Añadir espacio después del encabezado

            // Agregar contenido del documento
            document.add(new Paragraph("Detalles de Compra"));
            document.add(new Paragraph("ID Compra: C00" + idcompras));

            // Verificar si la lista de detalles no está vacía
            if (detalle != null && !detalle.isEmpty()) {
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Detalles de la compra:"));

                // Crear tabla para los detalles de la compra
                PdfPTable table = new PdfPTable(5); // 5 columnas: Producto, Cantidad, Precio Unitario, IGV 18%, Subtotal
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Agregar encabezados a la tabla
                table.addCell("Producto");
                table.addCell("Cantidad");
                table.addCell("Precio Unitario");
                table.addCell("IGV 18%");
                table.addCell("Subtotal");

                double totalPagar = 0.0;
                for (DetalleCompra dc : detalle) {
                    table.addCell(dc.getProducto().getNombres());
                    table.addCell(String.valueOf(dc.getCantidad()));
                    double precioOriginal = dc.getPrecioCompra();
                    double precioConDescuento = precioOriginal / 1.18; // Precio sin IGV
                    table.addCell(String.format("%.2f", precioConDescuento));
                    double igv = precioOriginal - precioConDescuento; // IGV calculado
                    table.addCell(String.format("%.2f", igv));

                    double subtotal = precioOriginal * dc.getCantidad(); // Precio con IGV
                    table.addCell(String.format("%.2f", subtotal));
                    totalPagar += subtotal;
                }

                document.add(table);
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Total a pagar: " + String.format("%.2f", totalPagar)));
            } else {
                document.add(new Paragraph("No se encontraron detalles para esta compra."));
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            // Asegurarse de cerrar el documento y OutputStream
            if (document.isOpen()) {
                document.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

}
