package ec.pymeapps.micro.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Clase para registrar clientes que van a cosumir recursos de otros servicios REST
 * 
 * @author Gabriel Eguiguren
 *
 */
@Configuration
public class AppConfig {
	
	// asi se configura Balnaceo de Carga si no vamos a usar Feign 
	@LoadBalanced
	@Bean(name = "clienteRest")
	public RestTemplate registrarRestTemplate() {
		
		return new RestTemplate();
	}

}
