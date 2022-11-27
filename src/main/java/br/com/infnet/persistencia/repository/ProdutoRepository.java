package br.com.infnet.persistencia.repository;

import br.com.infnet.persistencia.model.Categoria;
import br.com.infnet.persistencia.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
