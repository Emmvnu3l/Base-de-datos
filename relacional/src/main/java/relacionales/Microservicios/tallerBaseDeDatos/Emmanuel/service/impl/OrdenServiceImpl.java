package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Carrito;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Orden;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.OrdenEstado;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.OrdenItem;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Producto;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository.CarritoRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository.OrdenRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.OrdenService;

@Service
@Transactional
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final CarritoRepository carritoRepository;

    public OrdenServiceImpl(OrdenRepository ordenRepository, CarritoRepository carritoRepository) {
        this.ordenRepository = ordenRepository;
        this.carritoRepository = carritoRepository;
    }

    @Override
    public Orden crearDesdeCarrito(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
            .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
        if (carrito.getUsuario() == null) {
            throw new IllegalArgumentException("Carrito sin usuario");
        }
        Set<Producto> productos = carrito.getProductos();
        if (productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("Carrito sin productos");
        }

        Orden orden = Orden.builder()
            .usuario(carrito.getUsuario())
            .carrito(carrito)
            .creadaEn(LocalDateTime.now())
            .estado(OrdenEstado.CREADA)
            .total(BigDecimal.ZERO)
            .build();

        Set<OrdenItem> items = productos.stream()
            .map(p -> {
                BigDecimal unit = p.getPrecio() != null ? p.getPrecio() : BigDecimal.ZERO;
                BigDecimal subtotal = unit;
                return OrdenItem.builder()
                    .orden(orden)
                    .producto(p)
                    .cantidad(1)
                    .precioUnitario(unit)
                    .subtotal(subtotal)
                    .build();
            })
            .collect(Collectors.toSet());

        orden.setItems(items);
        BigDecimal total = items.stream()
            .map(OrdenItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        orden.setTotal(total);

        return ordenRepository.save(orden);
    }

    @Override
    @Transactional(readOnly = true)
    public Orden obtenerPorId(Long id) {
        return ordenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Orden> listar() {
        return ordenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Orden> listarPorUsuario(Long usuarioId) {
        return ordenRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Orden actualizarEstado(Long id, OrdenEstado estado) {
        Orden orden = obtenerPorId(id);
        orden.setEstado(estado);
        return ordenRepository.save(orden);
    }

    @Override
    public void eliminar(Long id) {
        if (!ordenRepository.existsById(id)) {
            throw new EntityNotFoundException("Orden no encontrada");
        }
        ordenRepository.deleteById(id);
    }
}

