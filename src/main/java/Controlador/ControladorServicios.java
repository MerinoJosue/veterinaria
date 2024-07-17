package Controlador;

import Modelo.Servicios;
import Modelo.ServiciosDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorServicios", urlPatterns = {"/ControladorServicios"})
public class ControladorServicios extends HttpServlet {

    Servicios sv = new Servicios();
    ServiciosDAO sdao = new ServiciosDAO();
    int sde;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Servicios")) {
            switch (accion) {

                case "Listar":
                    List<Servicios> lista = sdao.listar();
                    System.out.println("Número de servicios listados en el servlet: " + lista.size());
                    request.setAttribute("servicios", lista);
                    request.getRequestDispatcher("Servicios.jsp").forward(request, response);
                    break;

                case "Agregar":
                    String Nombre = request.getParameter("txtNombre");
                    String Precio = request.getParameter("txtPrecio");
                    String Descripcion = request.getParameter("txtDescripcion");
                    sv.setNombre(Nombre);
                    sv.setPrecio(Precio);
                    sv.setDescripcion(Descripcion);
                    sdao.agregar(sv);
                    request.getRequestDispatcher("ControladorServicios?menu=Servicios&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    sde = Integer.parseInt(request.getParameter("id"));
                    Servicios s = sdao.listarId(sde);
                    request.setAttribute("servicio", s);
                    request.getRequestDispatcher("ControladorServicios?menu=Servicios&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String Nombre1 = request.getParameter("txtNombre");
                    String Precio1 = request.getParameter("txtPrecio");
                    String Descripcion1 = request.getParameter("txtDescripcion");
                    Servicios svActualizado = new Servicios(sde, Nombre1, Precio1, Descripcion1);
                    sdao.actualizar(svActualizado);
                    request.getRequestDispatcher("ControladorServicios?menu=Servicios&accion=Listar").forward(request, response);
                    break;

                case "Delete":
                    sde = Integer.parseInt(request.getParameter("id"));
                    sdao.delete(sde);
                    request.getRequestDispatcher("ControladorServicios?menu=Servicios&accion=Listar").forward(request, response);
                    break;

                default:
                    request.getRequestDispatcher("ControladorServicios?menu=Servicios&accion=Listar").forward(request, response);
                    break;
            }
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
}
}
