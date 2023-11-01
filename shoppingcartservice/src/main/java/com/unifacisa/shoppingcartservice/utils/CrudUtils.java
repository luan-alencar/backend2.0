package com.unifacisa.shoppingcartservice.utils;

import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudUtils<E> {

    List<E> listar();

    E salvar(E e);

    Mono<E> buscar(Long e);

    Mono<E> editar(E e);

    Mono<Void> deletar(Long id);
}
