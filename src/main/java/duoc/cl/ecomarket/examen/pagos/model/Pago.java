package duoc.cl.ecomarket.examen.pagos.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pago")
@Entity
public class Pago {

    @Schema(description = "Identificador unico del pago", example = "7")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="pago_id")
    private Integer pagoId;

    @Schema(description = "Fecha en la que fue procesado el pago")
    @Column(name="fecha_pago", nullable=false)
    private LocalDateTime fechaPago;

    @Schema(description = "Estado del pago", example = "Aceptado")
    @Column(name="estado",nullable=false)
    private String estado;

    @Schema(description ="Monto total del pago", example="10000")
    @Column(name="monto",nullable=false)
    private Integer monto;

    @Schema(description = "Metodo de pago de la venta", example = "Tarjeta de debito")
    @Column(name="metodo_pago",nullable = false)
    private String metodoPago;

    @Schema(description = "Identificador unico del producto del pago", example="3")
    @Column(name="id_producto",nullable=false)
    private Integer idProducto;

    @Schema(description = "Identificador unico del usuario del pago", example="7")
    @Column(name="id_usuario",nullable=false)
    private Integer idUsuario;

    @PrePersist
    protected void onCreate() {
        this.fechaPago = LocalDateTime.now();
    }

}
