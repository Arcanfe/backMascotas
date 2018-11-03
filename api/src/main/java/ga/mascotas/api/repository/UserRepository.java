package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.User;

public interface UserRepository extends MongoRepository<User, String> {
 
	public List<User> findAll();
	public User findByid(String id);
	public User findByNombreUsuario(String nombreUsuario);
	public User findByEmail(String email);
	public User findByNit(String nit);
	
}
