package com.unifacisa.usuarioservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProdutoDTO {

    private String nome;
    private Double preco;
    private Integer quantidade;
}
