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

import py.edu.facitec.model.Post;
import py.edu.facitec.repository.PostRepository;
@RestController 
@RequestMapping("api/")//afecta a todo los metodos
public class PostController {
	@Autowired
	private PostRepository postRepository;
	
	
	
	//retornara una lista de pos en formato json 
	//retornar un estado de la peticion
	//iniciar con la peticion /api/poss(cliente-servidor)
	// retornar del servidor al cliente un json [] (servidor-cliente)
	@GetMapping("post")
	public ResponseEntity<List<Post>> getPost(){
		                                             //retorna la lista completa
		List<Post> post=postRepository.findAll();
		System.out.println("Ingrese al listado");
		return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
	}
	
//	  resibir un objeto pos en formato json{}
//	                 con la url/api/poss
//	Almacenar en la base de datos
//	Retonar el nuevo pos registrado
//	Retornar el estado de la peticion ok si funciona
	                                   //error si no funciona
	@PostMapping("post")
	public ResponseEntity<Post>savePost(@RequestBody Post pos){
		try {
			Post postRetorno = postRepository.save(pos);
			//emite 200
			return new ResponseEntity<Post>(pos, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//si no funciona emite 500
			return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	/**recibir el codigo de la entidad Post
	 * por la Url /api/pos
	 * retornar un objeto pos
	 * retornar el estaru 200 si existe y 400 si no existe
	 * */
	
	  @GetMapping("post/{codigo}")
	 public ResponseEntity<Post> getById(@PathVariable Long codigo){
		  System.out.println("Ingrese a consulta"+codigo);
		  
		  Optional<Post> pos =postRepository.findById(codigo);
		   
		  if (pos.isPresent()) {
			  return new ResponseEntity<Post>(pos.get(),HttpStatus.OK);
			
		}else {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		}
	 }
	  
	  /**recibir un codigo
	   * por la url api/pos
	   * retorna en caso de eliminar el ststus 200
	   * retornar en caso de no encontrar el ststus 400
	   * */
	  
	  @DeleteMapping("post/{codigo}")
		 public ResponseEntity<Post> deleteById(@PathVariable Long codigo){
			  System.out.println("Ingrese a Eliminar"+codigo);
			  
			  Optional<Post> pos =postRepository.findById(codigo);
			   
			  if (pos.isPresent()) {
				  
				  postRepository.deleteById(codigo);
				  return new ResponseEntity<>(HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		 }

}