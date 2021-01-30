package ec.pymeapps.micro.app.item.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ec.pymeapps.micro.app.item.models.Item;
import ec.pymeapps.micro.app.item.models.Producto;
import ec.pymeapps.micro.app.item.models.service.ItemService;



/**
 * @RefreshScope forza a que se consulten de nuevo los datos del
 * servidor de configuracion, en conjuto con el Spring Boot Actuator
 * que se configura en bootstrap.properties: 
 *     	management.endpoints.web.exposure.include=*
 * 
 * se ejecuta con una llamada POST al URL:
 * 		localhost:8005/actuator/refresh
 * 
 * 
 * @author Gabriel E.
 *
 */
@RefreshScope
@RestController
public class ItemController {
	
	//@Qualifier("serviceRestTemplate")
	@Autowired
	@Qualifier("servicio-Productos-Feign")
	private ItemService service;
	
	
	// lee la propiedad obtenida por el servidor de configuracion
	// definido en bootstrap.properties
	@Value("${cloud-configuracion.texto}")
	private String mensaje;
	
	
	@Autowired
	Environment env;
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
		
		Map<String,String> json = new HashMap<String,String>();
		json.put("texto", mensaje);
		json.put("puerto", puerto);
		
		// pregunto si estoy en ambiente desarrollo
		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor-mantenimiento", env.getProperty("cloud-configuracion.autor.mantenimiento"));
			json.put("autor-email", env.getProperty("cloud-configuracion.autor.email"));
			
		}
				
		return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
		
	}
	
	@GetMapping("/listar")
	public List<Item> listar(){
		return service.findAll();
	}
	
	
	/***
	 * Con el @HystrixCommand trazo una ruta alterna
	 * para evitar errores en cascada
	 * 
	 * @param productoId
	 * @param cantidad
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item ver(@PathVariable(name = "id") Long productoId, @PathVariable Integer cantidad){
		
		return service.findById(productoId, cantidad);
	}

	public Item metodoAlternativo(Long productoId, Integer cantidad){
		
		Producto prod = new Producto();
		prod.setId(productoId);
		prod.setNombre("Default Fault Name");
		prod.setPrecio(new BigDecimal(0));
		Item item = new Item(prod, cantidad);
		
		return item;
	}

}
