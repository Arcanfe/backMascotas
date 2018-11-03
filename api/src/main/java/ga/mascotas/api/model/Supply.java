package ga.mascotas.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Supplies")
public class Supply {

	@Id
	private String id;
	private String idTipo;
	private String nombre;
	private int cantidad;
	private String unidad;
	private Date fechaCompra;
	private int precio;
	private String proveedor;
	private String consumoDiario;
	private String comentario;
	private String idUser;
	
	public Supply() {
	}
	

	public Supply(String id, String idTipo, String nombre, int cantidad, String unidad, Date fechaCompra,
			int precio, String proveedor, String consumoDiario, String comentario, String idUser) {
		this.id = id;
		this.idTipo = idTipo;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.fechaCompra = fechaCompra;
		this.precio = precio;
		this.proveedor = proveedor;
		this.consumoDiario = consumoDiario;
		this.comentario = comentario;
		this.idUser = idUser;
	}
	
	public Supply(String idTipo, String nombre, int cantidad, String unidad, Date fechaCompra,
			int precio, String proveedor, String consumoDiario, String comentario, String idUser) {
		this.idTipo = idTipo;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.fechaCompra = fechaCompra;
		this.precio = precio;
		this.proveedor = proveedor;
		this.consumoDiario = consumoDiario;
		this.comentario = comentario;
		this.setIdUser(idUser);
	}



	public Supply(String nombre, int cantidad, String unidad, Date fechaCompra, int precio, String proveedor,
			String consumoDiario, String comentario, String idUser) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.fechaCompra = fechaCompra;
		this.precio = precio;
		this.proveedor = proveedor;
		this.consumoDiario = consumoDiario;
		this.comentario = comentario;
		this.setIdUser(idUser);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getConsumoDiario() {
		return consumoDiario;
	}

	public void setConsumoDiario(String consumoDiario) {
		this.consumoDiario = consumoDiario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}



	public String getIdTipo() {
		return idTipo;
	}



	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

		
}
