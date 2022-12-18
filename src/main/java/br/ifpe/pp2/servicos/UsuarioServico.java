package br.ifpe.pp2.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ifpe.pp2.models.usuarios.Usuarios;
import br.ifpe.pp2.models.usuarios.UsuariosDAO;

@Service
public class UsuarioServico {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuariosDAO usuariosdao;
	
	
	public void salvar(Usuarios usuarios) {
		  usuarios.setSenha(passwordEncoder.encode(usuarios.getSenha()));
		  usuariosdao.save(usuarios);
	}
	public void entrar(Usuarios usuarios) {	
		  usuarios.setSenha(passwordEncoder.encode(usuarios.getSenha()));
		  
		  System.out.println(usuarios.getSenha());
		usuariosdao.findByEmailAndSenha(usuarios.getEmail(), usuarios.getSenha());
		System.out.println(	usuariosdao.findByEmailAndSenha(usuarios.getEmail(), usuarios.getSenha())
);
	}
}
