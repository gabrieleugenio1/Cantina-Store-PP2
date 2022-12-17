package br.ifpe.pp2.models.usuarios;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;

@Entity	
public class Usuarios {
	
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id_usuario;
	private String nome;
	private String email;
	private String senha; 
	private String telefone;
	 @Column(columnDefinition = "smallint default 0")
	private Tipo tipo;
	@Lob
	private byte[] foto;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate criacaoConta;
	
	public Long  getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long  id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public LocalDate getCriacaoConta() {
		return criacaoConta;
	}
	public void setCriacaoConta(LocalDate criacaoConta) {
		this.criacaoConta = criacaoConta;
	}
}
