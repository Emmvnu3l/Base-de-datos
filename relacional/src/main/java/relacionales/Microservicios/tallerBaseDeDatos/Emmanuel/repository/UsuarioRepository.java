package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}

