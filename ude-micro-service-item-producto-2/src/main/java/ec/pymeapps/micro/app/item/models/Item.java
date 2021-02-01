package ec.pymeapps.micro.app.item.models;

import java.math.BigDecimal;

import ec.pymeapps.micro.commons.app.models.entity.Producto;



public class Item {

	private Producto producto;
	private Integer cantidad;
	
	public BigDecimal getTotal() {
		return producto.getPrecio().multiply(new BigDecimal(cantidad));
	}
	
	public Item() {
	}
	
	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
