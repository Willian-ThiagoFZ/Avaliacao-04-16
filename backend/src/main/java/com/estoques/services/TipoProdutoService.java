package com.estoques.services;

import com.estoques.models.TipoProduto;
import com.estoques.repositories.ProdutoRepository;
import com.estoques.repositories.TipoProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoProdutoService {

    private final TipoProdutoRepository tipoProdutoRepository;
    private final ProdutoRepository produtoRepository;


    public boolean existsByTipoProdutoName(TipoProduto tipoProduto) {
        return tipoProdutoRepository.findTipoProdutoByName(tipoProduto.getName()).isPresent();
    }

    public TipoProduto create(TipoProduto tipoProduto) throws Exception {
        if (existsByTipoProdutoName(tipoProduto)) {
            throw new Exception("Tipo de Produto já cadastrado!");
        }
        return tipoProdutoRepository.save(tipoProduto);
    }

    public TipoProduto findById(Long id) throws Exception {
        return tipoProdutoRepository.findById(id).orElseThrow(() -> new Exception("Tipo de Produto não encontrado"));
    }

    public List<TipoProduto> findAll() {
        return tipoProdutoRepository.findAll();
    }

    public TipoProduto update(TipoProduto tipoProduto) throws Exception {
        if (!tipoProdutoRepository.existsById(tipoProduto.getId())) {
            throw new Exception("Tipo de Produto não encontrado");
        }
        return tipoProdutoRepository.save(tipoProduto);
    }

    public void delete(Long id) throws Exception {
        if (!tipoProdutoRepository.existsById(id)) {
            throw new Exception("Tipo de Produto não encontrado");
        } else if(produtoRepository.findTipoProdutoById(id).isPresent()){
            throw new Exception("Esse Tipo de Produto já está em uso!");
        }
        tipoProdutoRepository.deleteById(id);
    }
}
