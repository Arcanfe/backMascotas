package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.Service;

public interface ServiceRepository extends MongoRepository<Service, String> {

	public List<Service> findAll();
	public Service findByid(String id);
	public Service findByNombre(String nombre);
}
