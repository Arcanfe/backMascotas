package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.Pet;
import ga.mascotas.api.model.User;
import ga.mascotas.api.model.UserType;

public interface UserTypeRepository extends MongoRepository<UserType, String> {
	
	public UserType findByNombre(String nombre);
	public UserType findByid(String id);
	public List<UserType> findAll();
}
