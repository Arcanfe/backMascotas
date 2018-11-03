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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ga.mascotas.api.model.Pet;
import ga.mascotas.api.model.PetType;
import ga.mascotas.api.repository.PetTypeRepository;

@RestController
@CrossOrigin
public class PetTypeController {

	private final static String ROOT = "petType";
	
	@Autowired
	private PetTypeRepository repository;
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idP}")
	public PetType findById(@PathVariable String idP){
		PetType tipo = repository.findByid(idP); 
		return tipo;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/name/{name}")
	public PetType findByName(@PathVariable String name){
		PetType tipo = repository.findByid(name); 
		return tipo;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<PetType> find(){
		List<PetType> tipo = repository.findAll(); 
		return tipo;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createTP")
	public String createTypePet(@RequestBody Map<String, String> body) throws IOException, Exception{
		
		String nombre = body.get("nombre");
		
		PetType nuevo = new PetType(nombre);
		
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
	@PutMapping(ROOT + "/{idTP}")
	public String editPetType(@PathVariable String idTP, @RequestBody Map<String, String> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		PetType tipo = repository.findByid(idTP);
		if(tipo == null) {
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		tipo.setNombre(body.get("nombre"));
		
		try {
			this.repository.save(tipo);
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
	@DeleteMapping(ROOT + "/{idPT}")
	public String deletePetType(@PathVariable String idPT) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(idPT));
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
