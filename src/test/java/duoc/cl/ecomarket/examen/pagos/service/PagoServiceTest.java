package duoc.cl.ecomarket.examen.pagos.service;

import duoc.cl.ecomarket.examen.pagos.model.Pago;
import duoc.cl.ecomarket.examen.pagos.model.PagoRequest;
import duoc.cl.ecomarket.examen.pagos.repository.PagoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class PagoServiceTest {
    @Autowired
    private PagoService pagoService;
    
    @MockitoBean
    private PagoRepository pagoRepository;
    
    
    private Pago pago;
    private PagoRequest pagoR;
    Integer id=1;

    @BeforeEach
    public void setUp() {
        pago = new Pago();
        pago.setPagoId(id);
        pago.setIdProducto(id);
        pago.setIdUsuario(id);
        pago.setMonto(10000);
        LocalDateTime fecha = LocalDateTime.now().minusDays(10);
        pago.setFechaPago(fecha);
        pago.setMetodoPago("Tarjeta de Debito");
        pago.setEstado("Aprobado");

        pagoR = new PagoRequest();
        pagoR.setMetodoPago("Tarjeta de Debito");
        pagoR.setIdProducto(id);
        pagoR.setIdUsuario(id);
    }


    @Test
    public void testFindAll() {
        when(pagoRepository.findAll()).thenReturn(List.of(pago));
        List<Pago> pagos = pagoService.findAll();
        Assertions.assertNotNull(pagos);
        assertEquals(1,pagos.size());

    }

    @Test
    public void testFindById() {
        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));
        when(pagoRepository.existsById(id)).thenReturn(true);
        Pago found = pagoService.findById(id);
        Assertions.assertNotNull(pagoService.findById(1));
        assertEquals(1,found.getPagoId());

    }

    @Test
    public void testDelete() {
        doNothing().when(pagoRepository).deleteById(id);
        when(pagoRepository.existsById(id)).thenReturn(Boolean.TRUE);
        pagoService.deleteById(id);
        verify(pagoRepository, times(1)).deleteById(id);
    }
/*
    @Test
    public void testSave() {

    }
    @Test
    public void testUpdate() {

    }

    @Test
    public void testUpdateFallido() {

    }


*/
}


