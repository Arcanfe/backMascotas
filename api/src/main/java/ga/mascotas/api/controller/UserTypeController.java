package ga.mascotas.api.controller;

import java.io.IOException;
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

import ga.mascotas.api.model.SupplyType;
import ga.mascotas.api.model.UserType;
import ga.mascotas.api.repository.UserTypeRepository;

@RestController
@CrossOrigin
public class UserTypeController {

	private final static String ROOT = "userType";
	
	@Autowired
	private UserTypeRepository repository;
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idUT}")
	public UserType findById(@PathVariable String idUT){
		UserType tipo = repository.findByid(idUT); 
		return tipo;
	}

	@CrossOrigin
	@GetMapping(ROOT + "/nombre/{nombre}")
	public UserType findByNombre(@PathVariable String nombre){
		UserType tipo = repository.findByid(nombre); 
		return tipo;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<UserType> find(){
		List<UserType> tipo = repository.findAll(); 
		return tipo;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createUT")
	public String createUserType(@RequestBody Map<String, String> body) throws IOException, Exception{
		
		String nombre = body.get("nombre");
		
		UserType nuevo = new UserType(nombre);
		
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
	@PutMapping(ROOT + "/{idUT}")
	public String editUserType(@PathVariable String idUT, @RequestBody Map<String, String> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		UserType tipo = repository.findByid(idUT);
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
	@DeleteMapping(ROOT + "/{idUT}")
	public String deleteUserType(@PathVariable String idUT) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(idUT));
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
