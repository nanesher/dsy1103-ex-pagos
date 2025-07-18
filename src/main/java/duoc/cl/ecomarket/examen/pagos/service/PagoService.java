package duoc.cl.ecomarket.examen.pagos.service;


import duoc.cl.ecomarket.examen.pagos.model.Pago;
import duoc.cl.ecomarket.examen.pagos.model.PagoRequest;
import duoc.cl.ecomarket.examen.pagos.model.ProductoDTO;
import duoc.cl.ecomarket.examen.pagos.repository.PagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${productos-api-url}")
    private String productosApiUrl;

    public List<Pago> findAll(){
        List<Pago> pagos = pagoRepository.findAll();
        return pagos;
    }
    public Pago findById(Integer id){
        return pagoRepository.findById(id).get();
    }

    public Pago save(PagoRequest pagoInicio){
        Pago pago = new Pago();
        String url = productosApiUrl + pagoInicio.getIdProducto();
        ResponseEntity<EntityModel<ProductoDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        ProductoDTO productoDTO = response.getBody().getContent();
        pago.setIdProducto(pagoInicio.getIdProducto());
        assert productoDTO != null;
        pago.setMonto(productoDTO.getPrecio());
        pago.setIdUsuario(pagoInicio.getIdUsuario());
        if (pagoInicio.getMetodoPago().equals("Tarjeta de debito") || pagoInicio.getMetodoPago().equals("Tarjeta de credito") ||
        pagoInicio.getMetodoPago().equals("Transferencia")){
            pago.setEstado("Aprobado");
        }else{
            pago.setEstado("Rechazado");
        }
        pago.setMetodoPago(pagoInicio.getMetodoPago());
        return pagoRepository.save(pago);
    }

    public void deleteById(Integer id){
        pagoRepository.deleteById(id);
    }
    public Pago update(Integer id, Pago pago2){
        Pago pago1 = findById(id);
        pago1.setMetodoPago(pago2.getMetodoPago());
        pago1.setEstado(pago2.getEstado());
        pago1.setFechaPago(pago2.getFechaPago());
        pago1.setMonto(pago2.getMonto());
        return pagoRepository.save(pago1);
    }


}
