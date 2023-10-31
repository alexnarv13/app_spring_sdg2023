package py.edu.facitec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//Trabaja con los request y response 
public class HomeController {
	
	@RequestMapping("/")// url a responder 
	public String retornarPagina() {
		
		System.out.print("ingreso al metodo para");
		//pagina a responder 
		return "usuario/login";
		
	}
		
		@RequestMapping("/form")// url a responder 
		public String accederFormularioSuscrito() {
			
			System.out.print("ingreso al metodo para cargar la pag. suscrito");
			//pagina a responder 
			return "suscrito/form";
			
		}
	

}
