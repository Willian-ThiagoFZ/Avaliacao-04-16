package com.estoques.controllers;

import com.estoques.models.Fornecedor;
import com.estoques.services.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fornecedor")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<Fornecedor> create(@Valid @RequestBody Fornecedor fornecedor) throws Exception {
        Fornecedor savedVendor = fornecedorService.create(fornecedor);
        return ResponseEntity.created(URI.create("/fornecedor/" + savedVendor.getId())).body(savedVendor);
    }

    @GetMapping("{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(fornecedorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll() {
        return ResponseEntity.ok(fornecedorService.findAll());
    }

    @PutMapping
    public ResponseEntity<Fornecedor> update(@Valid @RequestBody Fornecedor fornecedor) throws Exception {
        return ResponseEntity.ok(fornecedorService.update(fornecedor));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
