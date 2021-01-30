package ec.pymeapps.micro.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * utilizamos Client ya que solo hay un cleinte si consumieramos mas 
 * servicios se utilizaria @RibbonClients 
 * 
 * 
 * @author Editor
 *
 */
@RibbonClient(name = "pymeapps.servicio.producto")
@EnableFeignClients
@SpringBootApplication
public class UdeMicroServiceItemProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdeMicroServiceItemProductoApplication.class, args);
	}

}
