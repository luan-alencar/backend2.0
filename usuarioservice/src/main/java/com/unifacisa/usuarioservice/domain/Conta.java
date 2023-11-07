package com.unifacisa.usuarioservice.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Conta implements Serializable {
    private static final long serialVersionUID = 6495481893638203330L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double saldo;

    private Double limite;

    private Integer total;

    @OneToOne
    private Usuario usuario;
}
