package ga.mascotas.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ga.mascotas.api.model.Agenda;

public interface AgendaRepository extends MongoRepository<Agenda, String>{
	public List<Agenda> findAll();
	public Agenda findByid(String id);
	public List<Agenda> findByIdMascota(String idMascota);
}
