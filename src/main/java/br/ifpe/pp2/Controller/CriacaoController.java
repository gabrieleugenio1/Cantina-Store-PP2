package br.ifpe.pp2.Controller;

import java.io.IOException;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.pp2.models.compra.Pedidos;
import br.ifpe.pp2.models.compra.PedidosDAO;
import br.ifpe.pp2.models.produtos.Categorias;
import br.ifpe.pp2.models.produtos.CategoriasDAO;
import br.ifpe.pp2.models.produtos.Produtos;
import br.ifpe.pp2.models.produtos.ProdutosDAO;
import br.ifpe.pp2.models.usuarios.Usuarios;
import br.ifpe.pp2.models.usuarios.UsuariosDAO;
import jakarta.servlet.http.HttpSession;
 
@Controller
public class CriacaoController {
	@Autowired
	private UsuariosDAO usuariosdao;
	@Autowired 
	private ProdutosDAO produtosdao;
	@Autowired
	private CategoriasDAO categoriadao;
	@Autowired
	private PedidosDAO pedidosdao;
	
	
	@GetMapping("/cadastro")
	public String cadastro(Usuarios usuario) {
		return "cadastro";
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
	
	@PostMapping("/alterardados")
	public String alterarDados(Usuarios usuarios,String nome, Boolean tipo,String email,RedirectAttributes redirect,String numero,HttpSession session) {		
		String id = session.getAttribute("id").toString();
		long codigo = Long.parseLong(id);
		System.out.println(tipo);
		
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
		session.setAttribute("tipo", encontrado.getAdmin());
		session.setAttribute("id", encontrado.getId());
		return "redirect:/minhaconta";
	}	
	
	@GetMapping("/gerenciamento")
	public String gerenciamento(Categorias categorias,Produtos produtos,Model model) {
		model.addAttribute("tipoAlimento", this.categoriadao.findAll());
		return "gerenciamento";
	}
	
	@PostMapping("/criarnovotipo")
	public String criarNovoTipo(Categorias tipo, RedirectAttributes redirect) {
		if(tipo.getNome() != null && categoriadao.findByNome(tipo.getNome()) == null) {
			
			categoriadao.save(tipo);			
			return "redirect:/gerenciamento";
		}else {
			redirect.addFlashAttribute("mensagem", "Categoria já existe ou nula");
			return "redirect:/gerenciamento";
		}
	}
	
	@PostMapping("/modificarcategoria")
	public String modificarCategoria(String nome, Integer antigonome,RedirectAttributes redirect) {
		Categorias alterarNome = categoriadao.findById(antigonome).orElse(null);
		System.out.println( categoriadao.findById(antigonome));

		if(nome != null) {	
			alterarNome.setNome(nome);	
			System.out.println(alterarNome.getNome());
			return "redirect:/gerenciamento";
		}else {
			redirect.addFlashAttribute("mensagem", "Categoria já existe ou nula");
			return "redirect:/gerenciamento";
		}
	}
	
	
	@PostMapping("/criarnovoproduto")
	public String criarNovoProduto(Produtos produtos, @RequestParam MultipartFile file) {
		try {
			produtos.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		produtosdao.save(produtos);
		return "redirect:/gerenciamento";
	}
	
	@GetMapping("/modificarPedidos")
	public String modificarPedidos(Model model) {
		model.addAttribute("MostrarPedidos", pedidosdao.findAll());
		return "modificarPedidos";
	}
	
	@GetMapping("/admin/editarPedido")
	public String editarPedido(Pedidos pedidos,Long codigo) {
		return "";
	}
	
}
