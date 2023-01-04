package br.ifpe.pp2.servicos;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroAdmin implements Filter{
	private String[] pathsLiberadosTodos = { "/","/carrinho","/login","/login/usuario","/meuspedidos","/admin","/entrar/admin", "/cadastro", "/salvar/novousuario","/criarnovotipo","/gerenciamento","/criarnovoproduto", "/foto(.*)","/h2-console(.*)" ,"/css(.*)", "/js(.*)"};
	private String[] pathsLiberadosLogado = { "/","/minhaconta","/alterardados","/carrinho","/login","/login/usuario","/meuspedidos","/admin","/entrar/admin", "/cadastro", "/salvar/novousuario","/criarnovotipo","/gerenciamento","/criarnovoproduto", "/foto(.*)","/h2-console(.*)" ,"/css(.*)", "/js(.*)"};
	
	@Override
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession sessao = req.getSession();
		String path = req.getRequestURI();

		for (String livre : pathsLiberadosTodos) {
			if (path.matches(livre)) {
				chain.doFilter(request, response);
				return;
			}
		}		
		
	
		System.out.println( sessao.getAttribute("tipo"));
		if(path.startsWith("/admin")) {
			if (sessao != null && sessao.getAttribute("tipo") != "admin") {
				res.sendRedirect("/");
				
			}else {
				chain.doFilter(request, response);

			}
		}
		if (sessao != null && sessao.getAttribute("usuarioLogado") != null){		
		chain.doFilter(request, response);

			
			
		} else {
			res.sendRedirect("/");
		}
	}
	
	
	
	

}
