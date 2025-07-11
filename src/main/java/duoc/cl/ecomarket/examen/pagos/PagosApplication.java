package duoc.cl.ecomarket.examen.pagos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagosApplication.class, args);
	}
	@Value("${productos-api-url}")
	private String productosApiUrl;
	@Value("${envios-api-url}")
	private String enviosApiUrl;
	@Value("${usuarios-api-url}")
	private String usuariosApiUrl;
	@Value("${ventas-api-url}")
	private String ventasApiUrl;

}
