package com.unifacisa.shoppingcartservice.utils;

import com.unifacisa.shoppingcartservice.domain.Produto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudResourceUtils<E> {


    ResponseEntity<List<E>> listar();

    ResponseEntity<Mono<Produto>> salvar(E e);

    ResponseEntity<E> buscar(Long e);

    ResponseEntity<E> editar(E e);
}
