package ec.pymeapps.micro.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ec.pymeapps.micro.app.item.models.Item;
import ec.pymeapps.micro.app.item.models.Producto;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService  {

	
	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {

		// obtengo los datos del microservicio a traves del ENDPOINT
		
		// sin balanceo de carga 
		//List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		
		// con balanceo
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://pymeapps.servicio.producto/listar", Producto[].class));
		
		
		// convierte una Lista de productos a una lista de Items utilizando el constructor de item con cantidad 1
		return productos.stream().map(prod -> new Item(prod, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		Map<String, String> pathVariables = new HashMap<String, String>();
		
		// en este mapa añado todas las variables necesarias descritas en el URL
		pathVariables.put("id", id.toString());
		
		// obtengo los datos del microservicio a traves del ENDPOINT
		// sin balanceo de carga
		// Producto producto = clienteRest.getForObject("http://localhost:8001/ver/{id}", Producto.class, pathVariables);
		
		// con balanceo
		Producto producto = clienteRest.getForObject("http://pymeapps.servicio.producto/ver/{id}", Producto.class, pathVariables);
		
		return new Item(producto, cantidad);

	}

}
