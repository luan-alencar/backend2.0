package com.unifacisa.shoppingcartservice.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class Conta implements Serializable {
    private static final long serialVersionUID = 6495481893638203330L;

    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
