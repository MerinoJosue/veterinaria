/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import com.google.gson.Gson;
import Modelo.Productos;
import Modelo.ProductosDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author USER
 */
@MultipartConfig
@WebServlet(name = "ControladorProductos", urlPatterns = {"/ControladorProductos"})
public class ControladorProductos extends HttpServlet {

    Productos p = new Productos();
    ProductosDAO pdao = new ProductosDAO();
    int pde;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Productos")) {
            switch (accion) {
                case "Listar":
                    List<Productos> lista = pdao.listar();
                    System.out.println("Número de productos listados en el servlet: " + lista.size());
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("Productos.jsp").forward(request, response);
                    break;

                // In your case "Agregar"
                case "Agregar":
                    // Obtener parámetros del formulario
                    String Nombre = request.getParameter("txtNombre");
                    String Descripcion = request.getParameter("txtDescripcion");
                    double Precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    int Stock = Integer.parseInt(request.getParameter("txtStock"));
                    Part part = request.getPart("fileFoto");

                    try (InputStream inputStream = part.getInputStream()) {
                        // Asignar los valores obtenidos al objeto producto
                        Productos p = new Productos();
                        p.setNombres(Nombre);
                        p.setDescripcion(Descripcion);
                        p.setPrecio(Precio);
                        p.setStock(Stock);
                        p.setFoto(inputStream);

                        // Llamar al método para agregar el nuevo producto a la base de datos
                        int result = pdao.agregar(p);
                        if (result > 0) {
                            System.out.println("Producto agregado exitosamente");
                        } else {
                            System.out.println("Error al agregar el producto");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    request.getRequestDispatcher("ControladorProductos?menu=Productos&accion=Listar").forward(request, response);
                    break;

                case "Editar":

                    pde = Integer.parseInt(request.getParameter("id"));
                    Productos p = pdao.listarId(pde);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("ControladorProductos?menu=Productos&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String Nombre1 = request.getParameter("txtNombre");
                    String Descripcion1 = request.getParameter("txtDescripcion");
                    double Precio1 = Double.parseDouble(request.getParameter("txtPrecio"));
                    int Stock1 = Integer.parseInt(request.getParameter("txtStock"));
                    Part Part1 = request.getPart("fileFoto");
                    Productos pr = new Productos(pde, Nombre1, Descripcion1, Precio1, Stock1, (InputStream) Part1);
                    pdao.actualizar(pr);
                    request.getRequestDispatcher("ControladorProductos?menu=Productos&accion=Listar").forward(request, response);
                    break;

                case "Delete":
                    pde = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(pde);
                    request.getRequestDispatcher("ControladorProductos?menu=Productos&accion=Listar").forward(request, response);
                    break;

                default:
                    request.getRequestDispatcher("ControladorProductos?menu=Productos&accion=Listar").forward(request, response);
            }
            System.out.println("");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JRException ex) {
            Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JRException ex) {
            Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
