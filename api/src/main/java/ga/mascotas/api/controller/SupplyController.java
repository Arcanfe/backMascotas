package ga.mascotas.api.controller;

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

import ga.mascotas.api.model.Supply;
import ga.mascotas.api.repository.SupplyRepository;

@RestController
@CrossOrigin
public class SupplyController {
	private final static String ROOT = "supply";
	
	@Autowired
	private SupplyRepository repository;
	
	@CrossOrigin
	@GetMapping(ROOT + "/id/{idS}")
	public Supply findById(@PathVariable String idS){
		Supply suministro = repository.findByid(idS); 
		return suministro;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/idUser/{idU}")
	public List<Supply> findByIdUser(@PathVariable String idU){
		List<Supply> suministro = repository.findByIdUser(idU); 
		return suministro;
	}
	
	@CrossOrigin
	@GetMapping(ROOT + "/")
	public List<Supply> find(){
		List<Supply> listaSuministros = repository.findAll(); 
		return listaSuministros;
	}
	
	@CrossOrigin
	@PostMapping(ROOT + "/createO")
	public String createSupply(@RequestBody Map<String, String> body) throws Exception{
		String idTipo = body.get("idTipo");
		String nombre = body.get("nombre");
		int cantidad = Integer.parseInt(body.get("cantidad"));
		String unidad = body.get("unidad");
		Date fechaCompra = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("fechaCompra"));
		int precio = Integer.parseInt(body.get("precio"));
		String proveedor = body.get("proveedor");
		String consumoDiario = body.get("consumoDiario");
		String comentario = body.get("comentario");
		String idUser = body.get("idUser");
		
		Supply nuevo = new Supply(idTipo, nombre, cantidad, unidad, fechaCompra, precio, proveedor, consumoDiario, comentario, idUser);
		
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
			System.out.println("-----------Supply - Create - Exception-------------");
			System.out.println(ex.getMessage());
			mapper = new ObjectMapper();
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}	
		
		
	}
	
	@CrossOrigin
	@PutMapping(ROOT + "/{idS}")
	public String editSupply(@PathVariable String idS, @RequestBody Map<String, String> body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode resultNode = mapper.createObjectNode();
		
		Supply suministro = repository.findByid(idS);
		if(suministro == null) {
			resultNode.put("status", false);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
		}
		
		
		suministro.setIdTipo(body.get("idTipo"));
		suministro.setNombre(body.get("nombre"));
		suministro.setCantidad(Integer.parseInt(body.get("cantidad")));
		suministro.setUnidad(body.get("unidad"));
		suministro.setFechaCompra(new SimpleDateFormat("yyyy-MM-dd").parse(body.get("fechaCompra")));
		suministro.setPrecio(Integer.parseInt(body.get("precio")));
		suministro.setProveedor(body.get("proveedor"));
		suministro.setConsumoDiario(body.get("consumoDiario"));
		suministro.setComentario(body.get("comentario"));
		suministro.setIdUser(body.get("idUser"));
		
		try {
			this.repository.save(suministro);
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
	public String deleteSupply(@PathVariable String idS) throws Exception {
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
