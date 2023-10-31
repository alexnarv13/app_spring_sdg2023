package py.edu.facitec.model;
import py.edu.facitec.model.Comentario;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;


@Entity
public class Suscrito {
	
	@Id
	@GeneratedValue
	private Long codigo;
	private String nombre;
	private String correo;
	//cuando el ojeto es mas importante
	//se ignora la lista.(2do caso)
	@JsonIgnore
	@OneToMany(mappedBy = "suscrito") // Indicar que es una relación OneToMany
	private List<Comentario> comentarios; // Relación con la entidad Comentario
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fecha;

	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	
	
}


	


