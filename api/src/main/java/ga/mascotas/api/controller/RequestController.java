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
import ga.mascotas.api.model.Request;
import ga.mascotas.api.repository.RequestRepository;

@RestController
@CrossOrigin
public class RequestController {

	private final static String ROOT = "request";
	
	@Autowired
	private RequestRepository repository;
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idD}")
	public Request findById(@PathVariable String idD){
		Request cita = repository.findByid(idD); 
		return cita;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/idPet/{idP}")
	public List<Request> findByIdMascota(@PathVariable String idP){
		List<Request> cita = repository.findByIdMascota(idP); 
		return cita;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/idService/{idS}")
	public List<Request> findByIdServicio(@PathVariable String idS){
		List<Request> cita = repository.findByIdServicio(idS); 
		return cita;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/idEstablishment/{idE}")
	public List<Request> findByIdEstablecimiento(@PathVariable String idE){
		List<Request> cita = repository.findByIdEstablecimiento(idE); 
		return cita;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<Request> findAll(){
		List<Request> cita = repository.findAll(); 
		return cita;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createC")
	public String createDate(@RequestBody Map<String, String> body) throws IOException, Exception{
		
		String idMascota = body.get("idMascota");
		String idServicio = body.get("idServicio");
		String idEstablecimiento = body.get("idEstablecimiento");
		String mensaje = body.get("mensaje");
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("fecha"));
		
		Request nuevo = new Request(idMascota, idServicio, idEstablecimiento, mensaje, fecha);
		
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
	@PutMapping(ROOT + "/{idC}")
	public String editDate(@PathVariable String idC, @RequestBody Map<String, String> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		Request cita = repository.findByid(idC);
		if(cita == null) {
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		cita.setIdMascota(body.get("idMascota"));
		cita.setIdServicio(body.get("idServicio"));
		cita.setIdEstablecimiento(body.get("idEstablecimiento"));
		cita.setMensaje(body.get("mensaje"));
		cita.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(body.get("fecha")));
		
		try {
			this.repository.save(cita);
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
	@DeleteMapping(ROOT + "/{idC}")
	public String deleteDate(@PathVariable String idC) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(idC));
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
