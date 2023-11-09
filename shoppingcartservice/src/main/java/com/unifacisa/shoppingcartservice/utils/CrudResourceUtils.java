package com.unifacisa.shoppingcartservice.utils;

import com.unifacisa.shoppingcartservice.domain.Acao;
import com.unifacisa.shoppingcartservice.domain.Conta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudResourceUtils<E> {


    ResponseEntity<List<E>> listar();

    ResponseEntity<E> salvar(E e);

    ResponseEntity<E> buscar(Long e);

    ResponseEntity<E> editar(E e);

    ResponseEntity<Void> deletar(Long id);
}
