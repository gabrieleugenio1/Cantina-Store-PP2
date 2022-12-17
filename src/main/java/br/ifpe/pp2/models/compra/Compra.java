package br.ifpe.models.compra;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.ifpe.models.produtos.ProdutosPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;

@Entity
public class Compra {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id_produto;
	private Float preco;
	private String observacao;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate datacompra;
	private TipoPagamento tipo;
	private StatusPedido status;
	@OneToOne
	private ProdutosPedido pedido;
	
	public Integer getId_produto() {
		return id_produto;
	}
	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public LocalDate getDatacompra() {
		return datacompra;
	}
	public void setDatacompra(LocalDate datacompra) {
		this.datacompra = datacompra;
	}
	public TipoPagamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public ProdutosPedido getPedido() {
		return pedido;
	}
	public void setPedido(ProdutosPedido pedido) {
		this.pedido = pedido;
	}


}
