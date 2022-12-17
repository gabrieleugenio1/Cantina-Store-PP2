package br.ifpe.models.produtos;


import org.springframework.data.annotation.Id;

import br.ifpe.models.usuarios.Usuarios;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;

@Entity
public class ProdutosPedido {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id_compra;
	private Integer quantidade;
    @OneToOne
    private Usuarios usuario;
    
	public Integer getId_compra() {
		return id_compra;
	}
	public void setId_compra(Integer id_compra) {
		this.id_compra = id_compra;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}    

	 
}
