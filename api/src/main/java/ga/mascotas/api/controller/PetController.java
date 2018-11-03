package ga.mascotas.api.controller;

import java.io.IOException;
import java.text.ParseException;
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

import ga.mascotas.api.model.Pet;
import ga.mascotas.api.model.PetType;
import ga.mascotas.api.model.User;
import ga.mascotas.api.repository.PetRepository;
import ga.mascotas.api.repository.PetTypeRepository;
import ga.mascotas.api.repository.UserRepository;

@RestController
@CrossOrigin
public class PetController {
	
	private final static String ROOT = "pet";
	
	@Autowired
	private PetRepository repository;
		
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idP}")
	public Pet findById(@PathVariable String idP){
		Pet mascota = repository.findByid(idP); 
		return mascota;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/nombre/{nombre}")
	public Pet findByNombre(@PathVariable String nombre){
		Pet mascota = repository.findByid(nombre); 
		return mascota;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/idUser/{idU}")
	public List<Pet> findByIdUsuario(@PathVariable String idU){
		List<Pet> mascota = repository.findByIdUsuario(idU); 
		return mascota;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<Pet> find(){
		List<Pet> listaMascotas = repository.findAll(); 
		return listaMascotas;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createP")
	public String createPet(@RequestBody Map<String, String> body) throws IOException, Exception{
		
		String idUser = body.get("idUsuario");
		String imagen = body.get("imagen");
		String nombre = body.get("nombre");
		String idTipo = body.get("idTipo");
		String genero = body.get("genero");
		String raza = body.get("raza");
		Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("fechaNacimiento"));
		String esterilizado = body.get("esterilizado");
		String color = body.get("color");
		String descripcion = body.get("descripcion");
		
		Pet nuevo = new Pet(idUser, imagen, nombre, idTipo, genero, fechaNacimiento, raza, esterilizado, color, descripcion);
		
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
			System.out.println("-----------Pet - Create - Exception-------------");
			System.out.println(ex.getMessage());
			mapper = new ObjectMapper();
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}

	}
	
	@CrossOrigin
	@PutMapping(ROOT + "/{idP}")
	public String editPet(@PathVariable String idP, @RequestBody Map<String, String> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		Pet mascota = repository.findByid(idP);
		if(mascota == null) {
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		mascota.setImagen(body.get("imagen"));
		mascota.setNombre(body.get("nombre"));
		mascota.setIdTipo(body.get("idTipo"));
		mascota.setGenero(body.get("genero"));
		mascota.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(body.get("fechaNacimiento")));
		mascota.setRaza(body.get("raza"));
		mascota.setEsterilizado(body.get("esterilizado"));
		mascota.setColor(body.get("color"));
		mascota.setDescripcion("descripcion");	
	
		try {
			this.repository.save(mascota);
			mapper = new ObjectMapper();
			resultNode.put("status", true);
			resultNode.put("message", "OK");
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------Pet - Update - Exception-------------");
			System.out.println(ex.getMessage());
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
	}
	
	@CrossOrigin
	@DeleteMapping(ROOT + "/{idM}")
	public String deletePet(@PathVariable String idM) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(idM));
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
