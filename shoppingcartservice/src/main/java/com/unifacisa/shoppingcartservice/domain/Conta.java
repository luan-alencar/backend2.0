package com.unifacisa.shoppingcartservice.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class Conta implements Serializable {
    private static final long serialVersionUID = -5971774670037394998L;

    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
