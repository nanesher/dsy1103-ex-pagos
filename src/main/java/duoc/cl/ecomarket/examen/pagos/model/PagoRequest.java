package duoc.cl.ecomarket.examen.pagos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequest {


    private Integer idProducto;
    private Integer idUsuario;
    private String metodoPago;


}
