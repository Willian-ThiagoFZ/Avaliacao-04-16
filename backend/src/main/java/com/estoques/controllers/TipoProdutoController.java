package com.estoques.controllers;

import com.estoques.models.TipoProduto;
import com.estoques.services.TipoProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tipoProduto")
@RequiredArgsConstructor
public class TipoProdutoController {

    private final TipoProdutoService tipoProdutoService;

    @PostMapping
    public ResponseEntity<TipoProduto> create(@Valid @RequestBody TipoProduto tipoProduto) throws Exception {
        TipoProduto savedCategory = tipoProdutoService.create(tipoProduto);
        return ResponseEntity.created(URI.create("/tipoProduto/" + savedCategory.getId())).body(savedCategory);
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoProduto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(tipoProdutoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TipoProduto>> findAll() {
        return ResponseEntity.ok(tipoProdutoService.findAll());
    }

    @PutMapping
    public ResponseEntity<TipoProduto> update(@Valid @RequestBody TipoProduto tipoProduto) throws Exception {
        return ResponseEntity.ok(tipoProdutoService.update(tipoProduto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        tipoProdutoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
