package com.unifacisa.usuarioservice.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
