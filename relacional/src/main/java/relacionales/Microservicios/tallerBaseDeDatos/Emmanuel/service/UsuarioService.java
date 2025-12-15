package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service;

import java.util.List;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Usuario;

public interface UsuarioService {
    Usuario crear(Usuario usuario);
    Usuario obtenerPorId(Long id);
    List<Usuario> listar();
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
}

