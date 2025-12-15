package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.impl;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Usuario;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository.UsuarioRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario crear(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario existente = obtenerPorId(id);
        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());
        existente.setPassword(usuario.getPassword());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        repository.deleteById(id);
    }
}

