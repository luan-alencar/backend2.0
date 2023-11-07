package com.unifacisa.usuarioservice.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 6495481893638204530L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @OneToOne
    private Conta conta;
}

