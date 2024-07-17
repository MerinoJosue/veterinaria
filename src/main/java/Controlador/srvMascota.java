package Controlador;

import Modelo.Mascota;
import Modelo.MascotaDAO;
import Modelo.usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "srvMascota", urlPatterns = {"/srvMascota"})
public class srvMascota extends HttpServlet {

    Mascota m = new Mascota();
    MascotaDAO mdao = new MascotaDAO();
    int mde;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "Listar":
                        listarMascotas(request, response);
                        break;
                    case "agregar":
                        agregarMascota(request, response);
                        break;
                    case "Editar":
                        editarMascota(request, response);
                        break;
                    case "Actualizar":
                        actualizarMascota(request, response);
                        break;
                    case "Delete":
                        deleteMascota(request, response);
                        break;
                    default:
                        response.sendRedirect("RegistrarMascota.jsp");
                }
            } else {
                response.sendRedirect("RegistrarMascota.jsp");
            }
        } catch (Exception e) {
            request.setAttribute("msje", "Error: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
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

    private void listarMascotas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");
        if (usuario != null) {
            List<Mascota> lista = mdao.Listar();
            System.out.println("Número de mascotas listadas en el servlet: " + lista.size());
            request.setAttribute("mascotas", lista);
            request.getRequestDispatcher("RegistrarMascota.jsp").forward(request, response);
        } else {
            System.out.println("Usuario no autenticado en srvMascota");
            request.setAttribute("msje", "Usuario no autenticado");
            request.getRequestDispatcher("identificar.jsp").forward(request, response);
        }
    }

    private void agregarMascota(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");
        if (usuario != null) {
            int userId = usuario.getIDUSUARIO();
            System.out.println("Usuario autenticado: " + userId);

            Mascota mascota = new Mascota();
            mascota.setNombre(request.getParameter("nombre"));
            mascota.setRaza(request.getParameter("raza"));
            mascota.setSexo(request.getParameter("sexo"));
            mascota.setEspecie(request.getParameter("especie"));

            System.out.println("Datos de la mascota: " + mascota.getNombre() + ", " + mascota.getRaza() + ", " + mascota.getSexo() + ", " + mascota.getEspecie());

            MascotaDAO mascotaDAO = new MascotaDAO();
            int result = mascotaDAO.agregar(mascota, userId);

            if (result > 0) {
                request.setAttribute("msje", "Mascota añadida exitosamente");
            } else {
                request.setAttribute("msje", "Fallo al añadir la mascota");
            }
        } else {
            System.out.println("Usuario no autenticado en srvMascota");
            request.setAttribute("msje", "Usuario no autenticado");
        }
        listarMascotas(request, response);

    }

    private void editarMascota(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");
        if (usuario != null) {
            int userId = Integer.parseInt(request.getParameter("id"));
            Mascota mascota = mdao.buscarPorId(userId);
            if (mascota != null) {
                request.setAttribute("mascota", mascota);
                request.getRequestDispatcher("RegistrarMascota.jsp").forward(request, response);
            } else {
                request.setAttribute("msje", "No se encontró la mascota");
                listarMascotas(request, response);
            }
        } else {
            request.setAttribute("msje", "Usuario no autenticado");
        }
    }

    private void actualizarMascota(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");
        if (usuario != null) {
            int userId = usuario.getIDUSUARIO();
            System.out.println("Usuario autenticado: " + userId);

            Mascota mascota = new Mascota();
            mascota.setNombre(request.getParameter("nombre"));
            mascota.setRaza(request.getParameter("raza"));
            mascota.setSexo(request.getParameter("sexo"));
            mascota.setEspecie(request.getParameter("especie"));

            int result = mdao.actualizar(mascota,userId);
            if (result > 0) {
                request.setAttribute("msje", "Mascota actualizada exitosamente");
            } else {
                request.setAttribute("msje", "Fallo al actualizar la mascota");
            }
            listarMascotas(request, response);
        } else {
            request.setAttribute("msje", "Usuario no autenticado");
        }
    }

    private void deleteMascota(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");
        if (usuario != null) {
            int userId = Integer.parseInt(request.getParameter("id"));
            int result = mdao.eliminar(userId);
            if (result > 0) {
                request.setAttribute("msje", "Mascota eliminada exitosamente");
            } else {
                request.setAttribute("msje", "Fallo al eliminar la mascota");
            }
            listarMascotas(request, response);
        } else {
            request.setAttribute("msje", "Usuario no autenticado");
        }
    }
}
