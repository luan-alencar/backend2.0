package com.unifacisa.usuarioservice.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@Entity
public class Acao implements Serializable {
    private static final long serialVersionUID = 6495481893638203330L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double valor;
}
