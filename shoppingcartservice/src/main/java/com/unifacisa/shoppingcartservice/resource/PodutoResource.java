package com.unifacisa.shoppingcartservice.resource;


import com.unifacisa.shoppingcartservice.domain.Produto;
import com.unifacisa.shoppingcartservice.service.ProdutoService;
import com.unifacisa.shoppingcartservice.utils.ConstantsUtils;
import com.unifacisa.shoppingcartservice.utils.CrudResourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_PRODUTOS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_PRODUTOS)
public class PodutoResource implements CrudResourceUtils<Produto> {

    private final ProdutoService produtoService;

    @Override
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @Override
    @PostMapping
    public ResponseEntity<Mono<Produto>> salvar(Produto produto) {
        return ResponseEntity.ok().body(produtoService.salvar(produto));
    }

    @Override
    public ResponseEntity<Produto> buscar(Long e) {
        return null;
    }

    @Override
    public ResponseEntity<Produto> editar(Produto produto) {
        return null;
    }
}
