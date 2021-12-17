package com.estoques.services;

import com.estoques.models.Fornecedor;
import com.estoques.repositories.FornecedorRepository;
import com.estoques.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;

    public boolean existsByFornecedorName(Fornecedor fornecedor) {
        return fornecedorRepository.findFornecedorByName(fornecedor.getName()).isPresent();
    }

    public Fornecedor create(Fornecedor fornecedor) throws Exception {
        if (existsByFornecedorName(fornecedor)) {
            throw new Exception("Fornecedor já cadastrado!");
        }
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor findById(Long id) throws Exception {
        return fornecedorRepository.findById(id).orElseThrow(() -> new Exception("Fornecedor não encontrado"));
    }

    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor update(Fornecedor fornecedor) throws Exception {
        if (!fornecedorRepository.existsById(fornecedor.getId())) {
            throw new Exception("Fornecedor não encontrado");
        }
        return fornecedorRepository.save(fornecedor);
    }

    public void delete(Long id) throws Exception {
        if (!fornecedorRepository.existsById(id)) {
            throw new Exception("TipoProduto não encontrado");
        } else if(produtoRepository.findFornecedorById(id).isPresent()){
            throw new Exception("Este fornecedor está em uso!");
        }
        fornecedorRepository.deleteById(id);
    }

}
