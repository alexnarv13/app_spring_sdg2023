package py.edu.facitec.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//clase padre en JPA permite reutilizar los atributos contenidos
@MappedSuperclass
public abstract class General {
	
	@Id             //estrategia  para usar la gen de la db
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "General [id=" + id + "]";
	}
	
	
	

}
