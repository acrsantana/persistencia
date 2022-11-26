package br.com.infnet.persistencia.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp data;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne()
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedido;


}