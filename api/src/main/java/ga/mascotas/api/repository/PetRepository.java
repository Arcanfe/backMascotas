package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.Pet;

public interface PetRepository extends MongoRepository<Pet, String> {
	public List<Pet> findAll();
	public Pet findByid(String id);
	public Pet findByNombre(String nombre);
	public List<Pet> findByIdUsuario(String idUsuario);
	
	
}
