package ga.mascotas.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Services")
public class Service {

	@Id
	private String id;
	private String nombre;
	
	public Service() {
	}

	public Service(String nombre) {
		this.nombre = nombre;
	}
	
	public Service(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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
	
	
}
