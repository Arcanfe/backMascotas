package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.PetType;

public interface PetTypeRepository extends MongoRepository<PetType, String> {

	public List<PetType> findAll();
	public PetType findByid(String id);
	public PetType findByNombre(String nombre);
	
}
