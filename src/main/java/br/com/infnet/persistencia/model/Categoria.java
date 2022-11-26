package br.com.infnet.persistencia.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String categoria;
//    @OneToMany(mappedBy = "categoria")
//    private List<Produto> produto;
}
