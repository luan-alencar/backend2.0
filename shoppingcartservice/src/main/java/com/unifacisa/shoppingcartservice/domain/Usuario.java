package com.unifacisa.shoppingcartservice.domain;

import jakarta.persistence.OneToOne;
import lombok.Data;

import java.io.Serializable;

@Data
public class Usuario implements Serializable {
    private static final long serialVersionUID = 6495481893638203330L;

    private String nome;
    private String cpf;
    private Conta conta;
}
