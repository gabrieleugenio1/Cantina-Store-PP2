package br.ifpe.pp2;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
