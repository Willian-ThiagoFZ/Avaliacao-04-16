package com.estoques.services;

import com.estoques.models.Estoque;
import com.estoques.models.Produto;
import com.estoques.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public boolean existsByProdutoName(Produto produto) {
        return produtoRepository.findProdutoByName(produto.getName()).isPresent();
    }

    public Produto create(Produto produto) throws Exception {
        if (existsByProdutoName(produto)) {
            throw new Exception("Produto já cadastrado!");
        }
        return produtoRepository.save(produto);
    }

    public Produto findById(Long id) throws Exception {
        return produtoRepository.findById(id).orElseThrow(() -> new Exception("Produto não encontrado"));
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto update(Produto produto) throws Exception {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new Exception("Produto não encontrado");
        }
        return produtoRepository.save(produto);
    }

    public void delete(Long id) throws Exception {
        if (!produtoRepository.existsById(id)) {
            throw new Exception("Produto não encontrada");
        }
        produtoRepository.deleteById(id);
    }

    public Produto adicionarEmEstoque(Long id, Estoque estoque) throws Exception {
        Produto produto = findById(id);
        if (Objects.nonNull(estoque.getQuantity()) && estoque.getQuantity() >= 1) {
            produto.setBalance(Objects.nonNull(produto.getBalance()) ? produto.getBalance() + estoque.getQuantity() : estoque.getQuantity());
        } else {
            throw new Exception("Quantidade deve ser maior que zero!");
        }
        return produtoRepository.save(produto);
    }

    public Produto removerDoEstoque(Long id, Estoque estoque) throws Exception {
        Produto produto = findById(id);
        if (Objects.isNull(estoque.getQuantity())) {
            throw new Exception("Quantidade é obrigatório!");
        } else if (Objects.nonNull(produto.getBalance()) && (estoque.getQuantity() >= 1) && (produto.getBalance() >= estoque.getQuantity())) {
            produto.setBalance(produto.getBalance() - estoque.getQuantity());
        } else {
            throw new Exception("Quantidade a ser retirada excede a quantidade em estoque!");
        }
        return produtoRepository.save(produto);
    }
}
