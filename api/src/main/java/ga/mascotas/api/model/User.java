package ga.mascotas.api.model;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
public class User {
	
	@Id
	private String id;
	private String idTipo;
	private String nombre;
	private String apellidos;
	private String email;
	private String nombreUsuario;
	private String establecimiento;
	private String nit;
	private String descripcion;
	private String password;
	private boolean habilitado;
	
	public User() {
		
	}
	
	public User(String id, String nombre, String apellidos, String email, String nombreUsuario, String establecimiento,
			String nit, String descripcion, String password, boolean habilitado) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.establecimiento = establecimiento;
		this.nit = nit;
		this.descripcion = descripcion;
		this.password = password;
		this.habilitado = habilitado;
	}



	public User(String idTipo, String nombre, String apellidos, String email, String nombreUsuario,
			String password) {
		//this.tipoUsuario = new UserType("1");
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.habilitado = true;
	}

	
	public User(String idTipo, String nombre, String apellidos, String email, String nombreUsuario,
			String establecimiento, String nit, String descripcion, String password) {
		this.idTipo = idTipo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.establecimiento = establecimiento;
		this.nit = nit;
		this.descripcion = descripcion;
		this.password = password;
		this.habilitado = true;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
}
