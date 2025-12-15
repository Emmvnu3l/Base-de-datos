package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
}

