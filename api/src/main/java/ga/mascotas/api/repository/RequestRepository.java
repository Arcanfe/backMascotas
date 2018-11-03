package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.Request;

public interface RequestRepository extends MongoRepository<Request, String> {
	public List<Request> findAll();
	public Request findByid(String id);
	public List<Request> findByIdMascota(String idMascota);
	public List<Request> findByIdServicio(String idServicio);
	public List<Request> findByIdEstablecimiento(String idEstablecimiento);
}
