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
class CategoriaRepositoryTest {

    //Injeção de dependencia com anotação
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void testAdicionaCategoriaComSucesso(){
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        Categoria categoriaValidacao = categoriaRepository.save(categoria);

        assertNotNull(categoriaValidacao);
        assertNotNull(categoriaValidacao.getId());
    }

    @Test
    void testAtributoCategoriaNaoPodeSerNuloAoSalvarCategoria(){
        Categoria categoria = new Categoria();
        assertThrowsExactly(
                DataIntegrityViolationException.class,
                () -> categoriaRepository.save(categoria));
    }

    @Test
    void testBuscarCategoriaPorId(){
        Categoria categoria = new Categoria();
        categoria.setNome("Livros");
        Categoria save = categoriaRepository.save(categoria);
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(save.getId());
        assertEquals(save.getId(), categoriaOptional.get().getId());
        assertEquals(save.getNome(), categoriaOptional.get().getNome());
    }

    @Test
    void testBuscarTodasAsCategorias(){
        Categoria categoria = new Categoria();
        Categoria categoria2 = new Categoria();
        categoria.setNome("Livros");
        categoriaRepository.save(categoria);
        categoria2.setNome("Celulares");
        categoriaRepository.save(categoria2);
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 1);
    }

    @Test
    void testAlterarCategoriaCadastrada(){
        Categoria categoria = new Categoria();
        categoria.setNome("Modas");
        categoriaRepository.save(categoria);
        categoria.setNome("Moda");
        Categoria categoriaValidacao = categoriaRepository.saveAndFlush(categoria);
        assertEquals(categoria.getId(), categoriaValidacao.getId());
        assertEquals(categoria.getNome(), categoriaValidacao.getNome());
    }

    @Test
    void testFalhaAoAlterarCategoriaCadastradaParaNull(){
        Categoria categoria = new Categoria();
        categoria.setNome("Modas");
        categoriaRepository.save(categoria);
        categoria.setNome(null);
        assertThrowsExactly(
                DataIntegrityViolationException.class,
                () -> categoriaRepository.save(categoria));
    }

    @Test
    void testSucessoAoDeletarCategoriaPorId(){
        Categoria categoria = new Categoria();
        categoria.setNome("Brinquedos");

        categoriaRepository.save(categoria);
        Long id = categoria.getId();
        categoriaRepository.deleteById(categoria.getId());
        assertFalse(categoriaRepository.existsById(id));
    }
}
