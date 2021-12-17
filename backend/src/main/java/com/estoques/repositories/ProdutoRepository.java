package com.estoques.repositories;

import com.estoques.models.Fornecedor;
import com.estoques.models.Produto;
import com.estoques.models.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findProdutoByName(String name);

    Optional<TipoProduto> findTipoProdutoById(Long id);

    Optional<Fornecedor> findFornecedorById(Long id);
}
