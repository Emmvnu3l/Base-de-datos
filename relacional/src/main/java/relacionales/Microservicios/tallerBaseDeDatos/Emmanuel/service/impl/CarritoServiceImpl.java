package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Carrito;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Producto;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Usuario;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository.CarritoRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository.ProductoRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository.UsuarioRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.CarritoService;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public CarritoServiceImpl(
        CarritoRepository carritoRepository,
        UsuarioRepository usuarioRepository,
        ProductoRepository productoRepository
    ) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Carrito crear(Carrito carrito) {
        Usuario usuario = resolveUsuario(carrito.getUsuario());
        Set<Producto> productos = resolveProductos(carrito.getProductos());
        carrito.setUsuario(usuario);
        carrito.setProductos(productos);
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional(readOnly = true)
    public Carrito obtenerPorId(Long id) {
        return carritoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carrito> listar() {
        return carritoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carrito> listarPorUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Carrito actualizar(Long id, Carrito carrito) {
        Carrito existente = obtenerPorId(id);
        if (carrito.getUsuario() != null) {
            existente.setUsuario(resolveUsuario(carrito.getUsuario()));
        }
        if (carrito.getProductos() != null) {
            existente.setProductos(resolveProductos(carrito.getProductos()));
        }
        return carritoRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!carritoRepository.existsById(id)) {
            throw new EntityNotFoundException("Carrito no encontrado");
        }
        carritoRepository.deleteById(id);
    }

    private Usuario resolveUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuario requerido para el carrito");
        }
        return usuarioRepository.findById(usuario.getId())
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    private Set<Producto> resolveProductos(Set<Producto> productos) {
        if (productos == null || productos.isEmpty()) {
            return Set.of();
        }
        return productos.stream()
            .map(p -> {
                if (p.getId() == null) {
                    throw new IllegalArgumentException("Producto sin id en carrito");
                }
                return productoRepository.findById(p.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
            })
            .collect(Collectors.toSet());
    }
}

