package com.unifacisa.shoppingcartservice.utils;

import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudUtils<E> {

    List<E> listar();

    Mono<E> salvar(E e);

    E buscar(Long e);

    E editar(E e);
}
