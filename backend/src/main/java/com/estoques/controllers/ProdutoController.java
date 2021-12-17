package com.estoques.controllers;

import com.estoques.models.Estoque;
import com.estoques.models.Produto;
import com.estoques.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> create(@Valid @RequestBody Produto produto) throws Exception {
        Produto savedProduct = produtoService.create(produto);
        return ResponseEntity.created(URI.create("/produto/" + savedProduct.getId())).body(savedProduct);
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @PutMapping
    public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto) throws Exception {
        return ResponseEntity.ok(produtoService.update(produto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/addstock/{id}")
    public ResponseEntity<Produto> addToStock(@PathVariable Long id, @RequestBody Estoque estoque) throws Exception {
        return ResponseEntity.ok(produtoService.adicionarEmEstoque(id, estoque));
    }

    @PatchMapping("/removestock/{id}")
    public ResponseEntity<Produto> removeFromStock(@PathVariable Long id, @RequestBody Estoque estoque) throws Exception {
        return ResponseEntity.ok(produtoService.removerDoEstoque(id, estoque));
    }
}
