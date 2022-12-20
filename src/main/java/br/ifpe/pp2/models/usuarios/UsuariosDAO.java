package br.ifpe.pp2.models.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosDAO extends JpaRepository <Usuarios, Long>{

	 public Usuarios findByEmail(String email);

	 public Usuarios findByEmailAndSenha(String email, String senha);

}
