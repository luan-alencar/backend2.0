package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.repository.ProdutoRepository;
import com.unifacisa.usuarioservice.utils.CrudUtils;
import com.unifacisa.usuarioservice.utils.exceptions.CustomServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService implements CrudUtils<Produto> {


    private final WebClient webClient;

    private ProdutoRepository produtoRepository;

    @Override
    public ArrayList<Produto> listar() {
        Flux<Produto> produtosExternoFlux = webClient.get()
                .uri("/api/produtos")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, this::handler5xxServerError)
                .bodyToFlux(Produto.class);

        ArrayList<Produto> todosProdutos = (ArrayList<Produto>) produtosExternoFlux.collectList().block();
        return todosProdutos;
    }

    private Mono<? extends Throwable> handler5xxServerError(ClientResponse clientResponse) {
        return Mono.error(new CustomServerErrorException("Erro do server"));
    }

    @Override
    public Produto salvar(Produto produto) {
        return null;
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
