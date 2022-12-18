package br.ifpe.pp2;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.pp2.models.compra.CompraDAO;
import br.ifpe.pp2.models.produtos.ProdutosDAO;
import br.ifpe.pp2.models.produtospedido.ProdutosPedidoDAO;
import br.ifpe.pp2.models.usuarios.Usuarios;
import br.ifpe.pp2.models.usuarios.UsuariosDAO;
import br.ifpe.pp2.servicos.UsuarioServico;

@Controller
public class CardapioController {
	
	@Autowired
	private UsuarioServico usuariosdao;
	
	@Autowired
	private ProdutosPedidoDAO produtospedidodao;
	@Autowired
	private ProdutosDAO produtosdao;
	@Autowired
	private CompraDAO compradao;
	
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/meuspedidos")
	public String meusPedidos() {
		return "meusPedidos";
	}
	
	@GetMapping("/minhaconta")
	public String conta() {
		return "minhaConta";
	}
	@GetMapping("/pagamento")
	public String pagamento() {
		return "pagamento";
	}
	
	@GetMapping("/cadastro")
	public String cadastro(Usuarios usuario) {
		return "cadastro";
	}
	
	@PostMapping("/salvar/novousuario")
	public String salvarUsuario(Usuarios usuarios, RedirectAttributes redirect) {
		usuariosdao.salvar(usuarios);
		redirect.addFlashAttribute("sucess", "Conta criada com sucesso");
		return "redirect:/cadastro";
	}
	
	@GetMapping("/login")
	public String login(Usuarios usuarios) {
		return "login";
	}
	@PostMapping("/login/usuario")
	public String loginUsuario(Usuarios usuarios) {
		usuariosdao.entrar(usuarios);
		return "redirect:/login";
	}


}
