package com.unifacisa.shoppingcartservice.service;

import com.unifacisa.shoppingcartservice.domain.Produto;
import com.unifacisa.shoppingcartservice.service.exceptions.GlobalExceptionHandler;
import com.unifacisa.shoppingcartservice.utils.CrudUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_PRODUTOS;
import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_PRODUTOS_ID;
import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_PRODUTOS_EDITAR;

@Service
@RequiredArgsConstructor
public class ProdutoService implements CrudUtils<Produto> {

    private final WebClient webClient;

    public List<Produto> listar() {
        Flux<Produto> produtosExternoFlux = webClient.get()
                .uri(API_URL_PRODUTOS)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Produto.class)
                .onErrorResume(this::handleError);

        List<Produto> todosProdutos = produtosExternoFlux.collectList().block();
        return todosProdutos;
    }

    @Override
    public Produto salvar(Produto produto) {
        Mono<Produto> produtoMono = webClient.post()
                .uri(API_URL_PRODUTOS)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(produto)
                .retrieve()
                .bodyToMono(Produto.class);
//                .onErrorResume(this::handleError);
        Produto novoProduto = produtoMono.block();
        return novoProduto;
    }

    @Override
    public Produto buscar(Long id) {
        Mono<Produto> produtoMono = webClient.get()
                .uri(API_URL_PRODUTOS_ID, id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Produto.class)
                .onErrorResume(this::handleError);
        Produto produtoEcontrado = produtoMono.block();
        return produtoEcontrado;
    }

    @Override
    public Produto editar(Produto produto) {
        Mono<Produto> produtoMono = webClient.put()
                .uri(API_URL_PRODUTOS)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().
                bodyToMono(Produto.class);
//                .onErrorResume(this::handleError);
        Produto novoProduto = produtoMono.block();
        return novoProduto;
    }

    @Override
    public void deletar(Long id) {
        webClient.delete()
                .uri(API_URL_PRODUTOS_ID, id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
//                .onErrorResume(this::handleErrorVoid);
    }

    private Mono<? extends Produto> handleError(Throwable error) {
        return (error instanceof GlobalExceptionHandler)
                ? Mono.error(error)
                : Mono.error(new GlobalExceptionHandler());
    }

    private Mono<? extends Void> handleErrorVoid(Throwable error) {
        if (error instanceof GlobalExceptionHandler) {
            return Mono.error(error);
        } else {
            HttpStatusCode status = ((GlobalExceptionHandler) error).getStatusCode();
            String razao = "Erro de servidor";
            return Mono.error(new GlobalExceptionHandler(status, razao));
        }
    }
}
