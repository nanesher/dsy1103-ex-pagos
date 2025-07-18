package duoc.cl.ecomarket.examen.pagos.repository;


import duoc.cl.ecomarket.examen.pagos.model.Pago;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {
    @Autowired
    private PagoRepository pagoRepository;
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            Pago pago = new Pago();
            pago.setIdProducto(faker.number().numberBetween(1,15));
            pago.setIdUsuario(faker.number().numberBetween(1,15));
            pago.setMonto(1 + random.nextInt(10000));
            int dias = random.nextInt(30); // hasta 30 días atrás
            LocalDateTime fecha = LocalDateTime.now().minusDays(dias);
            pago.setFechaPago(fecha);
            String[] metodos = {"Tarjeta de Debito", "Tarjeta de Credito", "Transferencia", "Cheque", "Efectivo", "Criptomoneda"};
            String metodo = metodos[random.nextInt(metodos.length)];
            pago.setMetodoPago(metodo);
            if (metodo.equals("Tarjeta de Debito") || metodo.equals("Tarjeta de Credito") || metodo.equals("Transferencia")) {
                pago.setEstado("Aprobado");
            } else {
                pago.setEstado("Rechazado");
            }
            pagoRepository.save(pago);
        }
    }
}
