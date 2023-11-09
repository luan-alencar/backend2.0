package com.unifacisa.usuarioservice.utils;

import java.util.List;

public interface CrudUtils<E, D> {

    List<D> listar();

    D salvar(E e);

    D buscar(Long id);

//    D editar(E e);

    void deletar(Long id);
}
