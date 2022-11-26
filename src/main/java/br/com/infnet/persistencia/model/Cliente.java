package br.com.infnet.persistencia.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String nome;
    private String email;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}