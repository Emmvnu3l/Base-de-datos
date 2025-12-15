package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByUsuarioId(Long usuarioId);
}

