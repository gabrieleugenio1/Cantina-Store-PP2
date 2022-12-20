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
public class FiltroUsuarioLogado implements Filter{
	private String[] pathsLiberados = { "/","/login","/login/usuario","/meuspedidos", "/cadastro", "/salvar/novousuario","/gerenciamento", "/h2-console(.*)" ,"/css(.*)", "/js(.*)"};
	private String[] pathsBloqueados = {"/cadastro","/login"};

	@Override
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession sessao = req.getSession();
		String path = req.getRequestURI();
		// Verificar se o path chamado está na lista dos liberados
		for (String livre : pathsLiberados) {
			if (path.matches(livre)) {
				chain.doFilter(request, response);
				return;
			}
		}


		if (sessao != null && sessao.getAttribute("usuarioLogado") != null) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect("/");
		}
	}
	
	
	
	

}
