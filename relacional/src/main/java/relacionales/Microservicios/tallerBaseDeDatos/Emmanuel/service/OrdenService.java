package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service;

import java.util.List;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Orden;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.OrdenEstado;

public interface OrdenService {
    Orden crearDesdeCarrito(Long carritoId);
    Orden obtenerPorId(Long id);
    List<Orden> listar();
    List<Orden> listarPorUsuario(Long usuarioId);
    Orden actualizarEstado(Long id, OrdenEstado estado);
    void eliminar(Long id);
}

