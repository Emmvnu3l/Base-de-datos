package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service;

import java.util.List;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Carrito;

public interface CarritoService {
    Carrito crear(Carrito carrito);
    Carrito obtenerPorId(Long id);
    List<Carrito> listar();
    List<Carrito> listarPorUsuario(Long usuarioId);
    Carrito actualizar(Long id, Carrito carrito);
    void eliminar(Long id);
}

