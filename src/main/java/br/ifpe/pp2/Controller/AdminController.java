package br.ifpe.pp2.Controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.pp2.models.usuarios.Usuarios;
import br.ifpe.pp2.models.usuarios.UsuariosDAO;
import jakarta.servlet.http.HttpSession;
 
@Controller
public class AdminController {
	@Autowired
	private UsuariosDAO usuariosdao;
	
	@GetMapping("/admin")
	public String admin() {
		return "admin/admin";
	}
	@PostMapping("/entrar/admin")
	public String entrarAdmin(String login, String senha, HttpSession session, RedirectAttributes redirect) {
		String loginAdmin = login.toLowerCase();
		String senhaAdmin = senha.toLowerCase();
		System.out.println(loginAdmin.compareTo("entrar") + "  " + senhaAdmin.compareTo("123"));
		if(loginAdmin.compareTo("entrar") == 0 && senhaAdmin.compareTo("123") == 0) {
			session.setAttribute("tipo", "admin");
			return "redirect:/admin/index";
		}else {
			redirect.addFlashAttribute("mensagem", "Email ou senha incorreto");
			return "redirect:/admin";
		}
	}
	@GetMapping("/editarUsuario")
	public String editarVeiculo(Long codigo, Model model) {
		Usuarios usuarios = usuariosdao.findById(codigo).orElse(null);
		model.addAttribute("usuarios",usuarios);
		return "admin/alterarUsuario";
	}
	@GetMapping("/admin/index")
	public String indexAdmin(Model model) {
		model.addAttribute("listarUsuarios", usuariosdao.findAll());
		return "admin/index";
	}
	@PostMapping("/alterardadosusuario")
	public String alterarDados(Usuarios usuarios,String nome,String tipoconta, String email,RedirectAttributes redirect,String numero,HttpSession session) {		
		String id = session.getAttribute("id").toString();
		long codigo = Long.parseLong(id);
		System.out.println(tipoconta);
		
		Boolean admin = Boolean.valueOf(tipoconta);
		Usuarios encontrado = usuariosdao.findById(codigo).orElse(null);
		if(tipoconta!= null) {
			encontrado.setAdmin(admin);
			redirect.addFlashAttribute("sucess", "Tipo alterado com sucesso.");
		}
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
		return "redirect:/admin/index";
	}	
}
