package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity.Carrito;
import relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.service.CarritoService;

@RestController
@RequestMapping("/api/carritos")
@Validated
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito crear(@Valid @RequestBody Carrito carrito) {
        return service.crear(carrito);
    }

    @GetMapping("/{id}")
    public Carrito obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<Carrito> listar() {
        return service.listar();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Carrito> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @PutMapping("/{id}")
    public Carrito actualizar(@PathVariable Long id, @Valid @RequestBody Carrito carrito) {
        return service.actualizar(id, carrito);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

