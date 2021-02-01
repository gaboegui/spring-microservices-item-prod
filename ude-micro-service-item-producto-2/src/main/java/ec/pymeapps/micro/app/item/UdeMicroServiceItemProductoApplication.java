package ec.pymeapps.micro.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * utilizamos Client ya que solo hay un cleinte si consumieramos mas 
 * servicios se utilizaria @RibbonClients 
 *
 *  Al aumentar la dependencia en POM.xml de
 *  spring-cloud-starter-netflix-eureka-client
 *  se registra automaticamente el servicio, pero no esta de mas poner 
 *  la anotacion  @EnableEurekaClient de forma explicita
 *  
 *  Asi mismo al añadir eureka-client, ya no es necesaria
 *  la anotacion @RibbonClient(name = "pymeapps.servicio.producto")
 *  
 * para habilitar hystrix se añade @EnableCircuitBreaker  
 * 
 * @EnableAutoConfiguration es necesario ya que la Clase Producto en este 
 * servicio es un simple POJO sin conexion a la BD 
 * 
 * @author Gabriel Eguiguren
 *
 */

//@RibbonClient(name = "pymeapps.servicio.producto")
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class UdeMicroServiceItemProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdeMicroServiceItemProductoApplication.class, args);
	}

}
