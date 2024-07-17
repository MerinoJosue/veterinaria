
package Modelo;
import Modelo.Compra;
import Modelo.DetalleCompra;
import Modelo.ComprasDAO;

import java.util.ArrayList;
import java.util.List;

public class prueba {

    public static void main(String[] args) {
        ComprasDAO dao = new ComprasDAO();

        // Simular datos de prueba
        int idUsuario = 1;  // Reemplazar con el ID de usuario correcto
        double totalPagar = 100.0;  // Reemplazar con el total a pagar

        // Obtener el ID del pago (simulación)
        int idPago = dao.IdPago();

        // Crear una compra
        Compra compra = new Compra();
        compra.setIDUSUARIO(idUsuario);
        compra.setFecha("2024-07-16");  // Reemplazar con la fecha actual o adecuada
        compra.setMonto(totalPagar);
        compra.setIdPago(idPago);
        compra.setEstado("Cancelado - En Proceso de Envio");

        // Guardar la compra
        int resultadoCompra = dao.guardarCompra(compra);

        // Verificar si la compra se guardó correctamente
        if (resultadoCompra > 0) {
            System.out.println("Compra guardada correctamente.");
        } else {
            System.out.println("Error al guardar la compra.");
            return;  // Salir del método si hay un error
        }

        // Obtener el ID de la compra (simulación)
        int idCompra = dao.IdCompra();

        // Simular lista de productos comprados
        List<DetalleCompra> listaProductos = new ArrayList<>();
        DetalleCompra detalle1 = new DetalleCompra();
        detalle1.setIdcompra(idCompra);
        detalle1.setId_Producto(4);  // Reemplazar con el ID del producto correcto
        detalle1.setCantidad(2);    // Reemplazar con la cantidad comprada
        detalle1.setPrecioCompra(50.0);  // Reemplazar con el precio de compra por unidad
        listaProductos.add(detalle1);

        DetalleCompra detalle2 = new DetalleCompra();
        detalle2.setIdcompra(idCompra);
        detalle2.setId_Producto(4);  // Reemplazar con el ID del producto correcto
        detalle2.setCantidad(1);    // Reemplazar con la cantidad comprada
        detalle2.setPrecioCompra(30.0);  // Reemplazar con el precio de compra por unidad
        listaProductos.add(detalle2);

        // Guardar los detalles de compra
        for (DetalleCompra detalle : listaProductos) {
            int resultadoDetalle = dao.guardarDetalleCompra(detalle);
            if (resultadoDetalle > 0) {
                System.out.println("Detalle de compra guardado correctamente.");
            } else {
                System.out.println("Error al guardar el detalle de compra.");
                return;  // Salir del método si hay un error
            }
        }

        // Limpiar la lista de productos después de guardar
        listaProductos.clear();

        // Obtener y mostrar las compras del usuario (simulación)
        List compraUsuario = dao.misCompras(idUsuario);
        System.out.println("Compras del usuario:");
        for (Object compraObj : compraUsuario) {
            System.out.println(compraObj);
        }
    }
}
