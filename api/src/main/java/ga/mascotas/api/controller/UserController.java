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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ga.mascotas.api.model.User;
import ga.mascotas.api.repository.UserRepository;
import ga.mascotas.api.repository.UserTypeRepository;

@RestController
@CrossOrigin
public class UserController {

	private final static String ROOT = "user";
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserTypeRepository repositoryUserType;
	
	@PostMapping(ROOT + "/login")
	@ResponseBody
	@CrossOrigin
    public String loginOwner(@RequestBody Map<String, String> body){
        String usuario = body.get("usuario");
        String password = body.get("contrasena");
        
        User aux = repository.findByNombreUsuario(usuario); 
        
        String result;
        
        if(aux == null) {
        	result = "{\r\n" + 
					"	\"status\":false,\r\n" + 
					"}";
        }
        else {
        	if(password != aux.getPassword()) {
        		result = "{\r\n" + 
    					"	\"status\":false,\r\n" +  
    					"}";
        	}
        	else {
        		String tipo = repositoryUserType.findByid(aux.getId()).getNombre();
        		result = "{\r\n" + 
    					"	\"status\":true,\r\n" + 
    					"	\"message\":\"" + tipo + "\r\n" + 
    					"}";
        	}
        }
        
        return result;
//        if(usuario.equals("cristhian") && password.equals("1234")){
//        	return true;
//        }
//        else {
//        	return false;
//        }
    }
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idU}")
	public User findById(@PathVariable String idU){
		User usuario = repository.findByid(idU); 
		return usuario;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/user/{nameU}")
	public User findByNombreUsuario(@PathVariable String nameU){
		User usuario = repository.findByNombreUsuario(nameU); 
		return usuario;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<User> find(){
		List<User> listaUsuarios = repository.findAll(); 
		return listaUsuarios;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createU")
	public String createUser(@RequestBody Map<String, String> body) throws IOException{
		
		String idTipo = body.get("idTipo");
		String nombre = body.get("nombre");
		String apellidos = body.get("apellidos");
		String email = body.get("email");
		String nombreUsuario = body.get("nombreUsuario");
		String password = body.get("password");
		
		User nuevo = new User(idTipo, nombre, apellidos, email, nombreUsuario, password);
		
		User byEmail = repository.findByEmail(email);
		User byNombreUsuario = repository.findByNombreUsuario(nombreUsuario);
		
		String result;
		String resultOriginal = "";
		
		if(byEmail != null) {
//			ObjectMapper mapper = new ObjectMapper();
//			//JsonNode root = mapper.createObjectNode();
//			ObjectNode positionNode = mapper.createObjectNode();
//			positionNode.put("status", false);
//			positionNode.put("message", "Email Addres already exists!");
//			resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(positionNode);
			result = "{\r\n" + 
					"	\"status\":false,\r\n" + 
					"	\"message\":\"The email has already in use\"\r\n" + 
					"}";
		}
		else if(byNombreUsuario != null) {
//			ObjectMapper mapper = new ObjectMapper();
//			//JsonNode root = mapper.createObjectNode();
//			ObjectNode positionNode = mapper.createObjectNode();
//			positionNode.put("status", false);
//			positionNode.put("message", "The user has already exists!");
//			resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(positionNode);
			result = "{\r\n" + 
					"	\"status\":false,\r\n" + 
					"	\"message\":\"The user has already exists\"\r\n" + 
					"}";
		}
		else {
			result = "{\r\n" + 
					"	\"status\":true,\r\n" + 
					"	\"message\":\"OK\"\r\n" + 
					"}";
			repository.save(nuevo);
		}
		
		return result;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createO")
	public String createOwner(@RequestBody Map<String, String> body) throws Exception{
		String idTipo = body.get("idTipo");
		String nombre = body.get("nombre");
		String apellidos = body.get("apellidos");
		String email = body.get("email");
		String nombreUsuario = body.get("nombreUsuario");
		String establecimiento = body.get("establecimiento");
		String nit = body.get("nit");
		String descripcion = body.get("descripcion");
		String password = body.get("password");
		
		User nuevo = new User(idTipo, nombre, apellidos, email, nombreUsuario, establecimiento, nit, descripcion, password);
		
		User byEmail = repository.findByEmail(email);
		User byNombreUsuario = repository.findByNombreUsuario(nombreUsuario);
		User byNit = repository.findByNit(nit);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			if(byEmail != null) {
				resultNode.put("status", false);
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
			}
			else if(byNombreUsuario != null) {
				resultNode.put("status", false);
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
			}
			else if(byNit != null) {
				resultNode.put("status", false);
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
			}
			else {
				this.repository.insert(nuevo);
//				resultNode.put("status", true);
//				resultNode.put("message", "OK");
				EstablishmentController negocio = new EstablishmentController();
				return negocio.createEstablishment(body);
			}
		}
		catch(Exception ex) {			
			System.out.println("-----------User - Create - Exception-------------");
			System.out.println(ex.getMessage());
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
	}
	
	@CrossOrigin
	@PutMapping(ROOT + "/{idU}")
	public String editUser(@PathVariable String idU, @RequestBody Map<String, String> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		User usuario = repository.findByid(idU);
		if(usuario == null) {
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		
		usuario.setNombre(body.get("nombre"));
		usuario.setApellidos(body.get("apellidos"));
		usuario.setEmail(body.get("email"));
		usuario.setNombreUsuario(body.get("nombreUsuario"));
		usuario.setPassword(body.get("password"));	
		if(body.get("habilitado").equals("true")) {
			usuario.setHabilitado(true);
		}
		else {
			usuario.setHabilitado(false);
		}
		
		try {
			this.repository.save(usuario);
			mapper = new ObjectMapper();
			resultNode.put("status", true);
			resultNode.put("message", "OK");
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------User - Update - Exception-------------");
			System.out.println(ex.getMessage());
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
	}
	
	@CrossOrigin
	@PutMapping(ROOT + "/owner/{idO}")
	public String editOwner(@PathVariable String idO, @RequestBody Map<String, String> body) throws Exception {
		
		User usuario = repository.findByid(idO);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		if(usuario == null) {
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		
		//Condicional acerca de si existe o no el campo?
		usuario.setNombre(body.get("nombre"));
		usuario.setApellidos(body.get("apellidos"));
		usuario.setEmail(body.get("email"));
		usuario.setNombreUsuario(body.get("nombreUsuario"));
		usuario.setEstablecimiento(body.get("establecimiento"));
		usuario.setNit(body.get("nit"));
		usuario.setDescripcion(body.get("descripcion"));
		usuario.setPassword(body.get("password"));	
		if(body.get("habilitado").equals("true")) {
			usuario.setHabilitado(true);
		}
		else {
			usuario.setHabilitado(false);
		}
		
		try {
			this.repository.save(usuario);
			mapper = new ObjectMapper();
			resultNode.put("status", true);
			resultNode.put("message", "OK");
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------Owner - Update - Exception-------------");
			System.out.println(ex.getMessage());
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
	}
	
	@CrossOrigin
	@DeleteMapping(ROOT + "/{id}")
	public String deleteUser(@PathVariable String id) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		try {
			repository.delete(repository.findByid(id));
			mapper = new ObjectMapper();
			resultNode.put("status", true);
			resultNode.put("message", "OK");
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		catch(Exception ex) {
			System.out.println("-----------User - Delete - Exception-------------");
			System.out.println(ex.getMessage());
			mapper = new ObjectMapper();
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
	}
	
}
