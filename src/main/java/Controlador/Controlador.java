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
import java.io.File;
import java.io.IOException;

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
                VerDetalle(request, response);
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

    private void VerDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        usuario usuario = (usuario) sesion.getAttribute("vendedor");

        if (usuario != null) {
            totalPagar = 0.0;
            int idcompras = Integer.parseInt(request.getParameter("idcompra"));
            List<DetalleCompra> detalle = cdao.Detalle(idcompras);
            request.setAttribute("myDetalle", detalle);
            for (int i = 0; i < detalle.size(); i++) {
                totalPagar += detalle.get(i).getPrecioCompra() * detalle.get(i).getCantidad();
            }

            request.setAttribute("montoPagar", totalPagar);

            // Redireccionar a la página de detalles de compra después de obtener los detalles
            request.getRequestDispatcher("vistas/DetalleCompra.jsp").forward(request, response);
        }
    }

}
