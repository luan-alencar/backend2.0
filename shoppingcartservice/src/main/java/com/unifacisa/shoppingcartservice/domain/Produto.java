package com.unifacisa.shoppingcartservice.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
public class Produto implements Serializable {
    private static final long serialVersionUID = 6495481893638203330L;

    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
