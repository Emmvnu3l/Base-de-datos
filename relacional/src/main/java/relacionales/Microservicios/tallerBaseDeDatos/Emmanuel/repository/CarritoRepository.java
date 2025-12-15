package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByUsuarioId(Long usuarioId);
}

