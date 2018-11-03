package ga.mascotas.api.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Establishments")
public class Establishment {

	@Id
	private String id;
	private String idUsuario;
	private String nit;
	private String nombre;
	private String telefono;
	private String direccion;
	private String email;
	private String paginaWeb;
	private ArrayList<String> servicios;
	private String horarios;
	private String horaInicial;
	private String horaFinal;
	private String descripcion;
	private String imagen;
	private boolean habilitado;
	
	
	
	public Establishment() {
	}

	public Establishment(String idUsuario, String nit, String nombre, String descripcion) {
		this.idUsuario = idUsuario;
		this.nit = nit;
		this.nombre = nombre;
		this.telefono = "";
		this.direccion = "";
		this.email = "";
		this.paginaWeb = "";
		this.servicios = new ArrayList<>();
		this.horarios = "";
		this.horaInicial = "";
		this.horaFinal = "";
		this.descripcion = descripcion;
		this.imagen = "";
		this.habilitado = false;
	}

	public Establishment(String id, String idUsuario, String nit, String nombre, String descripcion) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.nit = nit;
		this.nombre = nombre;
		this.telefono = "";
		this.direccion = "";
		this.email = "";
		this.paginaWeb = "";
		this.servicios = new ArrayList<>();
		this.horarios = "";
		this.horaInicial = "";
		this.horaFinal = "";
		this.descripcion = descripcion;
		this.imagen = "";
		this.habilitado = false;
	}
	
	public Establishment(String idUsuario, String nit, String nombre, String telefono, String direccion,
			String email, String paginaWeb, ArrayList<String> servicios, String horarios, String horaInicial,
			String horaFinal, String descripcion, String imagen, boolean habilitado) {
		this.idUsuario = idUsuario;
		this.nit = nit;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		this.paginaWeb = paginaWeb;
		this.servicios = servicios;
		this.horarios = horarios;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.habilitado = habilitado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public ArrayList<String> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<String> servicios) {
		this.servicios = servicios;
	}

	public String getHorarios() {
		return horarios;
	}

	public void setHorarios(String horarios) {
		this.horarios = horarios;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
	
	
}
