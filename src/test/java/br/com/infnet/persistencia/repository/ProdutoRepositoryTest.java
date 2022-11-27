package br.com.infnet.persistencia.repository;

import br.com.infnet.persistencia.model.Categoria;
import br.com.infnet.persistencia.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProdutoRepositoryTest {

    //Injeção de dependencia com anotação
    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void testAdicionaProdutoComSucesso(){
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");
        Produto produto = new Produto();
        produto.setNome("Soundbar");
        produto.setDescricao("Soundbar Dolby Atmos");
        produto.setPreco(new BigDecimal("1500.0"));
        produto.setQuantidade(20);
        produto.setCategoria(categoria);

        Produto produtoValidacao = produtoRepository.save(produto);

        assertNotNull(produtoValidacao);
        assertEquals(produto.getId(), produtoValidacao.getId());
        assertEquals(produto.getCategoria(), produtoValidacao.getCategoria());
    }

    @Test
    void testAtributoCategoriaNomeNaoPodeSerNuloAoSalvarProduto(){
        Categoria categoria = new Categoria();
        Produto produto = new Produto();
        produto.setNome("Soundbar");
        produto.setDescricao("Soundbar Dolby Atmos");
        produto.setPreco(new BigDecimal("1500.0"));
        produto.setQuantidade(20);
        produto.setCategoria(categoria);
        assertThrowsExactly(
                DataIntegrityViolationException.class,
                () -> produtoRepository.save(produto));
    }

    @Test
    void testAtributoProdutoNomeNaoPodeSerNuloAoSalvarProduto(){
        Categoria categoria = new Categoria();
        categoria.setNome("Videogames");
        Produto produto = new Produto();
        produto.setDescricao("Xbox Series X");
        produto.setPreco(new BigDecimal("3900.0"));
        produto.setQuantidade(20);
        produto.setCategoria(categoria);
        assertThrowsExactly(
            DataIntegrityViolationException.class,
            () -> produtoRepository.save(produto));
    }

    @Test
    void testAtributoProdutoPrecoNaoPodeSerNuloAoSalvarProduto(){
        Categoria categoria = new Categoria();
        categoria.setNome("Videogames");
        Produto produto = new Produto();
        produto.setNome("Xbox Series X");
        produto.setDescricao("Xbox Series X");
        produto.setQuantidade(20);
        produto.setCategoria(categoria);
        assertThrowsExactly(
            DataIntegrityViolationException.class,
            () -> produtoRepository.save(produto));
    }

    @Test
    void testAtributoProdutoQuantidadeNaoPodeSerNuloAoSalvarProduto(){
        Categoria categoria = new Categoria();
        categoria.setNome("Videogames");
        Produto produto = new Produto();
        produto.setNome("Xbox Series X");
        produto.setDescricao("Xbox Series X");
        produto.setPreco(new BigDecimal("3900.0"));
        produto.setCategoria(categoria);
        assertThrowsExactly(
            DataIntegrityViolationException.class,
            () -> produtoRepository.save(produto));
    }

    @Test
    void testBuscarProdutoPorId(){
        Categoria categoria = new Categoria();
        categoria.setNome("Livros");
        Produto produto = new Produto();
        produto.setNome("Clean Code");
        produto.setDescricao("Livro sobre clean code");
        produto.setPreco(new BigDecimal("150.0"));
        produto.setQuantidade(30);
        produto.setCategoria(categoria);

        produtoRepository.save(produto);

        Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());

        assertEquals(produto.getId(), optionalProduto.get().getId());
        assertEquals(produto.getNome(), optionalProduto.get().getNome());
        assertNotNull(optionalProduto.get().getCategoria());
    }

    @Test
    void testBuscarTodosOsProdutos(){
        Categoria categoria = new Categoria();
        categoria.setNome("Bebidas");
        Produto produto = new Produto();
        produto.setNome("Gin Tanqueray");
        produto.setDescricao("Gin Tanqueray 1l");
        produto.setPreco(new BigDecimal("150.0"));
        produto.setQuantidade(80);
        produto.setCategoria(categoria);

        produtoRepository.save(produto);

        List<Produto> produtos = produtoRepository.findAll();
        assertNotNull(produtos);
        assertTrue(produtos.size() >= 1);
    }

    @Test
    void testAlterarProdutoCadastrado(){
        Categoria categoria = new Categoria();
        categoria.setNome("Modas");
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setDescricao("Camiseta 100% algodão");
        produto.setPreco(new BigDecimal("50.0"));
        produto.setQuantidade(10);
        produto.setCategoria(categoria);

        produtoRepository.save(produto);

        produto.setQuantidade(25);

        Produto produtoValidacao = produtoRepository.saveAndFlush(produto);

        assertEquals(produtoValidacao.getId(), produto.getId());
        assertEquals("Camiseta", produtoValidacao.getNome());
        assertEquals("Camiseta 100% algodão", produtoValidacao.getDescricao());
        assertNotEquals(10, produtoValidacao.getQuantidade());
    }

    @Test
    void testFalhaAoAlterarProdutoComCategoriaNomeParaNull(){
        Categoria categoria = new Categoria();
        categoria.setNome("Brinquedos");
        Produto produto = new Produto();
        produto.setNome("Cara a Cara");
        produto.setDescricao("Descubra o personagem");
        produto.setPreco(new BigDecimal("99.0"));
        produto.setQuantidade(15);
        produto.setCategoria(categoria);

        produtoRepository.save(produto);

        categoria.setNome(null);
                assertThrowsExactly(
                DataIntegrityViolationException.class,
                () -> produtoRepository.save(produto));
    }

    @Test
    void testSucessoAoDeletarProdutoPorId(){
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrodomésticos");
        Produto produto = new Produto();
        produto.setNome("Geladeira Electrolux");
        produto.setDescricao("Geladeira Electrolux 475l");
        produto.setPreco(new BigDecimal("4499.0"));
        produto.setQuantidade(10);
        produto.setCategoria(categoria);

        produtoRepository.save(produto);
        Long produtoId = produto.getId();
        produtoRepository.deleteById(produto.getId());

        assertFalse(produtoRepository.existsById(produtoId));
    }
}
