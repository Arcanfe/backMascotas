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
import ga.mascotas.api.model.SupplyType;
import ga.mascotas.api.repository.SupplyTypeRepository;

@RestController
@CrossOrigin
public class SupplyTypeController {

	private final static String ROOT = "supplyType";
	
	@Autowired
	private SupplyTypeRepository repository;
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idST}")
	public SupplyType findById(@PathVariable String idST){
		SupplyType tipo = repository.findByid(idST); 
		return tipo;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/nombre/{nombre}")
	public SupplyType findByNombre(@PathVariable String nombre){
		SupplyType tipo = repository.findByNombre(nombre); 
		return tipo;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<SupplyType> find(){
		List<SupplyType> tipo = repository.findAll(); 
		return tipo;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createST")
	public String createSupplyType(@RequestBody Map<String, String> body) throws IOException, Exception{
		
		String nombre = body.get("nombre");
		
		SupplyType nuevo = new SupplyType(nombre);
		
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
	@PutMapping(ROOT + "/{idST}")
	public String editSupplyType(@PathVariable String idST, @RequestBody Map<String, String> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		SupplyType tipo = repository.findByid(idST);
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
	@DeleteMapping(ROOT + "/{idST}")
	public String deletePetType(@PathVariable String idST) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(idST));
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
