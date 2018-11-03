package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.Establishment;

public interface EstablishmentRepository extends MongoRepository<Establishment, String> {

	public List<Establishment> findAll();
	public Establishment findByid(String id);
	public Establishment findByIdUsuario(String idUsuario);
	public Establishment findByEmail(String email);
	public Establishment findByNit(String nit);
	public Establishment findByPaginaWeb(String paginaWeb);
	public Establishment findByTelefono(String telefono);
	public Establishment findByDireccion(String direccion);
	
}
