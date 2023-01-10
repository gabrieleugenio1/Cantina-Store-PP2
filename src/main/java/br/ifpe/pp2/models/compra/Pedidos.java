package br.ifpe.pp2.models.compra;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import br.ifpe.pp2.models.produtos.Produtos;
import br.ifpe.pp2.models.usuarios.Usuarios;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Pedidos {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id_pedidos;
	private Double preco;
	private String observacao;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataCompra;
	private TipoPagamento tipoPagamento;
	private StatusPedido status;
    @ManyToOne
    private Usuarios usuario;
	@OneToMany
	private List<Produtos> produto;
	public Long getId_pedidos() {
		return id_pedidos;
	}
	public void setId_pedidos(Long id_pedidos) {
		this.id_pedidos = id_pedidos;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public LocalDate getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	public List<Produtos> getProduto() {
		return produto;
	}
	public void setProduto(List<Produtos> produto) {
		this.produto = produto;
	} 
	

}
