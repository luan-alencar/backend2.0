package com.unifacisa.shoppingcartservice.service;

import com.unifacisa.shoppingcartservice.domain.Produto;
import com.unifacisa.shoppingcartservice.service.exceptions.GlobalExceptionHandler;
import com.unifacisa.shoppingcartservice.utils.CrudUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_PRODUTOS;
import static com.unifacisa.shoppingcartservice.utils.ConstantsUtils.API_URL_PRODUTOS_ID;

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
                .bodyToMono(Produto.class)
                .onErrorResume(this::handleError);
        Produto novoProduto = produtoMono.block();
        return novoProduto;
    }

    @Override
    public Mono<Produto> buscar(Long e) {
        return webClient.get()
                .uri(API_URL_PRODUTOS_ID, e)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Produto.class)
                .onErrorResume(this::handleError);
    }

    @Override
    public Mono<Produto> editar(Produto produto) {
        return webClient.patch()
                .uri(API_URL_PRODUTOS)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().
                bodyToMono(Produto.class)
                .onErrorResume(this::handleError);
    }

    @Override
    public Mono<Void> deletar(Long id) {
        return webClient.delete()
                .uri(API_URL_PRODUTOS_ID)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(this::handleErrorVoid);
    }

    private Mono<? extends Produto> handleError(Throwable error) {
        return (error instanceof GlobalExceptionHandler)
                ? Mono.error(error)
                : Mono.error(new ServerErrorException("Erro do servidor."));
    }

    private Mono<? extends Void> handleErrorVoid(Throwable error) {
        if (error instanceof GlobalExceptionHandler) {
            return Mono.error(error);
        } else {
            HttpStatus status = ((GlobalExceptionHandler) error).getStatus();
            String razao = "Erro de servidor";
            return Mono.error(new GlobalExceptionHandler(status, razao));
        }
    }
}
