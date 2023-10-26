package com.unifacisa.usuarioservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
public class Usuario implements Serializable {
    private static final long serialVersionUID = 6495481893638204530L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
}

