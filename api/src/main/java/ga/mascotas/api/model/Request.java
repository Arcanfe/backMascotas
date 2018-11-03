package ga.mascotas.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Request {

	@Id
	private String id;
	private String idMascota;
	private String idServicio;
	private String idEstablecimiento;
	private String mensaje;
	private Date fecha;
	
	public Request() {
	}
	
	public Request(String id, String idMascota, String idServicio, String idEstablecimiento, String mensaje,
			Date fecha) {
		this.id = id;
		this.idMascota = idMascota;
		this.idServicio = idServicio;
		this.idEstablecimiento = idEstablecimiento;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}



	public Request(String idMascota, String idServicio, String idEstablecimiento, String mensaje,
			Date fecha) {
		this.idMascota = idMascota;
		this.idServicio = idServicio;
		this.idEstablecimiento = idEstablecimiento;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(String idMascota) {
		this.idMascota = idMascota;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(String idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
