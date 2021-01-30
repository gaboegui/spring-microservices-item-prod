package ec.pymeapps.micro.app.item.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ec.pymeapps.micro.app.item.models.Item;
import ec.pymeapps.micro.app.item.models.Producto;
import ec.pymeapps.micro.app.item.models.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	//@Qualifier("serviceRestTemplate")   
	@Qualifier("servicio-Productos-Feign")
	private ItemService service;
	
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
