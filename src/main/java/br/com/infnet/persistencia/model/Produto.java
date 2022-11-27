package br.com.infnet.persistencia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produtos")
@Getter @Setter
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itemPedido;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Categoria categoria;
}
