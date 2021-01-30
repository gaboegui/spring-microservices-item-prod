package ec.pymeapps.micro.app.item.models.service;

import java.util.List;

import ec.pymeapps.micro.app.item.models.Item;

public interface ItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);

}
