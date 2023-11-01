package com.unifacisa.usuarioservice.utils;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudResourceUtils<E, D> {


    ResponseEntity<List<D>> listar();

    ResponseEntity<D> salvar(E e);

    ResponseEntity<D> buscar(Long id);

    ResponseEntity<D> editar(E e);

    ResponseEntity<Void> deletar(Long id);
}
