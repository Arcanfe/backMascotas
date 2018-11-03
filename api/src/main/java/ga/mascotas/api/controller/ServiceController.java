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

import ga.mascotas.api.model.PetType;
import ga.mascotas.api.model.Service;
import ga.mascotas.api.repository.ServiceRepository;

@RestController
@CrossOrigin
public class ServiceController {

	private final static String ROOT = "service";
	
	@Autowired
	private ServiceRepository repository;
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idS}")
	public Service findById(@PathVariable String idS){
		Service tipo = repository.findByid(idS); 
		return tipo;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/nombre/{nombre}")
	public Service findByNombre(@PathVariable String nombre){
		Service tipo = repository.findByNombre(nombre); 
		return tipo;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<Service> find(){
		List<Service> tipo = repository.findAll();
		return tipo;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createS")
	public String createService(@RequestBody Map<String, String> body) throws IOException, Exception{
		
		String nombre = body.get("nombre");
		
		Service nuevo = new Service(nombre);
		
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
			System.out.println("-----------Service - Create - Exception-------------");
			System.out.println(ex.getMessage());
			mapper = new ObjectMapper();
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}

	}
	
	@CrossOrigin
	@PutMapping(ROOT + "/{idS}")
	public String editService(@PathVariable String idS, @RequestBody Map<String, String> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		Service tipo = repository.findByid(idS);
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
	@DeleteMapping(ROOT + "/{idS}")
	public String deleteService(@PathVariable String idS) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(idS));
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
