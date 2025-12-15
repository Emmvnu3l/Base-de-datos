package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service;

import java.util.List;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Producto;

public interface ProductoService {
    Producto crear(Producto producto);
    Producto obtenerPorId(Long id);
    List<Producto> listar();
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}

