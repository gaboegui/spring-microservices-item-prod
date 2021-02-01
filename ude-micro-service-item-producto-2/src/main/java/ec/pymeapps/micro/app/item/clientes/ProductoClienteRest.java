package ec.pymeapps.micro.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ec.pymeapps.micro.commons.app.models.entity.Producto;

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
	
	@PostMapping("/crear")
	public Producto crearProducto(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	public Producto update(@RequestBody Producto producto,	@PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);

}
