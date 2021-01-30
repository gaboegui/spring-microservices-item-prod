package ec.pymeapps.micro.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ec.pymeapps.micro.app.item.models.Producto;

/**
 * Clase que consume el Servicio Remoto Productos utilizando Feign
 * 
 * name = nombre del servicio definido por spring.application.name 
 * en el archivo application.properties del SERVICIO PRODUCTO
 * 
 * al utilizar ribbon tambien se eliminar el url, ya que se lo configurará
 * en el aplication.properties local:
 * 
 * @FeignClient(name = "pymeapps.servicio.producto", url = "localhost:8001")
 * 
 * @author Editor
 *
 */
@FeignClient(name = "pymeapps.servicio.producto")
public interface ProductoClienteRest {
	
	/**
	 * tiene que ser el mismo ENDPOINT del origen (servicio que estamos consumiendo)
	 * en este caso de ProductoController
	 * 
	 * @return
	 */
	@GetMapping("/listar")
	public List<Producto> listar();
	
	
	@GetMapping("/ver/{id}")
	public Producto ver(@PathVariable Long id);

}
