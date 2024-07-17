/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import com.google.gson.Gson;
import Modelo.Veterinario;
import Modelo.VeterinarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "ControladorVeterinario", urlPatterns = {"/ControladorVeterinario"})
public class ControladorVeterinario extends HttpServlet {

    Veterinario ve = new Veterinario();
    VeterinarioDAO vdao = new VeterinarioDAO();
    int vde;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Veterinario")) {
            switch (accion) {

                case "Listar":
                    List<Veterinario> lista = vdao.listar();
                    System.out.println("Número de veterinarios listados en el servlet: " + lista.size());
                    request.setAttribute("veterinarios", lista);
                    request.getRequestDispatcher("Veterinarios.jsp").forward(request, response);
                    break;

                case "Agregar":
                    String Dni = request.getParameter("txtDni");
                    String Nom = request.getParameter("txtNom");
                    String Tel = request.getParameter("txtTel");
                    String Estado = request.getParameter("txtEstado");
                    ve.setDni(Dni);
                    ve.setNom(Nom);
                    ve.setTel(Tel);
                    ve.setEstado(Estado);
                    vdao.agregar(ve);
                    request.getRequestDispatcher("ControladorVeterinario?menu=Veterinario&accion=Listar").forward(request, response);
                    break;

                case "Editar":

                    vde = Integer.parseInt(request.getParameter("id"));
                    Veterinario v = vdao.listarId(vde);
                    request.setAttribute("veterinario", v);
                    request.getRequestDispatcher("ControladorVeterinario?menu=Veterinario&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String Dni1 = request.getParameter("txtDni");
                    String Nom1 = request.getParameter("txtNom");
                    String Tel1 = request.getParameter("txtTel");
                    String Estado1 = request.getParameter("txtEstado");
                    Veterinario ve = new Veterinario(vde, Dni1, Nom1, Tel1, Estado1);
                    vdao.actualizar(ve);
                    request.getRequestDispatcher("ControladorVeterinario?menu=Veterinario&accion=Listar").forward(request, response);
                    break;
                     case "GenerarPDF":
                    // Obtener la lista de productos
                    List<Veterinario> listaVeterinario = vdao.listar();
                    try {
                        response.setContentType("application/pdf");
                        response.setHeader("Content-Disposition", "attachment; filename=Veterinario.pdf");
                        PrintWriter out = response.getWriter();

                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Delete":
                    vde = Integer.parseInt(request.getParameter("id"));
                    vdao.delete(vde);
                    request.getRequestDispatcher("ControladorVeterinario?menu=Veterinario&accion=Listar").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("ControladorVeterinario?menu=Veterinario&accion=Listar").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
