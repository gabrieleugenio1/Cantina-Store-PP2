package br.ifpe.pp2.models.produtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tipo {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id_tipo;
	private String nome;
	
	public Long getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(Long id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
