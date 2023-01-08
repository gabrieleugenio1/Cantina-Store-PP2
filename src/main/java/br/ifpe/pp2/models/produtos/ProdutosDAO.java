package br.ifpe.pp2.models.produtos;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosDAO extends JpaRepository<Produtos, Long>{

	List<Produtos> findByNomeContainingIgnoreCase(String nomeProduto, Sort sort);
}
