package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.SupplyType;

public interface SupplyTypeRepository extends MongoRepository<SupplyType, String>   {

	public List<SupplyType> findAll();
	public SupplyType findByid(String id);
	public SupplyType findByNombre(String nombre);
}
