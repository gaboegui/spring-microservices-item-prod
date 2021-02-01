package ec.pymeapps.micro.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.pymeapps.micro.app.item.clientes.ProductoClienteRest;
import ec.pymeapps.micro.app.item.models.Item;

import ec.pymeapps.micro.commons.app.models.entity.Producto;


/**
 * Crea la clase utilizanco Feign, se anota como @Primary para que sea 
 * el que se inyecta por defecto en el Controller 
 * 
 * @author Editor
 *
 */
@Service("servicio-Productos-Feign")
@Primary
public class ItemServiceFeignImpl implements ItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(prod -> new Item(prod, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.ver(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		
		return clienteFeign.crearProducto(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);
		
	}

}
