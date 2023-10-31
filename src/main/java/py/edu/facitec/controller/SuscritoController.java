package py.edu.facitec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//arquitectura  REST
//representacional state Transfer
//Peticion sin gettion de estado en el servidor

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;
@RestController 
@RequestMapping("api/")//afecta a todo los metodos
public class SuscritoController {
	@Autowired
	private SuscritoRepository suscritoRepository;
	
	
	
	//retornara una lista de suscrito en formato json 
	//retornar un estado de la peticion
	//iniciar con la peticion /api/suscritos(cliente-servidor)
	// retornar del servidor al cliente un json [] (servidor-cliente)
	@GetMapping("suscritos")
	public ResponseEntity<List<Suscrito>> getSuscrito(){
		                                             //retorna la lista completa
		List<Suscrito> suscritos=suscritoRepository.findAll();
		System.out.println("Ingrese al listado");
		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
	}
	
//	  resibir un objeto suscrito en formato json{}
//	                 con la url/api/suscritos
//	Almacenar en la base de datos
//	Retonar el nuevo suscrito registrado
//	Retornar el estado de la peticion ok si funciona
	                                   //error si no funciona
	@PostMapping("suscrito")
	public ResponseEntity<Suscrito>saveSuscrito(@RequestBody Suscrito suscrito){
		try {
			Suscrito suscritoRetorno = suscritoRepository.save(suscrito);
			//emite 200
			return new ResponseEntity<Suscrito>(suscrito, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//si no funciona emite 500
			return new ResponseEntity<Suscrito>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	/**recibir el codigo de la entidad Suscrito
	 * por la Url /api/suscrito
	 * retornar un objeto suscrito
	 * retornar el estaru 200 si existe y 400 si no existe
	 * */
	
	  @GetMapping("suscrito/{codigo}")
	 public ResponseEntity<Suscrito> getById(@PathVariable Long codigo){
		  System.out.println("Ingrese a consulta"+codigo);
		  
		  Optional<Suscrito> suscrito =suscritoRepository.findById(codigo);
		   
		  if (suscrito.isPresent()) {
			  return new ResponseEntity<Suscrito>(suscrito.get(),HttpStatus.OK);
			
		}else {
			return new ResponseEntity<Suscrito>(HttpStatus.NOT_FOUND);
		}
	 }
	  
	  /**recibir un codigo
	   * por la url api/suscrito
	   * retorna en caso de eliminar el ststus 200
	   * retornar en caso de no encontrar el ststus 400
	   * */
	  
	  @DeleteMapping("suscrito/{codigo}")
		 public ResponseEntity<Suscrito> deleteById(@PathVariable Long codigo){
			  System.out.println("Ingrese a Eliminar"+codigo);
			  
			  Optional<Suscrito> suscrito =suscritoRepository.findById(codigo);
			   
			  if (suscrito.isPresent()) {
				  
				  suscritoRepository.deleteById(codigo);
				  return new ResponseEntity<>(HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		 }

}