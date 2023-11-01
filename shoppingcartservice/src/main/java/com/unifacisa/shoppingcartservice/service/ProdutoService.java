package com.unifacisa.shoppingcartservice.service;

import com.unifacisa.shoppingcartservice.domain.Produto;
import com.unifacisa.shoppingcartservice.service.exceptions.ClientErrorException;
import com.unifacisa.shoppingcartservice.service.exceptions.CustomExceptionHandler;
import com.unifacisa.shoppingcartservice.utils.CrudUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;
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
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Produto.class)
                .onErrorResume(this::handleError);
    }

    private Mono<? extends Produto> handleError(Throwable error) {
        return (error instanceof ClientErrorException)
                ? Mono.error((ClientErrorException) error)
                : Mono.error(new ServerErrorException("Erro do servidor."));
    }

    private Mono<? extends Throwable> handlerServerError(ClientResponse clientResponse) {
        return Mono.error(new ServerErrorException("Erro de servidor"));
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
