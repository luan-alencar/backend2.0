package com.unifacisa.usuarioservice.utils;

import com.unifacisa.usuarioservice.domain.Produto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudUtils<E, D> {

    List<D> listar();

    D salvar(E e);

    D buscar(Long e);

    D editar(E e);
}
