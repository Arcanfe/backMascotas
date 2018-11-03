package ga.mascotas.api.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ga.mascotas.api.model.Agenda;
import ga.mascotas.api.model.Pet;
import ga.mascotas.api.repository.AgendaRepository;
import ga.mascotas.api.repository.PetRepository;

@RestController
@CrossOrigin
public class AgendaController {

	private final static String ROOT = "agenda";
	
	@Autowired
	private AgendaRepository repository;
	
	@Autowired
	private PetRepository repositoryPet;
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idA}")
	public Agenda findById(@PathVariable String idA){
		Agenda agenda = repository.findByid(idA); 
		return agenda;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/idPet/{idP}")
	public List<Agenda> findByIdPet(@PathVariable String idP){
		List<Agenda> agenda = repository.findByIdMascota(idP); 
		return agenda;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<Agenda> findAll(){
		List<Agenda> listaAgenda = repository.findAll(); 
		return listaAgenda;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createU")
	public String createAgenda(@RequestBody Map<String, String> body) throws IOException, Exception{
		
		String idMascota = body.get("idMascota");		
		String nombre = body.get("nombre");
		String ubicacion = body.get("ubicacion");
		String tipo = body.get("tipo");
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("fecha"));
		String descripcion = body.get("descripcion");
		
		Agenda nuevo = new Agenda(idMascota, nombre, ubicacion, tipo, fecha, descripcion);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			this.repository.insert(nuevo);
			mapper = new ObjectMapper();
			resultNode.put("status", true);
			resultNode.put("message", "OK");
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------Agenda - Create - Exception-------------");
			System.out.println(ex.getMessage());
			mapper = new ObjectMapper();
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
	}
	
	@CrossOrigin
	@PutMapping(ROOT + "/{idA}")
	public String editAgenda(@PathVariable String idA, @RequestBody Map<String, String> body) throws Exception {
		
		Agenda agenda = repository.findByid(idA);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		if(agenda == null) {
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		agenda.setNombre(body.get("nombre"));
		agenda.setUbicacion(body.get("ubicacion"));
		agenda.setTipo(body.get("tipo"));	
		agenda.setFecha(new SimpleDateFormat("dd/MM/yyyy").parse(body.get("fecha")));
		agenda.setDescripcion(body.get("descripción"));
		
		try {
			this.repository.save(agenda);
			mapper = new ObjectMapper();
			resultNode.put("status", true);
			resultNode.put("message", "OK");
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------Agenda - Update - Exception-------------");
			System.out.println(ex.getMessage());
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
	}
	
	@CrossOrigin
	@DeleteMapping(ROOT + "/{idA}")
	public String deleteAgenda(@PathVariable String idA) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(idA));
			mapper = new ObjectMapper();
			resultNode.put("status", true);
			resultNode.put("message", "OK");
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------Pet - Delete - Exception-------------");
			System.out.println(ex.getMessage());
			mapper = new ObjectMapper();
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		
	}
	
}
