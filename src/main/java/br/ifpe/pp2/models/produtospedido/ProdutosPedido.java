package br.ifpe.pp2.models.produtospedido;


import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import br.ifpe.pp2.models.produtos.Produtos;
import br.ifpe.pp2.models.usuarios.Usuarios;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;

@Entity
public class ProdutosPedido {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id_compra;
	@ManyToOne
	private Produtos produto;
	public Produtos getProduto() {
		return produto;
	}
	public void setProduto(Produtos produto) {
		this.produto = produto;
	}
	private Integer quantidade;
    @OneToOne
    private Usuarios usuario;
    
	public Long getId_compra() {
		return id_compra;
	}
	public void setId_compra(Long id_compra) {
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
