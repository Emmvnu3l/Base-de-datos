package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.impl;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Producto;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.repository.ProductoRepository;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.ProductoService;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Producto crear(Producto producto) {
        return repository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return repository.findAll();
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Producto existente = obtenerPorId(id);
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        repository.deleteById(id);
    }
}

