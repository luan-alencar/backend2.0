package com.unifacisa.usuarioservice.utils;

import com.unifacisa.usuarioservice.domain.Produto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudUtils<E> {

    List<E> listar();

    E salvar(E e);

    E buscar(Long e);

    E editar(E e);
}
