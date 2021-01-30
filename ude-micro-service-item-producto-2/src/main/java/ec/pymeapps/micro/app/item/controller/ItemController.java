package ec.pymeapps.micro.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ec.pymeapps.micro.app.item.models.Item;
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
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item ver(@PathVariable(name = "id") Long productoId, @PathVariable Integer cantidad){
		
		return service.findById(productoId, cantidad);
	}

	

}
