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
public class ProdutoRepositoryTest {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testAdicionaProdutoComSucesso(){
        Categoria categoria = new Categoria();
        categoria.setCategoria("Eletrônicos");
        Produto produto = new Produto();
        produto.setNome("Soundbar");
        produto.setDescricao("Soundbar Dolby Atmos");
        produto.setPreco(new BigDecimal(1500.0));
        produto.setQuantidade(20);
        produto.setCategoria(categoria);

        Produto produtoValidacao = produtoRepository.save(produto);

        assertNotNull(produtoValidacao);
        assertEquals(produto.getId(), produtoValidacao.getId());
        assertEquals(produto.getCategoria(), produtoValidacao.getCategoria());
    }

//    @Test
//    public void testAtributoCategoriaNaoPodeSerNuloAoSalvarCategoria(){
//        Categoria categoria = new Categoria();
//        assertThrowsExactly(
//                DataIntegrityViolationException.class,
//                () -> produtoRepository.save(categoria));
//    }
//
//    @Test
//    public void testBuscarCategoriaPorId(){
//        Categoria categoria = new Categoria();
//        categoria.setCategoria("Livros");
//        Categoria save = produtoRepository.save(categoria);
//        Optional<Categoria> categoriaOptional = produtoRepository.findById(save.getId());
//        assertEquals(save.getId(), categoriaOptional.get().getId());
//        assertEquals(save.getCategoria(), categoriaOptional.get().getCategoria());
//    }
//
//    @Test
//    public void testBuscarTodasAsCategorias(){
//        Categoria categoria = new Categoria();
//        Categoria categoria2 = new Categoria();
//        categoria.setCategoria("Livros");
//        produtoRepository.save(categoria);
//        categoria2.setCategoria("Celulares");
//        produtoRepository.save(categoria2);
//        List<Categoria> categorias = produtoRepository.findAll();
//        assertNotNull(categorias);
//        assertTrue(categorias.size() > 1);
//    }
//
//    @Test
//    public void testAlterarCategoriaCadastrada(){
//        Categoria categoria = new Categoria();
//        categoria.setCategoria("Modas");
//        produtoRepository.save(categoria);
//        categoria.setCategoria("Moda");
//        Categoria categoriaValidacao = produtoRepository.saveAndFlush(categoria);
//        assertEquals(categoria.getId(), categoriaValidacao.getId());
//        assertEquals(categoria.getCategoria(), categoriaValidacao.getCategoria());
//    }
//
//    @Test
//    public void testFalhaAoAlterarCategoriaCadastradaParaNull(){
//        Categoria categoria = new Categoria();
//        categoria.setCategoria("Modas");
//        produtoRepository.save(categoria);
//        categoria.setCategoria(null);
//        assertThrowsExactly(
//                DataIntegrityViolationException.class,
//                () -> produtoRepository.save(categoria));
//    }
//
//    @Test
//    public void testSucessoAoDeletarCategoriaPorId(){
//        Categoria categoria = new Categoria();
//        categoria.setCategoria("Brinquedos");
//
//        produtoRepository.save(categoria);
//        Long id = categoria.getId();
//        produtoRepository.deleteById(categoria.getId());
//        assertFalse(produtoRepository.existsById(id));
//    }
}
