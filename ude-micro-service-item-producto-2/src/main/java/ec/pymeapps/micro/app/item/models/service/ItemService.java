package ec.pymeapps.micro.app.item.models.service;

import java.util.List;

import ec.pymeapps.micro.app.item.models.Item;
import ec.pymeapps.micro.commons.app.models.entity.Producto;

public interface ItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
	
	public Producto save(Producto producto);
	
	public Producto update(Producto producto, Long id);
	
	public void delete(Long id);
	

}
