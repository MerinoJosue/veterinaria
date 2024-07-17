/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Citas;
import Modelo.CitasDao;
import Modelo.Mascota;
import Modelo.MascotaDAO;
import Modelo.Servicios;
import Modelo.ServiciosDAO;
import Modelo.Veterinario;
import Modelo.VeterinarioDAO;
import Modelo.usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "svrCitas", urlPatterns = {"/svrCitas"})
public class svrCitas extends HttpServlet {
    Citas c = new Citas();
    CitasDao cDao = new CitasDao();
    int cde;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "Listar":
                        listarCitas(request, response);
                        break;
                    case "agregar":
                        agregarCita(request, response);
                        break;
                    case "Editar":
                        editarCita(request, response);
                        break;
                    case "Actualizar":
                        actualizarCita(request, response);
                        break;
                    case "Delete":
                        deleteCita(request, response);
                        break;
                    default:
                        response.sendRedirect("RegistrarCitas.jsp");
                }
            } else {
                response.sendRedirect("RegistrarCitas.jsp");
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

    private void listarCitas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        Object vendedor = sesion.getAttribute("vendedor");
        usuario usuario;
        if (vendedor != null) {
            usuario = (usuario) sesion.getAttribute("vendedor");
        } else {
           usuario = (usuario) sesion.getAttribute("usuario");
        }
                
        if (usuario != null) {
            
            //Llenando el combobox Mascota
            MascotaDAO mdao = new MascotaDAO();
            List<Mascota> lstMascota = mdao.Listar();
            request.setAttribute("mascotas", lstMascota);
            
            //Llenando el combobox Veterinario
            VeterinarioDAO vdao = new VeterinarioDAO();
            List<Veterinario> lstVet = vdao.listar();
            request.setAttribute("veterinarios", lstVet);
            
            //Llenando el combobox Servicios
            ServiciosDAO sdao = new ServiciosDAO();
            List<Servicios> lstServ = sdao.listar();
            request.setAttribute("servicios", lstServ);
            
            List<Citas> lista = cDao.Listar();
            System.out.println("Número de Citas listadas en el servlet: " + lista.size());
            request.setAttribute("citas", lista);
            request.getRequestDispatcher("RegistrarCitas.jsp").forward(request, response);
        } else {
            System.out.println("Usuario no autenticado en svrCitas");
            request.setAttribute("msje", "Usuario no autenticado");
            request.getRequestDispatcher("identificar.jsp").forward(request, response);
        }
    }

    private void agregarCita(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        Object vendedor = sesion.getAttribute("vendedor");
        usuario usuario;
        if (vendedor != null) {
            usuario = (usuario) sesion.getAttribute("vendedor");
        } else {
           usuario = (usuario) sesion.getAttribute("usuario");
        }
        if (usuario != null) {
            int userId = usuario.getIDUSUARIO();
            System.out.println("Usuario autenticado: " + userId);

            Citas cita = new Citas();
            cita.setMascota(request.getParameter("mascota"));
            cita.setVeterinario(request.getParameter("veterinario"));
            cita.setServicio(request.getParameter("servicio"));
            cita.setFecha(request.getParameter("fecha"));
            cita.setHora(request.getParameter("hora"));
                      
            System.out.println("Datos de la mascota: " + cita.getMascota() + ", " + cita.getVeterinario() + ", " + cita.getServicio() + ", " + cita.getFecha()+ ", " + cita.getHora());

            int result = cDao.agregar(cita);
           

            if (result > 0) {
                request.setAttribute("msje", "Cita añadida exitosamente");
            } else {
                request.setAttribute("msje", "Fallo al añadir la Cita");
            }
        } else {
            System.out.println("Usuario no autenticado en svrCitas");
            request.setAttribute("msje", "Usuario no autenticado");
        }
        listarCitas(request, response);

    }

    private void editarCita(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        Object vendedor = sesion.getAttribute("vendedor");
        usuario usuario;
        if (vendedor != null) {
            usuario = (usuario) sesion.getAttribute("vendedor");
        } else {
           usuario = (usuario) sesion.getAttribute("usuario");
        }
        if (usuario != null) {
            int userId = Integer.parseInt(request.getParameter("id"));
            Citas cita = cDao.buscarPorId(userId);
            if (cita != null) {
                
                cde = Integer.parseInt(request.getParameter("id"));
                
                //Llenando el combobox Mascota
                MascotaDAO mdao = new MascotaDAO();
                List<Mascota> lstMascota = mdao.Listar();
                request.setAttribute("mascotas", lstMascota);

                //Llenando el combobox Veterinario
                VeterinarioDAO vdao = new VeterinarioDAO();
                List<Veterinario> lstVet = vdao.listar();
                request.setAttribute("veterinarios", lstVet);

                //Llenando el combobox Servicios
                ServiciosDAO sdao = new ServiciosDAO();
                List<Servicios> lstServ = sdao.listar();
                request.setAttribute("servicios", lstServ);
                                
                List<Citas> lista = cDao.Listar();
                request.setAttribute("citas", lista);
                
                request.setAttribute("cita", cita);
                request.getRequestDispatcher("RegistrarCitas.jsp").forward(request, response);
            } else {
                request.setAttribute("msje", "No se encontró la cita");
                listarCitas(request, response);
            }
        } else {
            request.setAttribute("msje", "Usuario no autenticado");
        }
    }

    private void actualizarCita(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        Object vendedor = sesion.getAttribute("vendedor");
        usuario usuario;
        if (vendedor != null) {
            usuario = (usuario) sesion.getAttribute("vendedor");
        } else {
           usuario = (usuario) sesion.getAttribute("usuario");
        }
        if (usuario != null) {
            int userId = usuario.getIDUSUARIO();
            System.out.println("Usuario autenticado: " + userId);

            Citas cita = new Citas();
            cita.setId_cita(cde);
            cita.setMascota(request.getParameter("mascota"));
            cita.setVeterinario(request.getParameter("veterinario"));
            cita.setServicio(request.getParameter("servicio"));
            cita.setFecha(request.getParameter("fecha"));
            cita.setHora(request.getParameter("hora"));

            int result = cDao.actualizar(cita);
            if (result > 0) {
                request.setAttribute("msje", "Cita actualizada exitosamente");
            } else {
                request.setAttribute("msje", "Fallo al actualizar la cita");
            }
            listarCitas(request, response);
        } else {
            request.setAttribute("msje", "Usuario no autenticado");
        }
    }

    private void deleteCita(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        Object vendedor = sesion.getAttribute("vendedor");
        usuario usuario;
        if (vendedor != null) {
            usuario = (usuario) sesion.getAttribute("vendedor");
        } else {
           usuario = (usuario) sesion.getAttribute("usuario");
        }
        if (usuario != null) {
            int Id_Cita = Integer.parseInt(request.getParameter("id"));
            int result = cDao.eliminar(Id_Cita);
            if (result > 0) {
                request.setAttribute("msje", "Cita eliminada exitosamente");
            } else {
                request.setAttribute("msje", "Fallo al eliminar la cita");
            }
            listarCitas(request, response);
        } else {
            request.setAttribute("msje", "Usuario no autenticado");
        }
    }
}
