package br.com.infnet.persistencia.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity @Table(name = "itens_pedidos")
public class ItemPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    private Integer quantidade;
    private BigDecimal valor;
    private BigDecimal subtotal;

}
