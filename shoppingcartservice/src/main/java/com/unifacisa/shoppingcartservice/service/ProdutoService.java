package com.unifacisa.shoppingcartservice.service;

import com.unifacisa.shoppingcartservice.domain.Produto;
import com.unifacisa.shoppingcartservice.utils.CrudUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_PRODUTOS;

@Service
@RequiredArgsConstructor
public class ProdutoService implements CrudUtils<Produto> {

    private final WebClient webClient;

    public List<Produto> listar() {
        Flux<Produto> produtosExternoFlux = webClient.get()
                .uri(API_URL_PRODUTOS)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Produto.class);

        List<Produto> todosProdutos = produtosExternoFlux.collectList().block();
        return todosProdutos;
    }

    @Override
    public Mono<Produto> salvar(Produto produto) {
        return webClient.post()
                .uri(API_URL_PRODUTOS)
                .body(Mono.just(produto), Produto.class)
                .retrieve()
                .bodyToMono(Produto.class);
    }

    @Override
    public Produto buscar(Long e) {
        return null;
    }

    @Override
    public Produto editar(Produto produto) {
        return null;
    }
}
