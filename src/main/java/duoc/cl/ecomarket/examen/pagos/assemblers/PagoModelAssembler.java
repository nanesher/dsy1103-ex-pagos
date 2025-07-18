package duoc.cl.ecomarket.examen.pagos.assemblers;

import duoc.cl.ecomarket.examen.pagos.controller.PagoController;
import duoc.cl.ecomarket.examen.pagos.model.Pago;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class PagoModelAssembler implements RepresentationModelAssembler<Pago, EntityModel<Pago>> {

    @Value("${productos-api-url}")
    private String productosApiUrl;

    @Value("${usuarios-api-url}")
    private String usuariosApiUrl;

    @Override
    public EntityModel<Pago> toModel(Pago pago) {

        return EntityModel.of(pago,
                linkTo(methodOn(PagoController.class).getById(pago.getPagoId())).withSelfRel(),
                linkTo(methodOn(PagoController.class).getAll()).withRel("Lista de todos los pagos(con metodo get)"),
                linkTo(methodOn(PagoController.class).delete(pago.getPagoId())).withRel("Borrar el pago (con metodo delete)"),
                linkTo(methodOn(PagoController.class).updatePago(pago.getPagoId(),null)).withRel("Actualizar el pago(con metodo put y con body)"),
                Link.of(productosApiUrl + pago.getIdProducto()).withRel("producto del pago"),
                Link.of(usuariosApiUrl + pago.getIdUsuario()).withRel("Usuario del pago"));
    }

}
