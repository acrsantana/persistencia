package br.com.infnet.persistencia.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itemPedido;
    @ManyToOne
    private Categoria categoria;
}
