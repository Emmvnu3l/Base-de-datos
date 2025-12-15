package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Orden;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.OrdenEstado;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.OrdenService;

@RestController
@RequestMapping("/api/ordenes")
@Validated
public class OrdenController {

    private final OrdenService service;

    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @PostMapping("/desde-carrito/{carritoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Orden crearDesdeCarrito(@PathVariable Long carritoId) {
        return service.crearDesdeCarrito(carritoId);
    }

    @GetMapping("/{id}")
    public Orden obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<Orden> listar() {
        return service.listar();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Orden> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @PutMapping("/{id}/estado")
    public Orden actualizarEstado(@PathVariable Long id, @RequestParam OrdenEstado estado) {
        return service.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

