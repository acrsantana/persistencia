package br.com.infnet.persistencia.repository;

import br.com.infnet.persistencia.model.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoriaRepositoryTest {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void testAdicionaCategoriaComSucesso(){
        Categoria categoria = new Categoria();
        categoria.setCategoria("Eletrônicos");

        Categoria categoriaValidacao = categoriaRepository.save(categoria);

        assertNotNull(categoriaValidacao);
        assertNotNull(categoriaValidacao.getId());
    }

    @Test
    public void testAtributoCategoriaNaoPodeSerNuloAoSalvarCategoria(){
        Categoria categoria = new Categoria();
        assertThrowsExactly(
                DataIntegrityViolationException.class,
                () -> categoriaRepository.save(categoria));
    }

    @Test
    public void testBuscarCategoriaPorId(){
        Categoria categoria = new Categoria();
        categoria.setCategoria("Livros");
        Categoria save = categoriaRepository.save(categoria);
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(save.getId());
        assertEquals(save.getId(), categoriaOptional.get().getId());
        assertEquals(save.getCategoria(), categoriaOptional.get().getCategoria());
    }

    @Test
    public void testBuscarTodasAsCategorias(){
        Categoria categoria = new Categoria();
        Categoria categoria2 = new Categoria();
        categoria.setCategoria("Livros");
        categoriaRepository.save(categoria);
        categoria2.setCategoria("Celulares");
        categoriaRepository.save(categoria2);
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 1);
    }

    @Test
    public void testAlterarCategoriaCadastrada(){
        Categoria categoria = new Categoria();
        categoria.setCategoria("Modas");
        categoriaRepository.save(categoria);
        categoria.setCategoria("Moda");
        Categoria categoriaValidacao = categoriaRepository.saveAndFlush(categoria);
        assertEquals(categoria.getId(), categoriaValidacao.getId());
        assertEquals(categoria.getCategoria(), categoriaValidacao.getCategoria());
    }

    @Test
    public void testFalhaAoAlterarCategoriaCadastradaParaNull(){
        Categoria categoria = new Categoria();
        categoria.setCategoria("Modas");
        categoriaRepository.save(categoria);
        categoria.setCategoria(null);
        assertThrowsExactly(
                DataIntegrityViolationException.class,
                () -> categoriaRepository.save(categoria));
    }

    @Test
    public void testSucessoAoDeletarCategoriaPorId(){
        Categoria categoria = new Categoria();
        categoria.setCategoria("Brinquedos");

        categoriaRepository.save(categoria);
        Long id = categoria.getId();
        categoriaRepository.deleteById(categoria.getId());
        assertFalse(categoriaRepository.existsById(id));
    }
}
