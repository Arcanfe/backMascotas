package ga.mascotas.api.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import ga.mascotas.api.model.Establishment;
import ga.mascotas.api.repository.EstablishmentRepository;
import ga.mascotas.api.repository.UserRepository;

@RestController
@CrossOrigin
public class EstablishmentController {

	private final static String ROOT = "establishment";
	
	@Autowired
	private EstablishmentRepository repository;
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idE}")
	public Establishment findById(@PathVariable String idE){
		Establishment local = repository.findByid(idE); 
		return local;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/idUser/{idU}")
	public Establishment findByIdUsuario(@PathVariable String idU){
		Establishment local = repository.findByIdUsuario(idU); 
		return local;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/email/{email}")
	public Establishment findByEmail(@PathVariable String email){
		Establishment local = repository.findByEmail(email); 
		return local;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/NIT/{nit}")
	public Establishment findByNit(@PathVariable String nit){
		Establishment local = repository.findByNit(nit); 
		return local;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/paginaWeb/{pagina}")
	public Establishment findByPaginaWeb(@PathVariable String pagina){
		Establishment local = repository.findByPaginaWeb(pagina); 
		return local;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<Establishment> find(){
		List<Establishment> listaLocales = repository.findAll(); 
		return listaLocales;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createE")
	public String createEstablishment(@RequestBody Map<?, ?> body) throws IOException{
		
		String idUser = (String) body.get("idUsuario");
		String nit = (String) body.get("nit");
		String nombre = (String) body.get("nombre");
		String telefono = (String) body.get("telefono");
		String direccion = (String) body.get("direccion");
		String email = (String) body.get("email");
		String paginaWeb = (String) body.get("paginaWeb");
		ArrayList<String> servicios = (ArrayList<String>) body.get("servicios"); 
		String horarios = (String) body.get("horarios");
		String horaInicial = (String) body.get("horaInicial");
		String horaFinal = (String) body.get("horaFinal");
		String descripcion = (String) body.get("descripcion");
		String imagen = (String) body.get("imagen");
		
		Establishment nuevo = new Establishment(idUser, nit, nombre, descripcion);
		
		Establishment byUser = repository.findByIdUsuario(idUser);
		Establishment byNit = repository.findByNit(nit);
		Establishment byTelefono = repository.findByTelefono(telefono);
		Establishment byDireccion = repository.findByDireccion(direccion);
		Establishment byEmail = repository.findByEmail(email);
		Establishment byPaginaWeb = repository.findByPaginaWeb(paginaWeb);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			if(byUser != null) {
				resultNode.put("status", false);
			}
			else if(byNit != null) {
				resultNode.put("status", false);
			}
			else if(byTelefono != null) {
				resultNode.put("status", false);
			}
			else if(byDireccion != null) {
				resultNode.put("status", false);
			}
			else if(byEmail != null) {
				resultNode.put("status", false);
			}
			else if(byPaginaWeb != null) {
				resultNode.put("status", false);
			}
			else {
				this.repository.insert(nuevo);
				resultNode.put("status", true);
				resultNode.put("message", "OK");
			}
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------Pet - Create - Exception-------------");
			System.out.println(ex.getMessage());
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
	}
	
	@CrossOrigin
	@PutMapping(ROOT + "/{idE}")
	public String editEstablishment(@PathVariable String idE, @RequestBody Map<?, ?> body) throws Exception {
		
		Establishment local = repository.findByid(idE);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		if(local == null) {
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		
		local.setNombre((String) body.get("nombre"));
		local.setTelefono((String) body.get("telefono"));
		local.setDireccion((String) body.get("direccion"));
		local.setEmail((String) body.get("email"));
		local.setPaginaWeb((String) body.get("paginaWeb"));
		ArrayList<String> servicios = (ArrayList<String>) body.get("servicios");
		local.setServicios(servicios);
		local.setHorarios((String) body.get("Horarios"));
		local.setHoraInicial((String) body.get("horaInicial"));
		local.setHoraFinal((String) body.get("horaFinal"));
		local.setDescripcion((String) body.get("descripcion"));
		local.setImagen((String) body.get("imagen"));
		if((boolean) body.get("habilitado").equals("true")) {
			local.setHabilitado(true);
		}
		else {
			local.setHabilitado(false);
		}
		
		try {
			repository.save(local);
			mapper = new ObjectMapper();
			resultNode.put("status", true);
			resultNode.put("message", "OK");
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------Establishment - Update - Exception-------------");
			System.out.println(ex.getMessage());
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		
	}
	
	@CrossOrigin
	@DeleteMapping(ROOT + "/{idE}")
	public String deleteEstablishment(@PathVariable String idE) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(idE));
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
