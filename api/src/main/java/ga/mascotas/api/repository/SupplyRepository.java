package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.Supply;

public interface SupplyRepository extends MongoRepository<Supply, String>  {

	public List<Supply> findAll();
	public Supply findByid(String id);
	public List<Supply> findByIdUser(String idUser);
	
}
