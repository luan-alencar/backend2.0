package com.unifacisa.shoppingcartservice.utils;

import com.unifacisa.shoppingcartservice.domain.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudResourceUtils<E> {


    ResponseEntity<List<E>> listar();

    ResponseEntity<Produto> salvar(E e);

    ResponseEntity<E> buscar(Long e);

    ResponseEntity<E> editar(E e);

    ResponseEntity<Void> deletar(Long id);
}
