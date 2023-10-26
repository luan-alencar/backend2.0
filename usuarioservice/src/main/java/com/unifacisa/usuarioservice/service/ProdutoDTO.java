package com.unifacisa.usuarioservice.service;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProdutoDTO implements Serializable {

    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
