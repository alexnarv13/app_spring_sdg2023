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

import py.edu.facitec.model.Comentario;
import py.edu.facitec.repository.ComentarioRepository;
@RestController 
@RequestMapping("api/")//afecta a todo los metodos
public class ComentarioController {
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	
	
	//retornara una lista de comentario en formato json 
	//retornar un estado de la peticion
	//iniciar con la peticion /api/comentarios(cliente-servidor)
	// retornar del servidor al cliente un json [] (servidor-cliente)
	@GetMapping("comentarios")
	public ResponseEntity<List<Comentario>> getComentario(){
		                                             //retorna la lista completa
		List<Comentario> comentarios=comentarioRepository.findAll();
		System.out.println("Ingrese al listado");
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}
	
//	  resibir un objeto comentario en formato json{}
//	                 con la url/api/comentarios
//	Almacenar en la base de datos
//	Retonar el nuevo comentario registrado
//	Retornar el estado de la peticion ok si funciona
	                                   //error si no funciona
	@PostMapping("comentario")
	public ResponseEntity<Comentario>saveComentario(@RequestBody Comentario comentario){
		try {
			Comentario comentarioRetorno = comentarioRepository.save(comentario);
			//emite 200
			return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//si no funciona emite 500
			return new ResponseEntity<Comentario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	/**recibir el codigo de la entidad Comentario
	 * por la Url /api/comentario
	 * retornar un objeto comentario
	 * retornar el estaru 200 si existe y 400 si no existe
	 * */
	
	  @GetMapping("comentario/{codigo}")
	 public ResponseEntity<Comentario> getById(@PathVariable Long codigo){
		  System.out.println("Ingrese a consulta"+codigo);
		  
		  Optional<Comentario> comentario =comentarioRepository.findById(codigo);
		   
		  if (comentario.isPresent()) {
			  return new ResponseEntity<Comentario>(comentario.get(),HttpStatus.OK);
			
		}else {
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
	 }
	  
	  /**recibir un codigo
	   * por la url api/comentario
	   * retorna en caso de eliminar el ststus 200
	   * retornar en caso de no encontrar el ststus 400
	   * */
	  
	  @DeleteMapping("comentario/{codigo}")
		 public ResponseEntity<Comentario> deleteById(@PathVariable Long codigo){
			  System.out.println("Ingrese a Eliminar"+codigo);
			  
			  Optional<Comentario> comentario =comentarioRepository.findById(codigo);
			   
			  if (comentario.isPresent()) {
				  
				  comentarioRepository.deleteById(codigo);
				  return new ResponseEntity<>(HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		 }

}