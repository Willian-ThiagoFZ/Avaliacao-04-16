package com.estoques.demo;

import com.estoques.models.Estoque;
import com.estoques.models.Fornecedor;
import com.estoques.models.Produto;
import com.estoques.models.TipoProduto;
import com.estoques.repositories.ProdutoRepository;
import com.estoques.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegraNegocioTest {
    private Produto produto;
    private Estoque estoque;

    @InjectMocks
    private ProdutoService produtoService;
    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp(){
        produto = Produto.builder()
                .id(1l)
                .name("TV LG")
                .balance(0)
                .precoCompra(3000.00)
                .precoVenda(3800.00)
                .tipoProduto(TipoProduto.builder().id(1L).name("Eletronicos").build())
                .fornecedor(Fornecedor.builder().id(1L).name("LG").build())
                .build();

        estoque = Estoque.builder().quantity(2).build();
    }

    @Test
    void DeveAdicionarProdutosNoEstoque_RetornarSucess() throws Exception {
        when(produtoRepository.findById(produto.getId())).thenReturn(java.util.Optional.ofNullable(produto));
        when(produtoRepository.save(produto)).thenReturn(produto);

        produtoService.adicionarEmEstoque(produto.getId(), estoque);

        assertThat(produto.getBalance()).isEqualTo(2);
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void DeveRemoverProdutosDoEstoque_RetornarSucess(){
        produto.setBalance(1);
        when(produtoRepository.findById(produto.getId())).thenReturn(java.util.Optional.ofNullable(produto));

        assertThrows(Exception.class, () -> produtoService.removerDoEstoque(produto.getId(), estoque));
    }

}