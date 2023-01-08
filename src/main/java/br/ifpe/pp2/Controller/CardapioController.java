package br.ifpe.pp2.Controller;
 
import java.io.IOException; 

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.pp2.models.produtos.Produtos;
import br.ifpe.pp2.models.produtos.ProdutosDAO;
import br.ifpe.pp2.models.produtos.Categorias;
import br.ifpe.pp2.models.produtos.CategoriasDAO;
import br.ifpe.pp2.models.usuarios.Usuarios;
import br.ifpe.pp2.models.usuarios.UsuariosDAO;
import jakarta.servlet.http.HttpSession;

@Controller
public class CardapioController {
	
	@Autowired
	private UsuariosDAO usuariosdao;
	@Autowired 
	private ProdutosDAO produtosdao;
	@Autowired
	private CategoriasDAO categoriadao;
	
	@GetMapping("/")
	public String produtos( Model model) {
		model.addAttribute("listarProdutos", this.produtosdao.findAll());
		model.addAttribute("mostrarTipos", this.categoriadao.findAll());		
		return "home";
	}
	
	//Mostrar imagem
	@GetMapping("/foto/{idprod}")
	@ResponseBody
	public byte[] exibirImagem(@PathVariable("idprod") Long idprod) {
		Produtos produto = this.produtosdao.findById(idprod).orElse(null);
		return produto.getImagem();
	}
	
	@GetMapping("/meuspedidos")
	public String meusPedidos() {
		return "meusPedidos";
	}
	
	@GetMapping("/carrinho")
	public String carrinho() {
		return "carrinho";
	}
	@GetMapping("/minhaconta")
	public String conta() {
		return "minhaConta";
	}
	@GetMapping("/pagamento")
	public String pagamento() {
		return "pagamento";
	}
	

	@PostMapping("/removerConta")
	public String removerVeiculo(HttpSession session) {
		String id = session.getAttribute("id").toString();
		long codigo = Long.parseLong(id);
		usuariosdao.deleteById(codigo);
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login(Usuarios usuarios) {
		return "login";
	}
	@PostMapping("/logout")
	public String logout(HttpSession session	) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	@PostMapping("/login/usuario")
	public String loginUsuario(String email,String senha, RedirectAttributes ra, HttpSession session) {

		
		Usuarios usuario = this.usuariosdao.findByEmailAndSenha(email, senha);
		if (usuario != null) {
			if(session.getAttribute("tipo") == "admin") {
				session.invalidate();
			}
			session.setAttribute("usuarioLogado", usuario);
			session.setAttribute("id", usuario.getId());
			session.setAttribute("tipo", usuario.getAdmin());
			System.out.println(usuario.getId());			
			return "redirect:/";
		} else {
			ra.addFlashAttribute("mensagemErro", "Usuário/senha inválidos");
			return "redirect:/login";
		} 

	}


	
	
	
	

}
