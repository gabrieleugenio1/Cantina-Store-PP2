package br.ifpe.pp2;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ifpe.pp2.models.usuarios.UsuariosDAO;

@Controller
public class CardapioController {
	
	
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/meuspedidos")
	public String meusPedidos() {
		return "meusPedidos";
	}
	
	@RequestMapping("/minhaconta")
	public String conta() {
		return "minhaConta";
	}
	@RequestMapping("/pagamento")
	public String pagamento() {
		return "pagamento";
	}




}
