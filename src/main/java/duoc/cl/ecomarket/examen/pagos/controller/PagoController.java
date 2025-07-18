package duoc.cl.ecomarket.examen.pagos.controller;


import duoc.cl.ecomarket.examen.pagos.assemblers.PagoModelAssembler;
import duoc.cl.ecomarket.examen.pagos.model.Pago;
import duoc.cl.ecomarket.examen.pagos.model.PagoRequest;
import duoc.cl.ecomarket.examen.pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pagos")
@Tag(name="Pagos",description = "Operaciones relacionadas con el registro de los pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;
    @Autowired
    private PagoModelAssembler assembler;
    
    
    @Operation(summary="Obtener todos los pagos",description = "Obtiene una lista de todos los pagos con sus atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas los pagos listados correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pago>> getAll() {
        List<EntityModel<Pago>> pagos = pagoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(pagos,
                linkTo(methodOn(PagoController.class).getAll()).withSelfRel(),
                linkTo(methodOn(PagoController.class).create(null)).withRel("Crear un pago(con metodo post)"));
    }

    @Operation(summary="Crear un pago",description = "Se crea un nuevo pago con los parametros enviados sin incluir el id asignadole una ID automaticamente" +
            " haciendo validaciones de sus datos para procesarlo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago creado con éxito",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody PagoRequest pago1) {
            Pago pago = pagoService.save(pago1);
            return ResponseEntity.ok(assembler.toModel(pago));
    }

    @Operation(summary="Obtener un pago",description = "Obtiene un pago con sus atributos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago listado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })

    @GetMapping (value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pago> getById(@Parameter(
            description = "ID del pago a obtener",
            name = "id",
            required = true,
            in = ParameterIn.PATH,
            example = "5"
    )@PathVariable Integer id) {
        Pago pago = pagoService.findById(id);
        return assembler.toModel(pago);
    }

    @Operation(summary="Eliminar un pago",description = "Se elimina un pago por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pago eliminado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> delete(@Parameter(
            description = "ID del pago a eliminar",
            name = "id",
            required = true,
            in = ParameterIn.PATH,
            example = "5"
    )@PathVariable Integer id) {
        pagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary="Actualizar un pago",description = "Se actualizan los datos de un pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago actualizado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pago> updatePago(@Parameter(
            description = "ID del pago a actualizar",
            name = "id",
            required = true,
            in = ParameterIn.PATH,
            example = "5"
    )@PathVariable Integer id, @RequestBody Pago pago){
        Pago pagoActualizada = pagoService.update(id, pago);
        return assembler.toModel(pagoActualizada);
    }

}





