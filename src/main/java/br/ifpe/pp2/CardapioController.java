package br.ifpe.pp2;
 
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.pp2.models.produtos.Produtos;
import br.ifpe.pp2.models.produtos.ProdutosDAO;
import br.ifpe.pp2.models.produtos.Tipo;
import br.ifpe.pp2.models.produtos.TipoDAO;
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
	private TipoDAO tipodao;
	
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
	@GetMapping("/gerenciamento")
	public String gerenciamento(Tipo tipo,Produtos produtos,Model model) {
		model.addAttribute("tipoAlimento", this.tipodao.findAll());
		return "gerenciamento";
	}
	@PostMapping("/criarnovotipo")
	public String criarNovoTipo(Tipo tipo) {
		tipodao.save(tipo);
		return "redirect:/gerenciamento";
	}
	@PostMapping("/criarnovoproduto")
	public String criarNovoProduto(Produtos produtos) {
		produtosdao.save(produtos);
		 return "redirect:/gerenciamento";
	}
	
	@PostMapping("/salvar/novousuario")
	public String salvarUsuario(String email,Usuarios usuarios, RedirectAttributes redirect) {
		
	if(email != null && usuariosdao.findByEmail(email) == null) {
		usuariosdao.save(usuarios);
		redirect.addFlashAttribute("sucess", "Conta criada com sucesso.");
		return "redirect:/cadastro";
		
	}else {
		redirect.addFlashAttribute("falha", "Falha ao criar conta, email existente.");
	  return "redirect:/cadastro";

	}}
	
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
	@PostMapping("/alterardados")
	public String alterarDados(Usuarios usuarios,String nome, String email,RedirectAttributes redirect,String numero,HttpSession session) {		
		String id = session.getAttribute("id").toString();
		long codigo = Long.parseLong(id);
		System.out.println(codigo);
		
		Usuarios encontrado = usuariosdao.findById(codigo).orElse(null);
		if(nome!= null) {
			encontrado.setNome(nome);
			redirect.addFlashAttribute("sucess", "Nome alterado com sucesso.");
		}
		if(email != null && usuariosdao.findByEmail(email) == null) {
			encontrado.setEmail(email);	
			redirect.addFlashAttribute("sucess", "Email alterado com sucesso.");
		}
		if(numero != null) {
			encontrado.setTelefone(numero);			
			redirect.addFlashAttribute("sucess", "Número alterado com sucesso.");
		}		
		usuariosdao.save(encontrado);
		session.setAttribute("usuarioLogado", encontrado);
		session.setAttribute("id", encontrado.getId());
		return "redirect:/minhaconta";
	}
	
	@PostMapping("/login/usuario")
	public String loginUsuario(String email,String senha, RedirectAttributes ra, HttpSession session) {
		Usuarios usuario = this.usuariosdao.findByEmailAndSenha(email, senha);
		if (usuario != null) {
			session.setAttribute("usuarioLogado", usuario);
			session.setAttribute("id", usuario.getId());
			System.out.println(usuario.getId());
			
			return "redirect:/";
		} else {
			ra.addFlashAttribute("mensagemErro", "Usuário/senha inválidos");
			return "redirect:/login";
		} 

	}


}
