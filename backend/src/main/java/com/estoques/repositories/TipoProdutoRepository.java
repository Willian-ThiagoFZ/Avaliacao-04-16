package com.estoques.repositories;

import com.estoques.models.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {

    Optional<TipoProduto> findTipoProdutoByName(String name);
}
