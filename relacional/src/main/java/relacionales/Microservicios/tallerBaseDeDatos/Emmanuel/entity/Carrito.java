package relacionales.Microservicios.tallerBaseDeDatos.Emmanuel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CARRITOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "CARRITO_PRODUCTOS",
        joinColumns = @JoinColumn(name = "CARRITO_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCTO_ID")
    )
    @Builder.Default
    private Set<Producto> productos = new HashSet<>();
}

