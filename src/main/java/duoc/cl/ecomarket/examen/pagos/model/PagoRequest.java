package duoc.cl.ecomarket.examen.pagos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequest {


    @Schema(description = "Identificador unico del producto del pago", example = "7")
    private Integer idProducto;
    @Schema(description = "Identificador unico del usuario del pago", example = "6")
    private Integer idUsuario;
    @Schema(description = "Metodo de pago valido para la venta", example = "Transferencia")
    private String metodoPago;


}
