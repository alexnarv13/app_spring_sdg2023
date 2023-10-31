package py.edu.facitec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.facitec.model.Suscrito;
														//entidad Tipo de datos del ID
public interface SuscritoRepository extends JpaRepository<Suscrito, Long> {//CRUD
	
	

}
